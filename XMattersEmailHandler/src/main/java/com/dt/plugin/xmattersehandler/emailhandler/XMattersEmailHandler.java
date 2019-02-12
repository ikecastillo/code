package com.dt.plugin.xmattersehandler.emailhandler;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.service.util.handler.MessageHandler;
import com.atlassian.jira.service.util.handler.MessageHandlerContext;
import com.atlassian.jira.service.util.handler.MessageHandlerErrorCollector;
import com.atlassian.jira.service.util.handler.MessageUserProcessor;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.mail.MailUtils;
import com.dt.plugin.xmattersehandler.api.EmailParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * XMattersEMailHandler class handles any incoming mail sent to XMattersConffeed@dealertrack.com.
 * This is configured to handle any email thats sent by XMatters, related to the XMatters event.
 * When a mail is received, this class is responsible to parsing the mail body and extracting the management or
 * technical conference bridge numbers found at the end of the mail. It then looks at the incident related to this
 * xMatters event and then tries to update the fields xMatters Technical Conference Bridge Number or
 * xMatters Management Conference Bridge Number, depending on the bridge type determined from the HipChat room Url value.
 * Note, it will only update these fields if they are found to be empty in the incident ticket. To parse the mail,
 * it makes use of the custom parser implementation of EmailParser interface, which is injected to it.
 *
 * Created by Yagnesh.Bhat on 9/1/2016.
 */
public class XMattersEmailHandler implements MessageHandler {

    private final Logger log = LoggerFactory.getLogger(XMattersEmailHandler.class);

    private final EmailParser emailParser;

    private final MessageUserProcessor messageUserProcessor;
    private final IssueService issueService;
    private final ApplicationUser jiraAdminUser;

    private static final String TECH_CONF_BRIDGE_NUMBER = "xMatters Technical Conference Bridge Number";
    private static final String MGMT_CONF_BRIDGE_NUMBER = "xMatters Management Conference Bridge Number";

    public XMattersEmailHandler(EmailParser emailParser, MessageUserProcessor messageUserProcessor) {
        this.emailParser = emailParser;
        this.messageUserProcessor = messageUserProcessor;
        issueService = ComponentAccessor.getIssueService();
        //need one standard user to set
        jiraAdminUser  = ComponentAccessor.getUserManager().getUserByName("dtjira.admin");
    }

    @Override
    public void init(Map<String, String> params, MessageHandlerErrorCollector messageHandlerErrorCollector) {
        log.info("--------------------XMattersEmailHandler.init ( params: " + params + ")");
    }

    @Override
    public boolean handleMessage(Message message, MessageHandlerContext messageHandlerContext)
            throws MessagingException {

        JiraAuthenticationContext jAC = ComponentAccessor.getJiraAuthenticationContext();

        jAC.setLoggedInUser(jiraAdminUser);

        log.info("-----------------------------XMATTERS EMAIL HANDLER : Admin user used is : " + jiraAdminUser.getUsername());

        String emailSubject = message.getSubject();
		
        if (isBlank(emailSubject)) {
            log.info("------------------Email message doesnt have any subject, nothing to do for this handler! Exiting ...");
            return true;
        }
        log.info("--------------------XMATTERS EMAIL HANDLER : ATTN : Processing a mail with the subject : " + emailSubject);
        String issueKey = emailParser.extractIssueKeyFromMailSubject(emailSubject);
        // log.info("--------------EmailSubject after parsed for issue key is --------:"+issueKey);
        //If issue key itself is absent, exit the handler, as there is nothing to process
        if (isBlank(issueKey)) {
            log.info("--------------------------Cannot find the issue key in the mail subject, handler will exit");
            return true;
        }

        log.info("----------------------XMATTERS EMAIL HANDLER : Issue Key extracted is : " + issueKey);

        //xMatters Technical Conference Bridge Number
        //xMatters Management Conference Bridge Number
        String xMattersMailBody = MailUtils.getBody(message);
        Map<String, String> emailMap = emailParser.parseAndReturnConfBridgeTypeAndNumbers(xMattersMailBody);
        updateIncidentWithBridgeNumbers(emailMap, issueKey, messageHandlerContext);

        return true;
    }

