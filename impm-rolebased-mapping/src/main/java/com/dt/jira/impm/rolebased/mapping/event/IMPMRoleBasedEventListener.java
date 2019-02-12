package com.dt.jira.impm.rolebased.mapping.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.database.DbConnectionManager;
import com.atlassian.jira.issue.index.IssueIndexManager;
import org.ofbiz.core.entity.GenericEntityException;
import org.ofbiz.core.entity.GenericValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import com.atlassian.core.util.map.EasyMap;
import com.atlassian.crowd.embedded.api.Group;
import com.atlassian.event.api.EventListener;
import com.atlassian.event.api.EventPublisher;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.event.issue.IssueEvent;
import com.atlassian.jira.event.type.EventType;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.ModifiedValue;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutStorageException;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.util.DefaultIssueChangeHolder;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.security.roles.OfBizProjectRoleAndActorStore;
import com.atlassian.jira.security.roles.ProjectRole;
import com.atlassian.jira.security.roles.ProjectRoleActors;
import com.atlassian.jira.security.roles.ProjectRoleManager;
import com.atlassian.jira.security.roles.RoleActor;
import com.atlassian.jira.security.roles.RoleActorFactory;
import com.atlassian.jira.security.roles.actor.UserRoleActorFactory;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.sal.api.ApplicationProperties;
import com.dt.jira.impm.rolebased.mapping.ao.IMPMRoleBased;
import com.dt.jira.impm.rolebased.mapping.service.IMPMRoleBasedMappingService;
import com.dt.jira.impm.rolebased.mapping.utils.IMPMRoleBasedGroupUtils;

/**
 * IMPMRoleBasedEventListener.java event listener class for populating
 * Multi User ,Multi Group custom feild based selected Solution Group products.
 * 
 * @author Srinadh.Garlapati & Firoz.Khan
 * @version 1.0.0
 */
public class IMPMRoleBasedEventListener implements InitializingBean,DisposableBean {
	private static final Logger log = LoggerFactory.getLogger(IMPMRoleBasedEventListener.class);
	private final EventPublisher publisher;
	private final JiraAuthenticationContext authenticationContext;
	private final ApplicationProperties applicationProperties;
	private final IMPMRoleBasedMappingService roleBasedService;
    private static String descriptionVal = "";
	/**
	 * Constructor
	 * 
	 * @param publisher
	 *            the EventPublisher to be injected
	 * @param authenticationContext
	 *            the JiraAuthenticationContext to be injected
	 * @param applicationProperties
	 *            the ApplicationProperties to be injected
	 */
	public IMPMRoleBasedEventListener(EventPublisher publisher,
			JiraAuthenticationContext authenticationContext,
			ApplicationProperties applicationProperties,
			IMPMRoleBasedMappingService roleBasedService) {
		this.publisher = publisher;
		this.authenticationContext = authenticationContext;
		this.applicationProperties = applicationProperties;
		this.roleBasedService = roleBasedService;
	}
	/**
	 * Verify Desription field is Modified or not. 
	 *
	 * @return boolean
	 */
	private boolean isSummaryModified(IssueEvent event){
	Issue issueParent = event.getIssue();
	IssueType issueType=issueParent.getIssueTypeObject();
	Boolean flag = Boolean.FALSE;
	log.info("isDescription******* modified:"+issueType.getName()); 
	String summary = issueParent.getSummary();
	if(summary!=null){
	flag = Boolean.TRUE;
	log.info("----------------------Summary is :"+summary);
	}
	return flag;
	}

