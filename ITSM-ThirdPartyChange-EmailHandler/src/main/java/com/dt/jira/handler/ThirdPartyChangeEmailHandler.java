package com.dt.jira.handler;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.IssueInputParameters;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.service.util.handler.MessageHandler;
import com.atlassian.jira.service.util.handler.MessageHandlerContext;
import com.atlassian.jira.service.util.handler.MessageHandlerErrorCollector;
import com.atlassian.jira.service.util.handler.MessageUserProcessor;
import com.atlassian.mail.MailUtils;
import org.apache.commons.lang.StringUtils;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.mail.Message;
import javax.mail.MessagingException;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.Option;
import com.dt.jira.utils.PropertyUtils;
import com.atlassian.jira.util.ErrorCollection;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.watchers.WatcherManager;

/**
 * The Java class corresponding to the functionality of Third Party Change Email Handler.
 * Please refer the atlassian tutorials for writing custom email handler for details on the basics.
 *
 * Created by Yagnesh.Bhat on 10/13/2015.
 */
public class ThirdPartyChangeEmailHandler implements MessageHandler{

    private static final Logger log = LoggerFactory.getLogger(ThirdPartyChangeEmailHandler.class);
    private final MessageUserProcessor messageUserProcessor;
    private final OptionsManager optionsManager;

    private String defaultProjectKey = null;  // default project where new issues are created
    private String defaultIssueTypeId = null;   // default type for new issues - this is basically the issue type id
    private String defaultSolutionGrpId = null; //default solution group id
    private String defaultImpacted = null; //default impacted from Impacted - function
    private String defaultFunction = null; //default function from Impacted - function
    private String defaultEnvironment = null; //default environment
    private String defaultChangeType = null; //default Change Type
    private String defaultRiskId = null;
    private String defaultPriorityId = null;
    private String defaultRollBackDesc = "TBD"; //by default it will be like this
    private String defaultDepartment = "Third Party Change"; //default value for this handler
    private String defaultManagerName = null;
    private String defaultWatchers = null;
    private String defaultHandlerParams = null;

    // Parameter names of the handler
    private static final String KEY_PROJECT = "project";
    private static final String KEY_ISSUETYPE = "issuetype";
    private static final String KEY_SOLNGRP = "solutiongroup";
    private static final String KEY_IMPACTED = "impacted";
    private static final String KEY_FUNCTION = "function";
    private static final String KEY_ENVIRONMENT = "environment";
    private static final String KEY_CHANGE_TYPE = "changetype";
    private static final String KEY_RISK = "risk";
    private static final String KEY_PRIORITY = "priority";
    private static final String KEY_MANAGERNAME = "managername";
    private static final String PROJECT_KEY_CHG="CHG";
    public static final String KEY_WATCHERS="watchers";
    public static final String KEY_CUSTOM_HANDLER_PARAMS="handlerparams";
    public static final String KEY_HANDLERPARAMS="testparams";

    public ThirdPartyChangeEmailHandler(MessageUserProcessor messageUserProcessor,OptionsManager optionsManager) {
        this.messageUserProcessor = messageUserProcessor;
        this.optionsManager=optionsManager;
    }

