package com.dt.remote.pisdtktcreator.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;

import static webwork.action.Action.SUCCESS;

/**
 * Created by yagnesh.bhat on 4/15/2016.
 */
public class ConfigureAction extends JiraWebActionSupport {

    private static final long serialVersionUID = -5480634702843231196L;

    @Override
    protected String doExecute() throws Exception {
        return SUCCESS;
    }

    @Override
    public String doDefault() throws Exception {
        return SUCCESS;
    }
}
