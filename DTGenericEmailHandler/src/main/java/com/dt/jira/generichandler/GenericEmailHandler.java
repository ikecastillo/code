package com.dt.jira.generichandler;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.service.util.handler.MessageHandler;
import com.atlassian.jira.service.util.handler.MessageHandlerContext;
import com.atlassian.jira.service.util.handler.MessageHandlerErrorCollector;
import com.atlassian.jira.service.util.handler.MessageUserProcessor;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.workflow.JiraWorkflow;
import com.atlassian.jira.workflow.WorkflowManager;
import com.atlassian.mail.MailUtils;
import com.opensymphony.workflow.loader.ActionDescriptor;
import com.opensymphony.workflow.loader.StepDescriptor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.*;

import static com.dt.jira.generichandler.utils.IssueListCreatorFromSubscriptionMail.getListOfIssuesFromSubscriptionMail;

/**
 * This code will execute
 * 1) During initial setup of handler (init)
 * 2) When the handler related mail comes (handleMessage)
 *
 * Created by Yagnesh.Bhat on 2/10/2016.
 */
public class GenericEmailHandler implements MessageHandler {

    private final Logger log = LoggerFactory.getLogger(GenericEmailHandler.class);
    private final IssueKeyValidator issueKeyValidator;
    private final MessageUserProcessor messageUserProcessor;

    // Parameter names of the handler
    public static final String KEY_PROJECT = "project";
    public static final String KEY_ASSOCIATED_STATUS = "associatedstatus";
    public static final String KEY_KEYWORD = "keyword";

    //param values of the handler
    private String projectKey;
    private List<String> associatedStatusList; // This is used to hold the list of associated statuses
    private List<String> keyWordsList; //This is used to hold the list of keywords

    private String selectedKWFromMailBody; //To decide upon the transition based on the mail body keyword


    public GenericEmailHandler(MessageUserProcessor messageUserProcessor, IssueKeyValidator issueKeyValidator) {
	//log.info("----------------------------------constructor----- starts--------------------------------");
        this.messageUserProcessor = messageUserProcessor;
        this.issueKeyValidator = issueKeyValidator;
		//log.info("----------------------------------constructor------ ends-------------------------------");
    }

    @Override
    public void init(Map<String, String> params, MessageHandlerErrorCollector messageHandlerErrorCollector) {
        log.info("--------------------------------------------------------Generic Email Handler init(params: " + params + ")");

        if (params.containsKey(KEY_PROJECT)) {
            projectKey = params.get(KEY_PROJECT);
        } else {
            log.info("-----------------------------------Project Key is Mandatory");
        }

        String associatedStatus = "";
        String keyWord = "";
        if (params.containsKey(KEY_ASSOCIATED_STATUS)) {
            associatedStatus = params.get(KEY_ASSOCIATED_STATUS);
        } else {
            log.info("----------------------------------------------Associated Status is mandatory");
        }

        if (params.containsKey(KEY_KEYWORD)) {
            keyWord = params.get(KEY_KEYWORD);
        } else {
            log.info("----------------------------------------------Key Word is mandatory");
        }


        if (keyWord.contains("|")) {
            //split and add in keywords list
            keyWordsList = Arrays.asList(keyWord.toLowerCase().split("\\|"));
        } else {
            //Just add the keyword
            keyWordsList = new ArrayList<>();
            keyWordsList.add(keyWord.toLowerCase());
        }

        if (associatedStatus.contains("|")) {
            //split and add in associated statusses list
            associatedStatusList = Arrays.asList(associatedStatus.split("\\|"));
        } else {
            //Just add the associated status
            associatedStatusList = new ArrayList<>();
            associatedStatusList.add(associatedStatus);
        }

        log.info("-----------------------Params: " + projectKey + " - " + associatedStatusList.toString() + " - " + keyWordsList.toString());
    }

