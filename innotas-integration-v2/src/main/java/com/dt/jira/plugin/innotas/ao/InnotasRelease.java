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
public interface InnotasRelease extends Entity {
	String getProjectId();
	void setProjectId(String value);
	String getReleaseId();
	void setReleaseId(String value);
	String getReleaseName();
	void setReleaseName(String value);
}