    private void updateIncidentWithBridgeNumbers(Map<String, String> emailMap, String issueKey,
                                                 MessageHandlerContext messageHandlerContext) {
        final IssueService.IssueResult issueResult = issueService.getIssue(jiraAdminUser, issueKey);
        final MutableIssue mutableIssue = issueResult.getIssue();

        //If issue object is not found, no need to process further.
        if (mutableIssue == null) {
            log.info("-----------------------Couldnt find issue with the key " + issueKey + ", handler will exit");
            return;
        }
        log.info("------------------------Found issue with key " + issueKey + ", proceeding to update bridge numbers");
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        CustomField techConfBridgeNumberCF = cfm.getCustomFieldObjectByName(TECH_CONF_BRIDGE_NUMBER);
        CustomField mgmtConfBridgeNumberCF = cfm.getCustomFieldObjectByName(MGMT_CONF_BRIDGE_NUMBER);
        String techConfBridgeNumber = (String)mutableIssue.getCustomFieldValue(techConfBridgeNumberCF);
        String mgmtConfBridgeNumber = (String)mutableIssue.getCustomFieldValue(mgmtConfBridgeNumberCF);

        String bridgeTypeFromHashMap = emailMap.get("Bridge Type");
        log.info("-----------------------XMATTERS EMAIL HANDLER : Bridge type from hash map is " + bridgeTypeFromHashMap);
        String bridgeDetailsFromHashMap = emailMap.get("Bridge Details");
        log.info("--------------------XMATTERS EMAIL HANDLER : Bridge details from hash map is " + bridgeDetailsFromHashMap);
		log.info("**********Add the null check for bridgeTypeFromHashMap 1 ********");
		log.info("**********Add the null check for bridgeTypeFromHashMap  2 ********");
        if (isBlank(techConfBridgeNumber) && (bridgeTypeFromHashMap!=null && bridgeTypeFromHashMap.equals("Technical")) &&
                isNotBlank(bridgeDetailsFromHashMap)) {
            //update the tech conference bridge details in the incident
            log.info("--------------------XMATTERS EMAIL HANDLER : Found the field " + TECH_CONF_BRIDGE_NUMBER + " blank for the issue "
            + issueKey + "! Proceeding to update it!");
            updateConferenceBridgeNumberForIncident(mutableIssue, techConfBridgeNumberCF, emailMap, messageHandlerContext);
        }

        if (isBlank(mgmtConfBridgeNumber) && (bridgeTypeFromHashMap!=null && bridgeTypeFromHashMap.equals("Management")) &&
                isNotBlank(bridgeDetailsFromHashMap)) {
            //update the mgmt conference bridge details in the incident
            log.info("--------------------------XMATTERS EMAIL HANDLER : Found the field " + MGMT_CONF_BRIDGE_NUMBER + " blank for the issue "
                    + issueKey + "! Proceeding to update it!");
            updateConferenceBridgeNumberForIncident(mutableIssue, mgmtConfBridgeNumberCF, emailMap, messageHandlerContext);

        }


    }


    private void updateConferenceBridgeNumberForIncident(MutableIssue mutableIssue,
                                                         CustomField confBridgeNumberCF,
                                                         Map<String, String> emailMap,
                                                         MessageHandlerContext messageHandlerContext) {
        IssueInputParameters issueInputParameters = issueService.newIssueInputParameters();
        String bridgeDetails = emailMap.get("Bridge Details");
        issueInputParameters.addCustomFieldValue(confBridgeNumberCF.getId(), bridgeDetails);
        IssueService.UpdateValidationResult updateValidationResult = issueService.validateUpdate(jiraAdminUser,
                mutableIssue.getId(), issueInputParameters);

        if (updateValidationResult.isValid()) {
            IssueService.IssueResult updateResult = issueService.update(jiraAdminUser, updateValidationResult);
            if (!updateResult.isValid()) {
                log.info("----------------------------XMATTERS EMAIL HANDLER - update result invalid :" + updateResult.getErrorCollection());
            } else {
                log.info("----------------------XMATTERS EMAIL HANDLER : Successfully updated the field " + confBridgeNumberCF.getName() +
                        " with the value : " + mutableIssue.getCustomFieldValue(confBridgeNumberCF));

                //Add a comment in the incident ticket that the concerned conf bridge field is updated.
                String commentMessage = "Updated the field " + confBridgeNumberCF.getName() + " with the following : \n" +
                        bridgeDetails;
                messageHandlerContext.createComment(mutableIssue,jiraAdminUser, commentMessage, false);
            }
        } else {
            log.info("------------------------XMATTERS EMAIL HANDLER : Could not update the field " + confBridgeNumberCF.getName() +
                    " for the Issue " + mutableIssue.getKey()+ "! ERROR : " +
                    updateValidationResult.getErrorCollection());
        }

    }

}
