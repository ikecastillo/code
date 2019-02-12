package com.dt.remote.pisdtktcreator.webwork;

import com.atlassian.jira.web.action.JiraWebActionSupport;

/**
 * Created by yagnesh.bhat on 4/15/2016.
 */
public class MappingAction extends JiraWebActionSupport {

    private static final long serialVersionUID = -5480634702843231197L;

    @Override
    protected String doExecute() throws Exception {
        return SUCCESS;
    }

    @Override
    public String doDefault() throws Exception {
        return SUCCESS;
    }

}
