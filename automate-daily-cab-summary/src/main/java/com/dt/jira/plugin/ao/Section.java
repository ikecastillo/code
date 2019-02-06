package com.dt.jira.plugin.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;
import net.java.ao.schema.StringLength;
import net.java.ao.Mutator;
/**
 * This entity class is used for automation configuration.
 * 
 * @author Srinadh.G
 */
@Preload
public interface Section extends Entity {

	@StringLength(value=StringLength.UNLIMITED)
	String getSectionName();

	@StringLength(value=StringLength.UNLIMITED)
	void setSectionName(String sectionName);
	
	@StringLength(value=StringLength.UNLIMITED)
	String getJqlQuery();

	@StringLength(value=StringLength.UNLIMITED)
	void setJqlQuery(String jqlQuery);
	
	@StringLength(value=StringLength.UNLIMITED)
	String getColumns();
	
	@StringLength(value=StringLength.UNLIMITED)
	void setColumns(String columns); 
    
	@StringLength(value=StringLength.UNLIMITED)
    String getHeaders();

	@StringLength(value=StringLength.UNLIMITED)
	void setHeaders(String headers); 
    
    java.util.Date getCreateDate();

	void setCreateDate(java.util.Date createDate);
    
    java.util.Date getUpdateDate();

	void setUpdateDate(java.util.Date updateDate);
    
    String getTemplateId();

	void setTemplateId(String templateId);
	
	
}
