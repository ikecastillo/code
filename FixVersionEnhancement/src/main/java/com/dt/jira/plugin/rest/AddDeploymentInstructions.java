package com.dt.jira.plugin.rest;



import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.MutableIssue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.issuetype.IssueType;
import com.atlassian.jira.issue.search.SearchResults;
import com.atlassian.jira.project.Project;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.plugins.rest.common.security.AnonymousAllowed;
import com.dt.jira.plugin.utils.CommonService;


/**
 * Get Issue Types of a given Project
 */
@Path("/addDeloplymentInstructions")
public class AddDeploymentInstructions {
	CustomField cf_webservers= ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("Web Servers");
	CustomField cf_msmq= ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("MSMQ");
	CustomField cf_econservers= ComponentAccessor.getCustomFieldManager()
			.getCustomFieldObjectByName("eCon Servers");
		
	CustomField cf_lenderservers= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Lender Servers");
	
	CustomField cf_compliance= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Compliance");
	
	CustomField cf_calcinterface= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Calc Interface");
	
	CustomField cf_mqbridge= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("MQ Bridge");
	
	CustomField cf_sitecore= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Site Core");
	
	CustomField cf_dtwebserver= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("DT 2.0 Web Server");
	
	CustomField cf_WSOASever= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("WSOA Sever");
	
	CustomField cf_cbcluster= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("CB Cluster");
	
	CustomField cf_drswsoa= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("DRS WSOA");
	
	CustomField cf_drsmsmqfdvip= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("DRS MSMQFDVIP");
	
	CustomField cf_msmqcluster= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("MSMQ C Cluster");
	
	CustomField cf_otherservers= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Other Servers");
	
	CustomField cf_rollback= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Rollback procedure");
	
	CustomField cf_otherins= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Other instructions");
	
