package com.dt.jira.generichandler;

import com.atlassian.configurable.ObjectConfigurationException;
import com.atlassian.jira.plugins.mail.webwork.AbstractEditHandlerDetailsWebAction;
import com.atlassian.jira.service.JiraServiceContainer;
import com.atlassian.jira.service.services.file.AbstractMessageHandlingService;
import com.atlassian.jira.service.util.ServiceUtils;
import com.atlassian.jira.util.collect.MapBuilder;
import com.atlassian.plugin.PluginAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static com.dt.jira.generichandler.GenericEmailHandler.*;

/**
 * This class works hand-in-hand with the GenericEmailHandler.java code to display custom UI of this handler.
 *
 * Created by Yagnesh.Bhat on 2/10/2016.
 */
public class EditGenericEmailHandlerDetailsWebAction extends AbstractEditHandlerDetailsWebAction{

    private final Logger log = LoggerFactory.getLogger(EditGenericEmailHandlerDetailsWebAction.class);

    private String project;
    private String associatedstatus ;
    private String keyword;

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getAssociatedstatus() {
        return associatedstatus;
    }

    public void setAssociatedstatus(String associatedstatus) {
        this.associatedstatus = associatedstatus;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }



    public EditGenericEmailHandlerDetailsWebAction(PluginAccessor pluginAccessor) {
        super(pluginAccessor);
    }

    // this method is called to let us populate our variables (or action state)
    // with current handler settings managed by associated service (file or mail).
    @Override
    protected void copyServiceSettings(JiraServiceContainer jiraServiceContainer) throws ObjectConfigurationException {
        final String params = jiraServiceContainer.getProperty(AbstractMessageHandlingService.KEY_HANDLER_PARAMS);
        final Map<String, String> parameterMap = ServiceUtils.getParameterMap(params);
        project = parameterMap.get(KEY_PROJECT);
        log.debug("Project Key is " + project);
        associatedstatus = parameterMap.get(KEY_ASSOCIATED_STATUS);
        log.debug("Associated Status is " + associatedstatus);
        keyword = parameterMap.get(KEY_KEYWORD);
        log.debug("Key Word is " + keyword);
        log.debug("Parameters from action to handler are -> " + project  + "," +  associatedstatus + "," + keyword);
    }

    @Override
    protected Map<String, String> getHandlerParams() {

        MapBuilder<String,String> mapBuilder = MapBuilder.newBuilder();
        mapBuilder.add(KEY_PROJECT,this.project);
        mapBuilder.add(KEY_ASSOCIATED_STATUS,this.associatedstatus);
        mapBuilder.add(KEY_KEYWORD,this.keyword);

        return mapBuilder.toHashMap();
    }

    @Override
    protected void doValidation() {
        if (configuration == null) {
            return; // short-circuit in case we lost session, goes directly to doExecute which redirects user
        }
        super.doValidation();
    }
}
