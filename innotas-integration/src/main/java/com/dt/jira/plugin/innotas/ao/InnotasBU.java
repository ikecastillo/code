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
public interface InnotasBU extends Entity {
	String getBuId();
	String getBuName();
	void setBuId(String value);
	void setBuName(String value);
}
