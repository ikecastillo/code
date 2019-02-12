
package com.dt.jira.plugin.pisdtojirarelease.action;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.config.properties.APKeys;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.util.json.JSONArray;
import com.atlassian.jira.util.json.JSONObject;
import com.atlassian.jira.web.action.JiraWebActionSupport;
import com.atlassian.sal.api.pluginsettings.PluginSettingsFactory;
import com.dt.jira.plugin.pisdtojirarelease.Constants.PISDToJiraConstants;
import com.dt.jira.plugin.pisdtojirarelease.handler.PluginSettingFactoryHandler;
import com.dt.jira.plugin.pisdtojirarelease.rest.AdminConfigResource;
import com.dt.jira.plugin.pisdtojirarelease.rest.JiraProject;
import com.dt.jira.plugin.pisdtojirarelease.rest.ProjMapBean;
import com.dt.jira.plugin.pisdtojirarelease.service.ProjMapService;
import com.dt.jira.plugin.pisdtojirarelease.utils.ApplicationIDFinder;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Surya.Sriram on 7/26/2016.
 * CreateAndLinkJiraResourceAction: This class inherits JiraWebActionSupport class which contains the methods which executes on user actions
 *
 */

public class CreateAndLinkJiraResourceAction extends JiraWebActionSupport {

    private Long id;
    private String key;
    private List<JiraProject>  jiraProjectList;
    private static PluginSettingsFactory pluginSettingsFactory;
    private static final String PLUGIN_STORAGE_KEY = AdminConfigResource.Config.class.getName();
    private final ProjMapService projMapService;
    private final PluginSettingFactoryHandler pluginSettingFactoryHandler;
    private String pisdticketApplicationName="";
    private String jiraEnggApplicationName = "";
    private String baseURL="";



    private final Logger logger = LoggerFactory.getLogger(CreateAndLinkJiraResourceAction.class);


    public CreateAndLinkJiraResourceAction(ProjMapService projMapService, PluginSettingFactoryHandler pluginSettingFactoryHandler) {

        this.projMapService = projMapService;
        this.pluginSettingFactoryHandler = pluginSettingFactoryHandler;
    }

/*
* This method overrides execute() method which is available in JiraWebActionSupport class
* This method fetches all the available jira projects from Jira Resource Management by using Rest Services.
* */
    @Override
    public String execute() throws Exception {
        pisdticketApplicationName =  pluginSettingFactoryHandler.getServiceDeskAppName();
        jiraEnggApplicationName = pluginSettingFactoryHandler.getJiraEnggAppName();
        Issue issueParent = getIssueObject();
        ApplicationIDFinder applicationIDFinder = new ApplicationIDFinder(jiraEnggApplicationName, pisdticketApplicationName,
                ComponentAccessor.getApplicationProperties().getString(APKeys.JIRA_BASEURL), pluginSettingFactoryHandler.getTokenId());
        Map<String, String> applicationIDMap = applicationIDFinder.createApplicationIDMap();
        baseURL = applicationIDMap.get(jiraEnggApplicationName).split("\\|")[0];


        HttpResponse response = null;
        String jiraEnggUrl = baseURL + PISDToJiraConstants.REST_API_LIST_OF_PROJECTS;
        HttpGet httpGet = new HttpGet(jiraEnggUrl);

        httpGet.setHeader("Content-Type", "application/json");
        httpGet.setHeader("Authorization", "Basic " + pluginSettingFactoryHandler.getTokenId());
        httpGet.setHeader("X-ExperimentalApi", "true");

        HttpClient defaultHttpClient =null;
        defaultHttpClient = HttpClientBuilder.create().build();
        try {
            response = defaultHttpClient.execute(httpGet);
        }
        catch (IOException e) {
		logger.info("Error occurs due to :"+e.getMessage());
            //e.printStackTrace();
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuilder content = new StringBuilder();
            String line;
            while (null != (line = br.readLine())) {
                content.append(line);
            }


            final JSONArray objArray = new JSONArray(content.toString());
            logger.info("**********length *****"+ objArray.length());
            List<JiraProject> projectList = new ArrayList<JiraProject>();

            /*
            * Below logic is to iterate the jira projects fetched from rest call and check whether they are mapped to the current issue project.
            * In case if the project is mapped to the current issue project, make the project appear as default selected in drop down.
            * */
            for(int i=0;i<objArray.length();i++) {
                JiraProject jiraProject = new JiraProject();
                JSONObject obj = objArray.getJSONObject(i);
                String projectKey = obj.getString("key");
                jiraProject.setProjectKey(projectKey);
                String projectName = obj.getString("name");
				 logger.info("----------------------------------------------------------------------Start:"+i);
				 logger.info("---Project Name:----------------------"+projectName+"-----------------");
				 logger.info("----------------------------------------------------------------------End:"+i);
                jiraProject.setProjectName(projectName);
                ProjMapBean projMapBean = new ProjMapBean();
                projMapBean.setSdProjKey(issueParent.getProjectObject().getKey());
                projMapBean.setSdProjName(issueParent.getProjectObject().getName());

                ProjMapBean dbProjectMapBean = projMapService.getMappingBasedOnSDProj(projMapBean);
                if(dbProjectMapBean!=null && projectKey.equalsIgnoreCase(dbProjectMapBean.getJiraProjKey())){
                    jiraProject.setSelected("selected");
                } else {
                    jiraProject.setSelected("");
                }

                projectList.add(jiraProject);
            }
            setJiraProjectList(projectList);

        }


        //RETURN create.vm
        if (issueParent.getIssueType() != null){
            return "create";
        }
        else {
            return "Error";
        }

    }


/*
* This code is to return the current issue object.
* */
    public Issue getIssueObject()
    {
        Issue issue = ComponentAccessor.getIssueManager().getIssueObject(id);

        return  issue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<JiraProject> getJiraProjectList() {
        return jiraProjectList;
    }

    public void setJiraProjectList(List<JiraProject> jiraProjectList) {
        this.jiraProjectList = jiraProjectList;
    }
}

