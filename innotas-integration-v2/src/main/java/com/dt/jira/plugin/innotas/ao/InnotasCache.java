/**
 * This represents the list of Innotas Business Units - cached in a Jira AO table
 */
package com.dt.jira.plugin.innotas.ao;

import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * @author sriram.rajaraman
 *
 */

@Preload
public interface InnotasCache extends Entity {
	String getProjectId();
	void setProjectId(String value);
	String getProjectName();
	void setProjectName(String value);
	String getBuId();
	void setBuId(String value);
	String getBuName();
	void setBuName(String value);
	String getSbuId();
	void setSbuId(String value);
	String getSbuName();
	void setSbuName(String value);
	String getProgramName();
	void setProgramName(String value);
	String getAgileProject();
	void setAgileProject(String value);
}
