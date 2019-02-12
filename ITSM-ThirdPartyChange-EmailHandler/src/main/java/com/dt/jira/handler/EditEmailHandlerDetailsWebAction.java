package com.dt.jira.handler;
import com.atlassian.configurable.ObjectConfigurationException;
import com.atlassian.jira.plugins.mail.webwork.AbstractEditHandlerDetailsWebAction;
import com.atlassian.jira.service.JiraServiceContainer;
import com.atlassian.jira.service.services.file.AbstractMessageHandlingService;
import com.atlassian.jira.service.util.ServiceUtils;
import com.atlassian.plugin.PluginAccessor;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Map;



public class EditEmailHandlerDetailsWebAction extends AbstractEditHandlerDetailsWebAction {

    private final Logger log = org.slf4j.LoggerFactory.getLogger(EditEmailHandlerDetailsWebAction.class);

    public EditEmailHandlerDetailsWebAction(PluginAccessor pluginAccessor) {
        super(pluginAccessor);



    }
    private String watchers;

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }

    private String handlerparams;



    public String getHandlerparams() {
        return handlerparams;
    }

    public void setHandlerparams(String handlerparams) {
        this.handlerparams = handlerparams;
    }

    // this method is called to let us populate our variables (or action state)
    // with current handler settings managed by associated service (file or mail).
    @Override
    protected void copyServiceSettings(JiraServiceContainer jiraServiceContainer) throws ObjectConfigurationException {
        final String params = jiraServiceContainer.getProperty(AbstractMessageHandlingService.KEY_HANDLER_PARAMS);
			/*handlerparams = jiraServiceContainer.getProperty(AbstractMessageHandlingService.KEY_CUSTOM_HANDLER_PARAMS);*/
			log.debug("-------params------------"+params);
        final Map<String, String> parameterMap = ServiceUtils.getParameterMap(params);
        log.debug("-------parameterMap------------"+parameterMap);
        watchers = parameterMap.get(ThirdPartyChangeEmailHandler.KEY_WATCHERS);
        log.debug("-------watchers------------"+watchers);
        parameterMap.remove(ThirdPartyChangeEmailHandler.KEY_WATCHERS);
        log.debug("-------parameterMap------------"+parameterMap);
        handlerparams =ServiceUtils.toParameterString(parameterMap);
        log.debug("-------handlerparams------------"+ handlerparams);

    }
   
   
    @Override
    protected Map<String, String> getHandlerParams() {

        String[] handlerOrigParams = handlerparams.split(",");
        log.debug("-------getHandlerParams------------"+ handlerOrigParams);
        Map<String, String> params = new HashMap<>();
        
        params.put(ThirdPartyChangeEmailHandler.KEY_WATCHERS,watchers);

        for (String handlerOrigParam : handlerOrigParams) {
            String[] param = handlerOrigParam.split("=");
            if(!param[0].equals(ThirdPartyChangeEmailHandler.KEY_WATCHERS)){
            params.put(param[0],param[1]);
            }
        }
        log.debug("-------params------------"+ params);
        //return MapBuilder.build(ThirdPartyChangeEmailHandler.KEY_WATCHERS, watchers);
        return params;
    }

    @Override
    protected void doValidation() {
        if (configuration == null) {
            return; // short-circuit in case we lost session, goes directly to doExecute which redirects user
        }
        //super.doValidation();
        //issueKeyValidator.validateIssue(watchers, new WebWorkErrorCollector());
    }
}