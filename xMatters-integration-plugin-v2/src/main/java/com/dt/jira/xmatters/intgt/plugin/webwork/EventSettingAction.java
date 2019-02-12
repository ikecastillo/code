package com.dt.jira.xmatters.intgt.plugin.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;

/**
 * Created by Yagnesh.Bhat on 10/16/2015.
 */
public class EventSettingAction extends JiraWebActionSupport {

    private static final long serialVersionUID = -5480634702843231198L;

    @Override
    protected String doExecute() throws Exception {
        return SUCCESS;
    }

    @Override
    public String doDefault() throws Exception {
        return SUCCESS;
    }

}
