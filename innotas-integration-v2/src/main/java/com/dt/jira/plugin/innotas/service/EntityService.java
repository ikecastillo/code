/**
 * 
 */
package com.dt.jira.plugin.innotas.service;
import services.objects.xsd.EntityObj;
import services.objects.xsd.ValuePair;

/**
 * @author sriram.rajaraman
 *
 */
public interface EntityService{
	public EntityObj[] findEntity(Long entityTypeId, ValuePair[] vp, long[] fieldsRequest);
}