    @Override
    public boolean handleMessage(Message message, MessageHandlerContext context) throws MessagingException {
       // log.info("--------------------Got a mail for the handler-------------------------");
		
		//log.info("----------message------"+ message);
		
		//log.info("---From---"+ message.getFrom()[0]);
		//log.info("---TO---"+ message.getRecipients(Message.RecipientType.TO)[0]);
		
		//log.info("----------context------"+ context);
		

        // this is a small util method JIRA API provides for us, let's use it.
        final ApplicationUser sender = messageUserProcessor.getAuthorFromSender(message);
        if (sender == null) {
          /*  context.getMonitor().error("Message sender(s) '" + StringUtils.join(MailUtils.getSenders(message), ",")
                    + "' do not have corresponding users in JIRA. Message will be ignored........ find error");*/
            return false;
        }

        //message body
        final String mailBody = MailUtils.getBody(message);
        log.debug("Mail body contents as received by the handler --> " + mailBody + " <-- END");

        Set<String> issueKeySet = getListOfIssuesFromSubscriptionMail(mailBody, projectKey);
        log.debug("Issue Key set returned by the utility for the handler is " + issueKeySet);


        if (mailBodyContainsOneOfKeywords(mailBody,keyWordsList)) {
            for (String issueKey : issueKeySet) {
                final Issue issue = issueKeyValidator.validateIssue(issueKey, context.getMonitor());

                // let's again validate the issue key - meanwhile issue could have been deleted, closed, etc..
                if (issue == null) {
                    log.error("No valid issue key found to process for this handler, the handler will exit");
                } else {
                    moveIssuePerHandlerRules(issue, issueKey, mailBody, sender, context);
                }
            }
        }

        return true;
    }

    /**
     * This is the important method that actually transitions issues.
     *
     * @param issue
     * @param issueKey
     * @param mailBody
     * @param sender
     */
    private void moveIssuePerHandlerRules(Issue issue, String issueKey, String mailBody, ApplicationUser sender,
                                          MessageHandlerContext context) {

        //Add the mail body as a comment
        log.debug("Going ahead to add the comment as mail body");


        // thanks to using passed context we don't need to worry about normal run vs. test run - our call
        // will be dispatched accordingly
        context.createComment(issue, sender, mailBody, false);
        log.debug("Mail body added as comment to the issue!");


        String projectKey = issueKey.split("-")[0]; //For eg, if issuekey is XYZ-123, then project key is XYZ
        log.debug("Project Key captured to move the issue is " + projectKey);
        String issueTypeId = issue.getIssueType().getId();

        String issueStatus = issue.getStatus().getName();
        log.debug("Status id of the issue is " + issueStatus);
        log.debug("Issue type id is " + issueTypeId);

        log.debug("Associated status list contains issue status? " + associatedStatusList.contains(issueStatus));
        if (projectKey.equals(this.projectKey)
                && associatedStatusList.contains(issueStatus)) {
                log.debug("Handler proceeding to change the status of the issue " + issueKey);
                //Transition the issue
                IssueService issueService = ComponentAccessor.getIssueService();
                IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();


                Integer transitionId = null;
                WorkflowManager workflowManager = ComponentAccessor.getWorkflowManager();
                JiraWorkflow workflow = workflowManager.getWorkflow(issue);
                StepDescriptor sd = workflow.getLinkedStep(issue.getStatus());
                List<ActionDescriptor> actions = sd.getActions();

                log.debug("Total Actions are " + actions.size());
                for (ActionDescriptor actionDescriptor : actions) {
                    if (selectedKWFromMailBody.equalsIgnoreCase(actionDescriptor.getName())) {
                        log.debug("Action getname required is " + actionDescriptor.getName());
                        transitionId = actionDescriptor.getId();
                        log.debug("Required transition id is " + transitionId);
                    }
                }

                if (transitionId != null) {
                    IssueService.TransitionValidationResult transitionValidationResult =
                            issueService.validateTransition(sender, issue.getId(), transitionId, issueInputParameters);

                    if (transitionValidationResult.isValid()) {
                        IssueService.IssueResult transitionResult = issueService.transition(sender, transitionValidationResult);
                        if (!transitionResult.isValid())
                        {
                            log.error("Cannot transition the issue " + issueKey + " to required status ");
                        }
                    }
                }
        }

    }

    /**
     * Checks if the if the first word in the mail body thats sent to handler contains any of the
     * configured keywords
     *
     * @param mailBody
     * @param keyWordsList
     * @return
     */
    private boolean mailBodyContainsOneOfKeywords(String mailBody, List<String> keyWordsList) {
        String kWIinMailBody = mailBody.trim().toLowerCase().split(" ")[0];
        log.debug("Key word found in mail body is " + kWIinMailBody);

        for(String keyWord : keyWordsList) {
            if (kWIinMailBody.contains(keyWord)) {
                selectedKWFromMailBody = keyWord;
                log.debug("SELECTED THIS KEYWORD FROM MAIL BODY FOR PROCESSING " + selectedKWFromMailBody);
                return true;
            }
        }
        return false;
    }

}
