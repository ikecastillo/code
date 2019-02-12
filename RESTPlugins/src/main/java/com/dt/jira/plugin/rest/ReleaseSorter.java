package com.dt.jira.plugin.rest;

import java.util.Comparator;

public class ReleaseSorter implements Comparator<ReleaseBurnDownModel>{

	

	@Override
	public int compare(ReleaseBurnDownModel o1, ReleaseBurnDownModel o2) {
		
		String sName1 = o1.getSprintKey();
		String sName2 = o2.getSprintKey();
		
		return sName1.compareTo(sName2);

	}

}
