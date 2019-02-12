package com.dt.jira.xmatters.intgt.plugin.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.dt.jira.xmatters.intgt.plugin.ao.FieldDB;
import com.dt.jira.xmatters.intgt.plugin.rest.FieldBean;
import net.java.ao.Query;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Contains all the service methods pertaining to
 * - Adding JIRA -> XMatters Mapping
 * - Deleting JIRA -> XMatters Mapping
 * - Getting all the persisted JIRA -> XMatters Mappings
 */
public class FieldMapperServiceImpl implements FieldMapperService
{
	private final Logger logger = Logger.getLogger(FieldMapperServiceImpl.class);
    private final ActiveObjects ao;
    /**
     * Constructor
     * @param ao the ActiveObjects to be injected
     */
    public FieldMapperServiceImpl(ActiveObjects ao) {
        this.ao = checkNotNull(ao);
    }

    /**
     * Save field mapping on DB
     * @param fieldBean 
     */
	@Override
	public FieldBean addMapping(final FieldBean fieldBean) {
		List<FieldBean> mappingsList = getMapping(fieldBean);

        //If the X_MATTERS_FIELD is already there, then we dont allow the mapping to happen. In other words
        //the mapping will happen only if the xmatters field is new.
        if (mappingsList!=null && mappingsList.size() == 0) {
           ao.executeInTransaction(new TransactionCallback<FieldBean>() {
               @Override
               public FieldBean doInTransaction() {
                   final FieldDB fieldDB1 = ao.create(FieldDB.class);
                   fieldDB1.setJiraField(fieldBean.getJiraFieldName());
                   fieldDB1.setxMattersField(fieldBean.getxMattersFieldName());
                   fieldDB1.save();
                   return fieldBean;
               }
           });
        }
        return null;
	}

	 /**
     * Delete field mapping from  DB
     * @param fieldBean 
     */
	@Override
	public FieldBean deleteMapping(final FieldBean fieldBean) {
        ao.executeInTransaction(new TransactionCallback<FieldBean>() {
            @Override
            public FieldBean doInTransaction() {
                FieldBean fb = new FieldBean();
                FieldDB[] fieldDb = ao.find(FieldDB.class, "JIRA_FIELD = ?", fieldBean.getJiraFieldName());
                if (fieldDb.length > 0 ) {
                    ao.delete(fieldDb);
                }
                return fb;
            }
        });
		 return null;
	}

    /**
     * Get all field mapping from  DB
     * @return list of all mappings
     */
	@Override
	public List<FieldBean> getAllMappingsFromDB() {
		List<FieldBean> mappingsList = new ArrayList<FieldBean>();
        FieldBean fieldBean;
        FieldDB[] fieldDB = ao.find(FieldDB.class, Query.select().order("JIRA_FIELD ASC"));
        for (FieldDB fieldDB1 : fieldDB) {
            fieldBean = new FieldBean();
            fieldBean.setJiraFieldName(fieldDB1.getJiraField());
            fieldBean.setxMattersFieldName(fieldDB1.getxMattersField());
            mappingsList.add(fieldBean);
        }
        return mappingsList;
	}

    /**
     * Get field mapping from  DB
     * @param fieldBean
     * @return bean containing the concerned mapping
     */
	@Override
	public List<FieldBean> getMapping(FieldBean fieldBean) {
		List<FieldBean> mappingsList = new ArrayList<FieldBean>();
        
        FieldDB[] fieldDB = ao.find(FieldDB.class, " X_MATTERS_FIELD = ? ", fieldBean.getxMattersFieldName());
        for (FieldDB fieldDB1 : fieldDB) {
            fieldBean = new FieldBean();
            fieldBean.setJiraFieldName(fieldDB1.getJiraField());
            fieldBean.setxMattersFieldName(fieldDB1.getxMattersField());
            mappingsList.add(fieldBean);
        }
        return mappingsList;		
	}
    
}

    