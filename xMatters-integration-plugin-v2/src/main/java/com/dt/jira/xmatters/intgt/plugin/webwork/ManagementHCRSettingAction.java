package com.dt.jira.xmatters.intgt.plugin.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;

/**
 * Created by yagnesh.bhat on 11/5/2015.
 */
public class ManagementHCRSettingAction extends JiraWebActionSupport {

    private static final long serialVersionUID = -5480634702843231199L;

    @Override
    protected String doExecute() throws Exception {
        return SUCCESS;
    }

    @Override
    public String doDefault() throws Exception {
        return SUCCESS;
    }
}
