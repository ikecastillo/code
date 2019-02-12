package com.dt.jira.plugin.rest;

import java.util.Comparator;

public class IssueSorter implements Comparator<ScopeVarianceModel>{

	

	@Override
	public int compare(ScopeVarianceModel o1, ScopeVarianceModel o2) {
		
		String sName1 = o1.getMessage4();
		String sName2 = o2.getMessage4();
		
		return sName1.compareTo(sName2);

	}

}