	CustomField cf_productioncommnts= ComponentAccessor.getCustomFieldManager()
	.getCustomFieldObjectByName("Production implementation comments");

@GET
@AnonymousAllowed
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public Response getMessage(@QueryParam("tickets") String alltickets) throws Exception{
	ArrayList<AddDeploymentInstructionsModel> listOfModels= new ArrayList<AddDeploymentInstructionsModel>();
	String ISSUE_TYPES = "Story, 'Technical Story', Defect, Requirement,'Performance Testing'";
	List<Issue> listOfIssues = null;
	 double totValidDef = 0;
	 String[] tickets = null;
	try{
		
		System.out.println("All tickets "+ alltickets);
		if(alltickets!=null && alltickets.length()> 0) {
			tickets = alltickets.split(",");
		}
		StringBuffer webserversbuffer = new StringBuffer();
		StringBuffer msmqbuffer = new StringBuffer();
		StringBuffer econserversbuffer = new StringBuffer();
		StringBuffer lenderserversbuffer = new StringBuffer();
		StringBuffer compliancebuffer = new StringBuffer();
		StringBuffer calinterfacebuffer = new StringBuffer();
		StringBuffer mqbridgebuffer = new StringBuffer();
		StringBuffer sitecorebuffer = new StringBuffer();
		StringBuffer dtwebserverbuffer = new StringBuffer();
		StringBuffer WSOASeverbuffer = new StringBuffer();
		StringBuffer cbclusterbuffer = new StringBuffer();
		StringBuffer drswsoabuffer = new StringBuffer();
		StringBuffer drsmsmqfdvipbuffer = new StringBuffer();
		StringBuffer msmqclusterbuffer = new StringBuffer();
		StringBuffer otherserversbuffer = new StringBuffer();
		StringBuffer rollbackbuffer = new StringBuffer();
		StringBuffer otherinsbuffer = new StringBuffer();
		StringBuffer prodcommentsbuffer = new StringBuffer();
		
		for(int i=0;i<tickets.length; i++){
			System.out.println(tickets[i]);
			IssueManager issueManager = ComponentAccessor.getIssueManager();
			MutableIssue issue  = issueManager.getIssueObject(tickets[i]);
			// for Web Servers field
			String webservers = (String)issue.getCustomFieldValue(cf_webservers);
			if(webservers!=null && !webservers.trim().equalsIgnoreCase("")){
				webserversbuffer.append(tickets[i]+"\n");
				webserversbuffer.append(webservers);
				webserversbuffer.append("\n**********************************************************************\n");
			} else {
				webserversbuffer.append(tickets[i]+"\n");
				webserversbuffer.append("*****No Comments*****");
				webserversbuffer.append("\n**********************************************************************\n");
			}	
			// for MSMQ field
			String msmq = (String)issue.getCustomFieldValue(cf_msmq);
			if(msmq!=null && !msmq.trim().equalsIgnoreCase("")){
				msmqbuffer.append(tickets[i]+"\n");
				msmqbuffer.append(msmq);
				msmqbuffer.append("\n**********************************************************************\n");
			} else {
				msmqbuffer.append(tickets[i]+"\n");
				msmqbuffer.append("*****No Comments*****");
				msmqbuffer.append("\n**********************************************************************\n");
			}
			// for eCon Servers field
			String econservers = (String)issue.getCustomFieldValue(cf_econservers);
			if(econservers!=null && !econservers.trim().equalsIgnoreCase("")){
				econserversbuffer.append(tickets[i]+"\n");
				econserversbuffer.append(econservers);
				econserversbuffer.append("\n**********************************************************************\n");
			} else {
				econserversbuffer.append(tickets[i]+"\n");
				econserversbuffer.append("*****No Comments*****");
				econserversbuffer.append("\n**********************************************************************\n");
			}
			
			// for Lender Servers field
						String lenderservers = (String)issue.getCustomFieldValue(cf_lenderservers);
						if(lenderservers!=null && !lenderservers.trim().equalsIgnoreCase("")){
							lenderserversbuffer.append(tickets[i]+"\n");
							lenderserversbuffer.append(lenderservers);
							lenderserversbuffer.append("\n**********************************************************************\n");
						} else {
							lenderserversbuffer.append(tickets[i]+"\n");
							lenderserversbuffer.append("*****No Comments*****");
							lenderserversbuffer.append("\n**********************************************************************\n");
						}
						
						// for compliance field
						String compliance = (String)issue.getCustomFieldValue(cf_compliance);
						if(compliance!=null && !compliance.trim().equalsIgnoreCase("")){
							compliancebuffer.append(tickets[i]+"\n");
							compliancebuffer.append(compliance);
							compliancebuffer.append("\n**********************************************************************\n");
						} else {
							compliancebuffer.append(tickets[i]+"\n");
							compliancebuffer.append("*****No Comments*****");
							compliancebuffer.append("\n**********************************************************************\n");
						}
						// for calc interface field
						String calinterface = (String)issue.getCustomFieldValue(cf_calcinterface);						
						if(calinterface!=null && !calinterface.trim().equalsIgnoreCase("")){
							calinterfacebuffer.append(tickets[i]+"\n");
							calinterfacebuffer.append(calinterface);
							calinterfacebuffer.append("\n**********************************************************************\n");
						} else {
							calinterfacebuffer.append(tickets[i]+"\n");
							calinterfacebuffer.append("*****No Comments*****");
							calinterfacebuffer.append("\n**********************************************************************\n");
						}
						
						String mqbridge = (String)issue.getCustomFieldValue(cf_mqbridge);
						if(mqbridge!=null && !mqbridge.trim().equalsIgnoreCase("")){
							mqbridgebuffer.append(tickets[i]+"\n");
							mqbridgebuffer.append(mqbridge);
							mqbridgebuffer.append("\n**********************************************************************\n");
						} else {
							mqbridgebuffer.append(tickets[i]+"\n");
							mqbridgebuffer.append("*****No Comments*****");
							mqbridgebuffer.append("\n**********************************************************************\n");
						}
						String sitecore = (String)issue.getCustomFieldValue(cf_sitecore);
						if(sitecore!=null && !sitecore.trim().equalsIgnoreCase("")){
							sitecorebuffer.append(tickets[i]+"\n");
							sitecorebuffer.append(sitecore);
							sitecorebuffer.append("\n**********************************************************************\n");
						} else {
							sitecorebuffer.append(tickets[i]+"\n");
							sitecorebuffer.append("*****No Comments*****");
							sitecorebuffer.append("\n**********************************************************************\n");
						}
						String dtwebserver = (String)issue.getCustomFieldValue(cf_dtwebserver);
						if(dtwebserver!=null && !dtwebserver.trim().equalsIgnoreCase("")){
							dtwebserverbuffer.append(tickets[i]+"\n");
							dtwebserverbuffer.append(dtwebserver);
							dtwebserverbuffer.append("\n**********************************************************************\n");
						} else {
							dtwebserverbuffer.append(tickets[i]+"\n");
							dtwebserverbuffer.append("*****No Comments*****");
							dtwebserverbuffer.append("\n**********************************************************************\n");
						}
						String WSOASever = (String)issue.getCustomFieldValue(cf_WSOASever);
						if(WSOASever!=null && !WSOASever.trim().equalsIgnoreCase("")){
							WSOASeverbuffer.append(tickets[i]+"\n");
							WSOASeverbuffer.append(WSOASever);
							WSOASeverbuffer.append("\n**********************************************************************\n");
						} else {
							WSOASeverbuffer.append(tickets[i]+"\n");
							WSOASeverbuffer.append("*****No Comments*****");
							WSOASeverbuffer.append("\n**********************************************************************\n");
						}
						String cbcluster = (String)issue.getCustomFieldValue(cf_cbcluster);
						if(cbcluster!=null && !cbcluster.trim().equalsIgnoreCase("")){
							cbclusterbuffer.append(tickets[i]+"\n");
							cbclusterbuffer.append(cbcluster);
							cbclusterbuffer.append("\n**********************************************************************\n");
						} else {
							cbclusterbuffer.append(tickets[i]+"\n");
							cbclusterbuffer.append("*****No Comments*****");
							cbclusterbuffer.append("\n**********************************************************************\n");
						}
						String drswsoa = (String)issue.getCustomFieldValue(cf_drswsoa);
						if(drswsoa!=null && !drswsoa.trim().equalsIgnoreCase("")){
							drswsoabuffer.append(tickets[i]+"\n");
							drswsoabuffer.append(drswsoa);
							drswsoabuffer.append("\n**********************************************************************\n");
						} else {
							drswsoabuffer.append(tickets[i]+"\n");
							drswsoabuffer.append("*****No Comments*****");
							drswsoabuffer.append("\n**********************************************************************\n");
						}
						String drsmsmqfdvip = (String)issue.getCustomFieldValue(cf_drsmsmqfdvip);
						if(drsmsmqfdvip!=null && !drsmsmqfdvip.trim().equalsIgnoreCase("")){
							drsmsmqfdvipbuffer.append(tickets[i]+"\n");
							drsmsmqfdvipbuffer.append(drsmsmqfdvip);
							drsmsmqfdvipbuffer.append("\n**********************************************************************\n");
						} else {
							drsmsmqfdvipbuffer.append(tickets[i]+"\n");
							drsmsmqfdvipbuffer.append("*****No Comments*****");
							drsmsmqfdvipbuffer.append("\n**********************************************************************\n");
						}
						String msmqcluster = (String)issue.getCustomFieldValue(cf_msmqcluster);
						if(msmqcluster!=null && !msmqcluster.trim().equalsIgnoreCase("")){
							msmqclusterbuffer.append(tickets[i]+"\n");
							msmqclusterbuffer.append(msmqcluster);
							msmqclusterbuffer.append("\n**********************************************************************\n");
						} else {
							msmqclusterbuffer.append(tickets[i]+"\n");
							msmqclusterbuffer.append("*****No Comments*****");
							msmqclusterbuffer.append("\n**********************************************************************\n");
						}
						String otherservers = (String)issue.getCustomFieldValue(cf_otherservers);
						if(otherservers!=null && !otherservers.trim().equalsIgnoreCase("")){
							otherserversbuffer.append(tickets[i]+"\n");
							otherserversbuffer.append(otherservers);
							otherserversbuffer.append("\n**********************************************************************\n");
						} else {
							otherserversbuffer.append(tickets[i]+"\n");
							otherserversbuffer.append("*****No Comments*****");
							otherserversbuffer.append("\n**********************************************************************\n");
						}
						String rollback = (String)issue.getCustomFieldValue(cf_rollback);
						if(rollback!=null && !rollback.trim().equalsIgnoreCase("")){
							rollbackbuffer.append(tickets[i]+"\n");
							rollbackbuffer.append(rollback);
							rollbackbuffer.append("\n**********************************************************************\n");
						} else {
							rollbackbuffer.append(tickets[i]+"\n");
							rollbackbuffer.append("*****No Comments*****");
							rollbackbuffer.append("\n**********************************************************************\n");
						}
						String otherins = (String)issue.getCustomFieldValue(cf_otherins);
						if(otherins!=null && !otherins.trim().equalsIgnoreCase("")){
							otherinsbuffer.append(tickets[i]+"\n");
							otherinsbuffer.append(otherins);
							otherinsbuffer.append("\n**********************************************************************\n");
						} else {
							otherinsbuffer.append(tickets[i]+"\n");
							otherinsbuffer.append("*****No Comments*****");
							otherinsbuffer.append("\n**********************************************************************\n");
						}
						String prodcomments = (String)issue.getCustomFieldValue(cf_productioncommnts);
						if(prodcomments!=null && !prodcomments.trim().equalsIgnoreCase("")){
							prodcommentsbuffer.append(tickets[i]+"\n");
							prodcommentsbuffer.append(prodcomments);
							prodcommentsbuffer.append("\n**********************************************************************\n");
						} else {
							prodcommentsbuffer.append(tickets[i]+"\n");
							prodcommentsbuffer.append("*****No Comments*****");
							prodcommentsbuffer.append("\n**********************************************************************\n");
						}
						
						
			//System.out.println(" webservers "+ webservers + " msmq "+ msmq + " econservers "+ econservers);			
		}
		AddDeploymentInstructionsModel webservers_M = new AddDeploymentInstructionsModel(cf_webservers.getHiddenFieldId(), webserversbuffer.toString());
		listOfModels.add(webservers_M);
		AddDeploymentInstructionsModel msmq_M = new AddDeploymentInstructionsModel(cf_msmq.getHiddenFieldId(), msmqbuffer.toString());
		listOfModels.add(msmq_M);
		AddDeploymentInstructionsModel econservers_M = new AddDeploymentInstructionsModel(cf_econservers.getHiddenFieldId(), econserversbuffer.toString());
		listOfModels.add(econservers_M);
		AddDeploymentInstructionsModel lenderservers_M = new AddDeploymentInstructionsModel(cf_lenderservers.getHiddenFieldId(), lenderserversbuffer.toString());
		listOfModels.add(lenderservers_M);
		AddDeploymentInstructionsModel compliance_M = new AddDeploymentInstructionsModel(cf_compliance.getHiddenFieldId(), compliancebuffer.toString());
		listOfModels.add(compliance_M);
		AddDeploymentInstructionsModel calcinterface_M = new AddDeploymentInstructionsModel(cf_calcinterface.getHiddenFieldId(), calinterfacebuffer.toString());
		listOfModels.add(calcinterface_M);
		AddDeploymentInstructionsModel mqbridge_M = new AddDeploymentInstructionsModel(cf_mqbridge.getHiddenFieldId(), mqbridgebuffer.toString());
		listOfModels.add(mqbridge_M);
		AddDeploymentInstructionsModel dtwebserver_M = new AddDeploymentInstructionsModel(cf_dtwebserver.getHiddenFieldId(), dtwebserverbuffer.toString());
		listOfModels.add(dtwebserver_M);
		AddDeploymentInstructionsModel WSOASever_M = new AddDeploymentInstructionsModel(cf_WSOASever.getHiddenFieldId(), WSOASeverbuffer.toString());
		listOfModels.add(WSOASever_M);
		AddDeploymentInstructionsModel cbcluster_M = new AddDeploymentInstructionsModel(cf_cbcluster.getHiddenFieldId(), cbclusterbuffer.toString());
		listOfModels.add(cbcluster_M);
		AddDeploymentInstructionsModel drswsoa_M = new AddDeploymentInstructionsModel(cf_drswsoa.getHiddenFieldId(), drswsoabuffer.toString());
		listOfModels.add(drswsoa_M);
		AddDeploymentInstructionsModel drsmsmqfdvip_M = new AddDeploymentInstructionsModel(cf_drsmsmqfdvip.getHiddenFieldId(), drsmsmqfdvipbuffer.toString());
		listOfModels.add(drsmsmqfdvip_M);
		AddDeploymentInstructionsModel msmqcluster_M = new AddDeploymentInstructionsModel(cf_msmqcluster.getHiddenFieldId(), msmqclusterbuffer.toString());
		listOfModels.add(msmqcluster_M);
		AddDeploymentInstructionsModel otherservers_M = new AddDeploymentInstructionsModel(cf_otherservers.getHiddenFieldId(), otherserversbuffer.toString());
		listOfModels.add(otherservers_M);
		AddDeploymentInstructionsModel rollback_M = new AddDeploymentInstructionsModel(cf_rollback.getHiddenFieldId(), rollbackbuffer.toString());
		listOfModels.add(rollback_M);
		AddDeploymentInstructionsModel otherins_M = new AddDeploymentInstructionsModel(cf_otherins.getHiddenFieldId(), otherinsbuffer.toString());
		listOfModels.add(otherins_M);
		AddDeploymentInstructionsModel productioncommnts_M = new AddDeploymentInstructionsModel(cf_productioncommnts.getHiddenFieldId(), prodcommentsbuffer.toString());
		listOfModels.add(productioncommnts_M);
		
	}catch(Exception e){
			e.printStackTrace();
	}
  		return Response.ok(listOfModels).build();		
	}
}
