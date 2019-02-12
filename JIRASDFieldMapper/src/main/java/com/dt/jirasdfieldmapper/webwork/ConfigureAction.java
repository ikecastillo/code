package com.dt.jirasdfieldmapper.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;

/**
 * Created by yagnesh.bhat on 6/22/2015.
 */
public class ConfigureAction extends JiraWebActionSupport {

    private static final long serialVersionUID = -5480634702843231222L;

    @Override
    protected String doExecute() throws Exception {
        return SUCCESS;
    }

    @Override
    public String doDefault() throws Exception {
        return SUCCESS;
    }

}