	/**
	 * Verify Solution group or Summary field is modified . 
	 *
	 * @return String
	 */
    private String fieldModified(IssueEvent event) {
    	
    	Issue issueParent = event.getIssue();
    	IssueType issueType=issueParent.getIssueTypeObject();
    	//Boolean flag = Boolean.FALSE;
		String fieldName = "";
    	log.info("isSolutonGroupModified Issue Type******************"+issueType.getName()); 
		if(issueType.getName().equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_PROBLEM) || issueType.getName().equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_INCIDENT)){
            List<GenericValue> changeItems = null;
            try {
                GenericValue changeLog = event.getChangeLog();
                changeItems = changeLog.internalDelegator.findByAnd("ChangeItem", EasyMap.build("group",changeLog.get("id")));
                for (Iterator<GenericValue> iterator = changeItems.iterator(); iterator.hasNext();){
	                GenericValue changetemp = (GenericValue) iterator.next();
					/* Update the solution group details from issue */	                
	                String field = changetemp.getString("field");
	                String oldValue = changetemp.getString("oldstring");
	                String newValue = changetemp.getString("newstring");
					log.info("-----------the value changed are:field="+field+"--,-oldValue="+oldValue+"--newValue="+newValue);
	                StringBuilder updateMessage = new StringBuilder();
	                updateMessage.append("Issue ");
	                updateMessage.append(issueParent.getKey());
	                updateMessage.append(" field ");
	                updateMessage.append(field);
	                updateMessage.append(" has been updated from ");
	                updateMessage.append(oldValue);
	                updateMessage.append(" to ");
	                updateMessage.append(newValue);
	                
	                if(field.equalsIgnoreCase(IMPMRoleBasedGroupUtils.SOLUTION_GROUPS_PRODUCTS_FIELD)){
	                	log.info("Updated Field Name******************"+updateMessage); 
	                	fieldName = IMPMRoleBasedGroupUtils.SOLUTION_GROUPS_PRODUCTS_FIELD;
	                }
					if(field.equalsIgnoreCase(IMPMRoleBasedGroupUtils.DESCRIPTION_FIELD) && !(oldValue.equalsIgnoreCase(newValue))){
					    log.info("Updated Field Name----"+updateMessage);
						descriptionVal = newValue;
						fieldName = IMPMRoleBasedGroupUtils.DESCRIPTION_FIELD;
					}
	            }
            } catch (GenericEntityException e){
                e.getMessage();
            }
         }	
		return fieldName;
      }

	/**
	 * event for loading Multi User and Multi Group custom Componet on editing
	 * Solution Group - products.
	 * 
	 * @param event
	 */
    @EventListener
	public void onIssueEvent(IssueEvent event) {		
		Long eventTypeId = event.getEventTypeId();
		log.info("--eventTypeId------------------------"+eventTypeId);
		log.info("--EventType.ISSUE_GENERICEVENT_ID----"+EventType.ISSUE_GENERICEVENT_ID);
		log.info("--EventType.ISSUE_CREATED_ID---------"+EventType.ISSUE_CREATED_ID);
		log.info("--EventType.ISSUE_UPDATED_ID---------"+EventType.ISSUE_UPDATED_ID);
		if (eventTypeId.equals(EventType.ISSUE_GENERICEVENT_ID)) {
			assignIncidentAndProblemRolesFromMappingOnCreate(event);				
		}else if (eventTypeId.equals(EventType.ISSUE_CREATED_ID)) {
			assignIncidentAndProblemRolesFromMappingOnCreate(event);
		}else if (eventTypeId.equals(EventType.ISSUE_UPDATED_ID)) {
			assignIncidentAndProblemRolesFromMappingOnUpdate(event);
		}
	}
    
	
	/**
     * Assign Incident and Problem roles and group from mapping on issue creation 
     * @param event the IssueEvent
     */
    private void assignIncidentAndProblemRolesFromMappingOnCreate(IssueEvent event){
    		   	
		try{
			final MutableIssue issueParent = (MutableIssue) event.getIssue(); 
			String issueTypeName = issueParent.getIssueTypeObject().getName();
			log.info("--issueTypeName on create ----"+issueTypeName);
			if (issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_PROBLEM)
					|| issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_RCA_REPORT_SUBTASK)
					|| issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_INCIDENT)
					|| issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK)) {
				String projectId = issueParent.getProjectObject().getKey();
				log.info("--projectId----"+issueParent.getProjectObject());
				
				Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectId);
				RoleActorFactory roleActorFactory = new UserRoleActorFactory(ComponentAccessor.getUserManager(), ComponentManager.getComponent(DbConnectionManager.class));
				OfBizProjectRoleAndActorStore obBizPrjRlActore = new OfBizProjectRoleAndActorStore(ComponentAccessor.getOfBizDelegator(), roleActorFactory,ComponentAccessor.getGroupManager());
				CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
				CustomField custComp = cfm.getCustomFieldObjectByName(IMPMRoleBasedGroupUtils.SOLUTION_GROUPS_PRODUCTS_FIELD);					
				List<Option> solGrpList = (List<Option>) issueParent.getCustomFieldValue(custComp);
				
				log.info("The solution group list :"+solGrpList);
				
		       if (solGrpList != null && solGrpList.size()>0) {  
			   log.info("after null check solGrpList :"+solGrpList);
		           Option solGrp =  solGrpList.get(0);                    
                   if(solGrp!=null){
                   List<IMPMRoleBased> projectRoleList= roleBasedService.findProjectRoleBySolutionGroup(projectId,issueTypeName,solGrp.getValue());
                   
                   CustomField cf = cfm.getCustomFieldObjectByName(IMPMRoleBasedGroupUtils.CUSTOM_COMPONENT_MULTI_USER);
                   CustomField cf1 = cfm.getCustomFieldObjectByName(IMPMRoleBasedGroupUtils.CUSTOM_COMPONENT_MULTI_GROUP);
                   
                   List<ApplicationUser> appUserList = new ArrayList<ApplicationUser>();
				   			   
                   List<Group> groupsList = new ArrayList<Group>();
                   for(IMPMRoleBased projectRoles: projectRoleList){                   	
                   		ProjectRole projectRole =obBizPrjRlActore.getProjectRoleByName(projectRoles.getProjectRole());
		        		ProjectRoleActors projectRoleActor = obBizPrjRlActore.getProjectRoleActors(projectRole.getId(),project.getId());		        		
		        		appUserList.addAll(projectRoleActor.getApplicationUsers());  
		        		groupsList.addAll(getProjectGroupsByProjectRole(projectRoles.getProjectRole(), project));
                   }
                  
                   updateUsersFromMapping(event, appUserList, cf);
	               updateGroupsFromMapping(event, groupsList, cf1);
                  
					try {
						ComponentManager.getComponent(IssueIndexManager.class).reIndex(issueParent);

					} catch (Exception ie) {
						//ie.printStackTrace();
						log.info("Error occurs inside IMPMRoleBasedEventListener.assignIncidentAndProblemRolesFromMappingOnCreate on line number:205 . the cause is--"+ie.getMessage());
					}
					}
		       }
			}
       }catch(Exception ie){
    	   //ie.printStackTrace();
		   log.info("Error occurs inside IMPMRoleBasedEventListener.assignIncidentAndProblemRolesFromMappingOnCreate on line number:211 . the cause is--"+ie.getMessage());
       }
    }
    
	/**
     * Assign Incident and Problem roles and group from mapping on issue update. 
     * @param event the IssueEvent
     */
    private void assignIncidentAndProblemRolesFromMappingOnUpdate(IssueEvent event){
    	
    	 if (fieldModified(event)!=null && fieldModified(event).equalsIgnoreCase(IMPMRoleBasedGroupUtils.SOLUTION_GROUPS_PRODUCTS_FIELD)) {
    		 try{
    			 
    		 final MutableIssue issueParent = (MutableIssue) event.getIssue();
				String issueTypeName = issueParent.getIssueTypeObject().getName();
				log.info("--issueTypeName on update----"+issueTypeName);
				if (issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_PROBLEM)
						|| issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_RCA_REPORT_SUBTASK)
						|| issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_INCIDENT)
					    || issueTypeName.equalsIgnoreCase(IMPMRoleBasedGroupUtils.ISSUE_TYPE_INCIDENT_REPORT_SUBTASK)) {
					String projectId = issueParent.getProjectObject().getKey();
					log.info("--projectId----"+issueParent.getProjectObject());
					
					Project project = ComponentAccessor.getProjectManager().getProjectObjByKey(projectId);
					RoleActorFactory roleActorFactory = new UserRoleActorFactory(ComponentAccessor.getUserManager(), ComponentManager.getComponent(DbConnectionManager.class));
					OfBizProjectRoleAndActorStore obBizPrjRlActore = new OfBizProjectRoleAndActorStore(ComponentAccessor.getOfBizDelegator(), roleActorFactory,ComponentAccessor.getGroupManager());
					CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
					CustomField custComp = cfm.getCustomFieldObjectByName(IMPMRoleBasedGroupUtils.SOLUTION_GROUPS_PRODUCTS_FIELD);					
					List<Option> solGrpList = (List<Option>) issueParent.getCustomFieldValue(custComp);
					
	                if (solGrpList != null && solGrpList.size()>0) {  
	 		           Option solGrp =  solGrpList.get(0);                    
	                   if(solGrp!=null){
	                   List<IMPMRoleBased> projectRoleList= roleBasedService.findProjectRoleBySolutionGroup(projectId,issueTypeName,solGrp.getValue());
	                   CustomField cf = cfm.getCustomFieldObjectByName(IMPMRoleBasedGroupUtils.CUSTOM_COMPONENT_MULTI_USER);
	                   CustomField cf1 = cfm.getCustomFieldObjectByName(IMPMRoleBasedGroupUtils.CUSTOM_COMPONENT_MULTI_GROUP);
	                   
	                   List<ApplicationUser> appUserList = new ArrayList<ApplicationUser>();
	                   List<Group> groupsList = new ArrayList<Group>();
	                   for(IMPMRoleBased projectRoles: projectRoleList){
							ProjectRole projectRole =obBizPrjRlActore.getProjectRoleByName(projectRoles.getProjectRole());
							ProjectRoleActors projectRoleActor = obBizPrjRlActore.getProjectRoleActors(projectRole.getId(),project.getId());
							appUserList.addAll(projectRoleActor.getApplicationUsers());
							groupsList.addAll(getProjectGroupsByProjectRole(projectRoles.getProjectRole(), project));
	                   }
	                  
	                   updateUsersFromMapping(event, appUserList, cf);
		               updateGroupsFromMapping(event, groupsList, cf1);
		                
						try {
							//ComponentAccessor.getIssueIndexManager().reIndex(issueParent);
							ComponentManager.getComponent(IssueIndexManager.class).reIndex(issueParent);

						} catch (Exception ie) {
							//ie.printStackTrace();
							log.info("Error occurs inside IMPMRoleBasedEventListener.assignIncidentAndProblemRolesFromMappingOnUpdate on line number:266 . the cause is--"+ie.getMessage());
						}
						}
					}
				}
    	 }catch(Exception ie){
      	   //ie.printStackTrace();
		   log.info("Error occurs inside IMPMRoleBasedEventListener.assignIncidentAndProblemRolesFromMappingOnUpdate on line number:272 . the cause is--"+ie.getMessage());
         }
    	 }
		  if (fieldModified(event)!=null && fieldModified(event).equalsIgnoreCase(IMPMRoleBasedGroupUtils.DESCRIPTION_FIELD)) {
		    try{
			final MutableIssue issueParent = (MutableIssue) event.getIssue();
			CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
			CustomField custComp = cfm.getCustomFieldObjectByName(IMPMRoleBasedGroupUtils.XMATTERS_DESCRIPTION_FIELD);					
			//String desc = (String) issueParent.getCustomFieldValue(custComp);
			log.info("---new Description value to set to xmatters desc is:"+descriptionVal);
			updateXMattersDesc(event,custComp,descriptionVal);
			log.info("----Description field updated successfully----------------");
			}catch(Exception ie){
			 log.info("Error occurs inside IMPMRoleBasedEventListener.assignIncidentAndProblemRolesFromMappingOnUpdate on line number:313 . the cause is--"+ie.getMessage());
			}
		  }
    }
	/**
     * update Xmatters Description while updating description 
     * @param event the IssueEvent
	 * @param customField the CustomField
	 * @param customFieldValue  the CustomField value
     */
    public void updateXMattersDesc(IssueEvent event,CustomField customField,String customFieldValue) throws FieldLayoutStorageException {

	    log.info("-------------update Xmatters Description while updating description----------------");
	    final MutableIssue issueParent = (MutableIssue) event.getIssue();	    
	    issueParent.setCustomFieldValue(customField, customFieldValue);
		log.info("---custom field value is:--"+customFieldValue);
 	    Map<String, ModifiedValue> modifiedFields = issueParent.getModifiedFields();
 	    FieldLayoutItem fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(issueParent).getFieldLayoutItem(customField);
 	    DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
 	    final ModifiedValue modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
		
 	    customField.updateValue(fieldLayoutItem, issueParent, modifiedValue, issueChangeHolder);
		//descriptionVal = "";
		
		log.info("----------successfully modified xmatters description---------");
 	}
	
   
	/**
     * Update users from mapping if corresponding project roles exist based on solution group. 
     * @param event the IssueEvent
	 * @param userList the List<ApplicationUser>
	 * @param customField the CustomField
     */
    public void updateUsersFromMapping(IssueEvent event, List<ApplicationUser> userList,CustomField customField) throws FieldLayoutStorageException {

	    log.info("updateUsersFromMapping users");
	    final MutableIssue issueParent = (MutableIssue) event.getIssue();	    
	    issueParent.setCustomFieldValue(customField, userList);
 	    Map<String, ModifiedValue> modifiedFields = issueParent.getModifiedFields();
 	    FieldLayoutItem fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(issueParent).getFieldLayoutItem(customField);
 	    DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
 	    final ModifiedValue modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
 	    customField.updateValue(fieldLayoutItem, issueParent, modifiedValue, issueChangeHolder);
 	}
    
    /**
     * Update groups from mapping if corresponding project roles and groups exist based on solution group. 
     * @param event the IssueEvent
	 * @param groupsList the Collection<Group> 
	 * @param customField the CustomField
     */
    public void updateGroupsFromMapping(IssueEvent event,  Collection<Group> groupsList,CustomField customField) throws FieldLayoutStorageException {

   	    log.info("updateUsersFromMapping groups");
   	    final MutableIssue issueParent = (MutableIssue) event.getIssue();	    
   	    issueParent.setCustomFieldValue(customField, groupsList);
 	    Map<String, ModifiedValue> modifiedFields = issueParent.getModifiedFields();
 	    FieldLayoutItem fieldLayoutItem = ComponentAccessor.getFieldLayoutManager().getFieldLayout(issueParent).getFieldLayoutItem(customField);
 	    DefaultIssueChangeHolder issueChangeHolder = new DefaultIssueChangeHolder();
 	    final ModifiedValue modifiedValue = (ModifiedValue) modifiedFields.get(customField.getId());
 	    customField.updateValue(fieldLayoutItem, issueParent, modifiedValue, issueChangeHolder);
 	}

	/**
	 * ProjectGroups By ProjectRole.
	 * 
	 * @param projectRoleName
	 * @param project
	 * @return
	 */
	private Collection<Group> getProjectGroupsByProjectRole(String projectRoleName, Project project) {
		log.info("**** method_name ******getProjectGroupsByProjectRole :  and projectRoleName is :"+ projectRoleName + "and Project is" + project);
		// Create TreeMap to Store the Role-Group association. Note a role can have more than one group
		Map<String, Collection<Group>> projectGroups = new HashMap<String, Collection<Group>>();

		ProjectRoleManager projectRoleManager = ComponentAccessor.getComponentOfType(ProjectRoleManager.class);
		// Get all the project roles
		Collection<ProjectRole> projectRoles = projectRoleManager.getProjectRoles();

		// Iterate through each role and get the groups associated with the role
		for (ProjectRole projectRole : projectRoles) {
			// Get the role actors for the project role
			ProjectRoleActors roleActors = projectRoleManager.getProjectRoleActors(projectRole, project);

			// Create an iterator to grab all of the groups for this project role
			Iterator<RoleActor> roleActorIterator = roleActors.getRoleActors().iterator();

			// Create a collection of strings to store all of the group's roles
			// to put in the map
			Collection<Group> groupRoles = new ArrayList<Group>();

			// Iterate the role actors to get the groups
			while (roleActorIterator.hasNext()) {

				Group group = ComponentAccessor.getGroupManager().getGroup(roleActorIterator.next().getDescriptor());
				// Add the group by string name into collection
				groupRoles.add(group);

			}// END While			
			// Add that role, and the associated groups to that role into ourmap.
			projectGroups.put(projectRole.getName(), groupRoles);

		}
		return projectGroups.get(projectRoleName);
	}

	@Override
	public void destroy() throws Exception {
		this.publisher.unregister(this);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.publisher.register(this);

	}
}
