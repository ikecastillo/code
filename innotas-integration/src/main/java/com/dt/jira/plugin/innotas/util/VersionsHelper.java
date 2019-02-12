package com.dt.jira.plugin.innotas.util;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.project.ProjectManager;
import com.atlassian.jira.project.version.VersionManager;
import com.atlassian.jira.project.version.Version;
import java.util.List;

public class VersionsHelper{
	private VersionManager vm;
	private ProjectManager pm;
	public VersionsHelper(){
		init();
	}
	private void init() {
		vm = ComponentAccessor.getVersionManager();
		pm = ComponentAccessor.getProjectManager();
	}
	public List<Version> getVersionsList(String pKey) {
		Long pId = pm.getProjectObjByKey(pKey).getId();
		return vm.getVersions(pId);
	}
}