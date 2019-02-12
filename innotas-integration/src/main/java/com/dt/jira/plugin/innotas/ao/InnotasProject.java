/**
 * 
 */
package com.dt.jira.plugin.innotas.ao;




import net.java.ao.Entity;
import net.java.ao.Preload;

/**
 * @author sriram.rajaraman
 *
 */
@Preload
public interface InnotasProject extends Entity {
	String getInnotasId();
	void setInnotasId(String value);
	String getInnotasName();
	void setInnotasName(String value);
	String getBuId();
	void setBuId(String value);
	String getSbuId();
	void setSbuId(String value);
	String getSbuName();
	void setSbuName(String value);
	String getProgramName();
	void setProgramName(String value);
}
