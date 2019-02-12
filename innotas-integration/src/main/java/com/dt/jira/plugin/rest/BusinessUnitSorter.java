package com.dt.jira.plugin.rest;

import java.util.Comparator;
/**
 * This class defined for sorting on business unit.
 * @author kiran.muthoju
 *
 */
public class BusinessUnitSorter implements Comparator<InnotasMapingTableModel>{

	

	@Override
	public int compare(InnotasMapingTableModel o1, InnotasMapingTableModel o2) {
		
		String sName1 = o1.getBusinessUnit();
		String sName2 = o2.getBusinessUnit();
		
		return sName1.compareTo(sName2);

	}

}