    /**
     * Empty Check
     * @param text
     * @return
     */
    private Boolean emptyCheck(String text) {
        if (text == null || text.isEmpty()) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public void init(Map<String, String> params, MessageHandlerErrorCollector monitor) {
        log.debug("ThirdPartyChangeEmailHandler.init(params: " + params + ")");

        if (params.containsKey(KEY_PROJECT)) {
            defaultProjectKey = params.get(KEY_PROJECT);
        }  else {
            log.error("Project Key is mandatory");
        }

        if (params.containsKey(KEY_ISSUETYPE)) {
            defaultIssueTypeId = params.get(KEY_ISSUETYPE);

        } else {
            log.error("Issue Type ID is mandatory");
        }

        if (params.containsKey(KEY_SOLNGRP)) {
            defaultSolutionGrpId = params.get(KEY_SOLNGRP);

        } else {
            log.error("Solution Group ID is mandatory");
        }

        if (params.containsKey(KEY_IMPACTED)) {
            defaultImpacted = params.get(KEY_IMPACTED);

        } else {
            log.error("Impacted function is mandatory");
        }

        if (params.containsKey(KEY_FUNCTION)) {
            defaultFunction = params.get(KEY_FUNCTION);


        } else {
            log.error("Impacted function is mandatory");
        }

        if (params.containsKey(KEY_ENVIRONMENT)) {
            defaultEnvironment = params.get(KEY_ENVIRONMENT);

        } else {
            log.error("Environment is mandatory");
        }

        if (params.containsKey(KEY_CHANGE_TYPE)) {
            defaultChangeType = params.get(KEY_CHANGE_TYPE);

        } else {
            log.error("Change Type is mandatory");
        }

        if (params.containsKey(KEY_RISK)) {
            defaultRiskId = params.get(KEY_RISK);

        } else {
            log.error("Risk is mandatory");
        }

        if (params.containsKey(KEY_PRIORITY)) {
            defaultPriorityId = params.get(KEY_PRIORITY);

        } else {
            log.error("Priority is mandatory");
        }

        if (params.containsKey(KEY_MANAGERNAME)) {
            defaultManagerName = params.get(KEY_MANAGERNAME);

        } else {
            log.error("Manager Name is mandatory");
        }

        if (params.containsKey(KEY_WATCHERS)) {
            defaultWatchers = params.get(KEY_WATCHERS);

        } else {
            log.error("Manager Name is mandatory");
        }


        if (params.containsKey(KEY_CUSTOM_HANDLER_PARAMS)) {
        	defaultHandlerParams = params.get(KEY_CUSTOM_HANDLER_PARAMS);

        } else {
            log.error("hANDLERS Name is mandatory");
        }

        log.debug("Params: " + defaultProjectKey + " - " + defaultIssueTypeId + "-" + defaultSolutionGrpId + "-" +
                "-" + defaultImpacted + "-" + defaultEnvironment + "-" + defaultChangeType + "-" + defaultPriorityId +
                "-" + defaultWatchers+ "_" + defaultHandlerParams);
    }


    /**
     * Adds users to watchers List of an issue.
     * @param watchersList
     * @param issueParentObj
     */
    private void addingWatcherToIssue(String[] watchersList,MutableIssue issueParentObj){
        for (String watcher : watchersList) {
            if(watcher!=null && (!watcher.isEmpty())){
                ApplicationUser user = ComponentAccessor.getUserManager().getUserByName(watcher.trim());
                if(user!=null){
                    WatcherManager wm = ComponentAccessor.getWatcherManager();
                    wm.startWatching(user, issueParentObj);
                }
            }
        }
    }

    @Override
    public boolean handleMessage(Message message, MessageHandlerContext context) throws MessagingException {

        // this is a small util method JIRA API provides for us, let's use it.
        final ApplicationUser sender = messageUserProcessor.getAuthorFromSender(message);
        if (sender == null) {
            context.getMonitor().error("Message sender(s) '" + StringUtils.join(MailUtils.getSenders(message), ",")
                    + "' do not have corresponding users in JIRA. Message will be ignored");
            return false;
        }

        //Issue description will be the message body
        final String issueDescription = MailUtils.getBody(message);

        //Issue summary will be the message subject
        final String issueSummary = message.getSubject();

        IssueService issueService = ComponentAccessor.getIssueService();

        IssueInputParameters issueIP = issueService.newIssueInputParameters();

        JiraAuthenticationContext jAC = ComponentAccessor.getJiraAuthenticationContext();

        //need one standard user to set
        ApplicationUser jiraAdminUser  = ComponentAccessor.getUserManager().getUser("dtjira.admin");
        jAC.setLoggedInUser(jiraAdminUser);

        Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(defaultProjectKey);
        Long lObj = new Long(project.getId());// ESTABLISH PROJECT ID AS A LONG

        //Set the required System fields
        issueIP.setProjectId(lObj);     // SET PROJECT ID
        issueIP.setSummary(issueSummary);// SETS SUMMARY
        issueIP.setIssueTypeId(defaultIssueTypeId);// SETS ISSUE TYPE
        issueIP.setDescription(issueDescription);// SETS ISSUE DESCRIPTION
        issueIP.setReporterId(sender.getName()); //Sets reporter to the sender of the email
        issueIP.setPriorityId(defaultPriorityId);
        issueIP.setAssigneeId(jiraAdminUser.getName());

        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        CustomField sgpCF = cfm.getCustomFieldObjectByName("Solution Group - Product");
        CustomField impactedFnCF = cfm.getCustomFieldObjectByName("Impacted - Function");
        CustomField environmentCF = cfm.getCustomFieldObjectByName("Environment");
        CustomField changeTypeCF = cfm.getCustomFieldObjectByName("Change Type");
        CustomField riskTypeCF = cfm.getCustomFieldObjectByName("Risk");
        CustomField reporterEmailCF = cfm.getCustomFieldObjectByName("Reporter's Contact Email");
        CustomField reporterDepartmentCF = cfm.getCustomFieldObjectByName("Reporter's Department");
        CustomField managersNameCF = cfm.getCustomFieldObjectByName("Manager's Name");
        CustomField rollbackDescCF = cfm.getCustomFieldObjectByName("Roll Back Description");

        CustomField reqStartDateCF = cfm.getCustomFieldObjectByName("Requested Start Date");
        CustomField reqEndDateCF = cfm.getCustomFieldObjectByName("Requested End Date");
		
		/* Risk Questions*/
        /*CustomField question1 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("RISK.QUESTION1"));
        CustomField question2 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("RISK.QUESTION2"));
        CustomField question3 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("RISK.QUESTION3"));
        CustomField question4 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("RISK.QUESTION4"));


        CustomField rollbackQuestion1 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION1"));
        CustomField rollbackQuestion2 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION2"));
        CustomField rollbackQuestion3 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION3"));
        CustomField rollbackQuestion4 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION4"));
        CustomField rollbackQuestion5 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION5"));
        CustomField rollbackQuestion6 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION6"));
        CustomField rollbackQuestion7 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION7"));
        CustomField rollbackQuestion8 = cfm.getCustomFieldObjectByName(PropertyUtils.getPropertyValue("ROLLBACKPLAN.QUESTION8"));*/



        log.debug("Manager name is " + defaultManagerName);

        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("MMM dd, YYYY hh:mm aa");

        String rollbackDefaultValue=PropertyUtils.getPropertyValue((defaultProjectKey.toUpperCase())+"."+"ROLLBACK_DEFAULT");

        issueIP.addCustomFieldValue(reqStartDateCF.getId(), getFormattedRequestStartDate(date,fmt))
                .addCustomFieldValue(reqEndDateCF.getId(), getFormattedRequestEndDate(date,fmt))
                .addCustomFieldValue(sgpCF.getId(), defaultSolutionGrpId)
                .addCustomFieldValue(impactedFnCF.getId(), defaultImpacted)
                .addCustomFieldValue(impactedFnCF.getId() + ":1", defaultFunction)
                .addCustomFieldValue(changeTypeCF.getId(), defaultChangeType)
                .addCustomFieldValue(environmentCF.getId(), defaultEnvironment)
                .addCustomFieldValue(riskTypeCF.getId(), defaultRiskId)
                .addCustomFieldValue(reporterEmailCF.getId(),sender.getEmailAddress())
                .addCustomFieldValue(reporterDepartmentCF.getId(),defaultDepartment)
                .addCustomFieldValue(managersNameCF.getId(),defaultManagerName);
                /*.addCustomFieldValue(question1.getId(), PropertyUtils.getPropertyValue(getPropertiesKey(defaultProjectKey,PropertyUtils.getPropertyValue("RISK.QUESTION1"))))
                .addCustomFieldValue(question2.getId(), PropertyUtils.getPropertyValue(getPropertiesKey(defaultProjectKey,PropertyUtils.getPropertyValue("RISK.QUESTION2"))))
                .addCustomFieldValue(question3.getId(), PropertyUtils.getPropertyValue(getPropertiesKey(defaultProjectKey,PropertyUtils.getPropertyValue("RISK.QUESTION3"))))
                .addCustomFieldValue(question4.getId(), PropertyUtils.getPropertyValue(getPropertiesKey(defaultProjectKey,PropertyUtils.getPropertyValue("RISK.QUESTION4"))))
                .addCustomFieldValue(rollbackQuestion1.getId(), rollbackDefaultValue)
                .addCustomFieldValue(rollbackQuestion2.getId(), sender.getName())
                .addCustomFieldValue(rollbackQuestion3.getId(), rollbackDefaultValue)
                .addCustomFieldValue(rollbackQuestion4.getId(), rollbackDefaultValue)
                .addCustomFieldValue(rollbackQuestion5.getId(), rollbackDefaultValue)
                .addCustomFieldValue(rollbackQuestion6.getId(), rollbackDefaultValue)
                .addCustomFieldValue(rollbackQuestion7.getId(), rollbackDefaultValue)
                .addCustomFieldValue(rollbackQuestion8.getId(), rollbackDefaultValue);*/







        IssueService.CreateValidationResult issue;

        issue = issueService.validateCreate(jAC.getLoggedInUser(), issueIP);// GETS THE CreateValidationResult OBJECT
        log.debug("Any errors while creating issue? " + issue.getErrorCollection());
        // PASSES THE CreateValidationResult OBJECT AND THE user INTO TO CREATE ISSUE STATEMENT, WHICH SHOULD CREATE THE ISSUE.
        IssueService.IssueResult iResult = issueService.create(jAC.getLoggedInUser(), issue);
        log.debug("0------------------"+iResult.getIssue());
        log.debug("Any errors ? :" + iResult.getErrorCollection());
        /* watchers */
        String[] watcherList=defaultWatchers.split(";");
        addingWatcherToIssue(watcherList,(MutableIssue)iResult.getIssue());
        // returning true means that we have handled the message successfully. It means it will be deleted next.
        return true;
    }

    /**
     * get CustomFeild ID By PropertyKey
     * @param key
     * @return
     */
   /* private String getCustomFeildByPropertyKey(String key){
    	CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
    	String customFieldId=null;
    	if(emptyCheck(key)){
    		String fieldName=key.replace("_", " ");	
    		if(emptyCheck(fieldName)){
    			CustomField customField=cfm.getCustomFieldObjectByName(fieldName);
    			if(customField!=null){
    				customFieldId=customField.getId();	
    			}
    			
    		}
    	}
    	return customFieldId;    	
    }*/

    /**
     * This removes empty spaces with under score and to upper case
     * this key passes to properties file.
     * @param projectKey
     * @param fieldName
     * @return
     */
    private String getPropertiesKey(String projectKey,String fieldName){
        String keyWithUnderscore=null;
        if(emptyCheck(projectKey) && emptyCheck(fieldName)){
            String keyWithSpace=(projectKey+"."+fieldName).toUpperCase();
            if(emptyCheck(keyWithSpace)){
                keyWithUnderscore=keyWithSpace.replace(" ", "_");
            }
        }
        return keyWithUnderscore;
    }


    /**
     * Helper method to get the requested start date as a string
     * @param date
     * @param fmt
     * @return Requested Start Date Formatted as String
     */
    private String getFormattedRequestStartDate(LocalDateTime date, DateTimeFormatter fmt) {
        date = date.plusHours(2); //Add two hours to current date time
        log.debug("Formatted Req Start date is " + date.toString(fmt));
        return date.toString(fmt);
    }

    /**
     * Helper method to get the requested end date as a string
     *
     * @param date
     * @param fmt
     * @return Requested End Date formatted as String
     */
    private String getFormattedRequestEndDate(LocalDateTime date, DateTimeFormatter fmt){
        date = date.plusHours(3); //add three hours to current date time
        log.debug("Formatted Req End Date is " + date.toString(fmt) );
        return date.toString(fmt);
    }
}
