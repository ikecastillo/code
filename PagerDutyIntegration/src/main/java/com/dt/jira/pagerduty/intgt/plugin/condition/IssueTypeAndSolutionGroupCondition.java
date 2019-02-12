package com.dt.jira.pagerduty.intgt.plugin.condition;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.plugin.webfragment.conditions.AbstractJiraCondition;
import com.atlassian.jira.plugin.webfragment.model.JiraHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atlassian.jira.security.PermissionManager;
import com.atlassian.jira.security.Permissions;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.user.ApplicationUser;

/*
   This class defined to apply the security on "Create xMatters Event" button on issue page
**/
public class IssueTypeAndSolutionGroupCondition extends AbstractJiraCondition {

	@Override
	public boolean shouldDisplay(ApplicationUser user, JiraHelper jiraHelper) {
	 ProjectRoleManager prm = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);
	 PermissionManager permissionManager = ComponentAccessor.getPermissionManager();	 
		 final Issue currentIssue = (Issue)jiraHelper.getContextParams().get("issue");		
		 String IssueTypeName = currentIssue.getIssueTypeObject().getName();		 
		  String statusName = currentIssue.getStatusObject().getName();
		  boolean userCanEditIssue = permissionManager.hasPermission(Permissions.EDIT_ISSUE,currentIssue, user);
		CustomField solnGrpCF = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Solution Groups - Products");
		List<String> solngroupprodValues = getMultiLevelCascadingSelectValue(currentIssue,solnGrpCF);
		String solutionGroupValue = "";
		if (solngroupprodValues != null && !solngroupprodValues.isEmpty()) {
			solutionGroupValue = solngroupprodValues.get(0);
		}

		CustomField clientImpactedCF = ComponentAccessor.getCustomFieldManager().getCustomFieldObjectByName("Clients Impacted");
		List<String> clientsImpactedVal = getCascadeSelectValue(currentIssue,clientImpactedCF);
		String clientsImpacted = clientsImpactedVal.get(0);
		String location = clientsImpactedVal.get(1);

		//Either one of the below conditions has to be true
		boolean externalDDCCondition = clientsImpacted.equals("External") && solutionGroupValue.equalsIgnoreCase("DDC");
		boolean internalBurlingtonCondition = clientsImpacted.equals("Internal") && location.equalsIgnoreCase("Burlington");

		 if((IssueTypeName.equalsIgnoreCase("Incident") || IssueTypeName.equalsIgnoreCase("Outage")) &&
				 !statusName.equalsIgnoreCase("Assigned") &&
				 ( externalDDCCondition || internalBurlingtonCondition ) &&
				 ( prm.isUserInProjectRole(user, prm.getProjectRole("NOC"), currentIssue.getProjectObject()) ||
		 prm.isUserInProjectRole(user, prm.getProjectRole("Incident Manager"), currentIssue.getProjectObject())||
		 prm.isUserInProjectRole(user, prm.getProjectRole("Administrators"), currentIssue.getProjectObject()) ||
		 userCanEditIssue)){
		     return true;
		 }else{
			 return false;
		 }
	}

	/**
	 * Helper method to deal with multilevel cascade field. Note, as of now, only the Solution Group Product - Category
	 * is a multilevel cascade field
	 *
	 * @param issue
	 * @param customField
	 * @return list of values for multiselect cascade field , out of which we need to pick only first two
	 */
	private List<String> getMultiLevelCascadingSelectValue(Issue issue, CustomField customField) {
		List<String> values = new ArrayList<>();
		List valuesList = (ArrayList)issue.getCustomFieldValue(customField);

		//Add only first two values are they are not supported in xMatters as of now
		if (valuesList != null) {
			for (Object value : valuesList) {
				values.add(value.toString());
			}

			//For now if by any chance the elements are present at third and fourth level of cascade, then remove them.
			if (values.size() > 2) {
				for (int i = 2 ; i < values.size() ; i++) {
					values.remove(i);
				}
			}
		}


		return values;
	}

	/**
	 * Get the values for custom field type is cascade select
	 * @param issue
	 * @param customField
	 * @return list of value(s) for cascade field - its size would always be > 1
	 */
	private List<String> getCascadeSelectValue(Issue issue, CustomField customField){
		String firtsDDValue = "";
		String secondDDValue = "";
		List<String> values = new ArrayList<>();
		Map<LazyLoadedOption, LazyLoadedOption> solutionGroupMap = (HashMap<LazyLoadedOption, LazyLoadedOption>)
				issue.getCustomFieldValue(customField);
		if(solutionGroupMap!=null){
			for(Map.Entry<LazyLoadedOption, LazyLoadedOption> opt : solutionGroupMap.entrySet()) {
				LazyLoadedOption llo = null;
				if(opt.getKey() ==  null ){ // for first dropdown
					llo = opt.getValue();
					firtsDDValue = String.valueOf(llo.getValue());
				}
				if(opt.getKey() !=  null ){ //for second dropdown
					llo = opt.getValue();
					secondDDValue = String.valueOf(llo.getValue());
				}
			}
		}
		values.add(firtsDDValue);
		values.add(secondDDValue);
		return values;
	}
}
