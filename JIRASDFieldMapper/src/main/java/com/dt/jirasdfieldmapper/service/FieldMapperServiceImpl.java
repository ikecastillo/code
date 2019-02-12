package com.dt.jirasdfieldmapper.service;

import com.atlassian.activeobjects.external.ActiveObjects;
import com.atlassian.sal.api.transaction.TransactionCallback;
import com.dt.jirasdfieldmapper.ao.JIRASDMapDB;
import com.dt.jirasdfieldmapper.rest.FieldBean;
import net.java.ao.Query;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Contains all the service methods pertaining to
 * add, delete and get all mappings
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

        if (mappingsList!=null && mappingsList.size() == 0) {
           ao.executeInTransaction(new TransactionCallback<FieldBean>() {
               @Override
               public FieldBean doInTransaction() {
                   final JIRASDMapDB fieldDB1 = ao.create(JIRASDMapDB.class);
                   fieldDB1.setJiraField(fieldBean.getJiraFieldName());
                   fieldDB1.setJiraSDField(fieldBean.getJiraSdFieldName());
                   fieldDB1.setJiraSDFieldId(fieldBean.getJiraSdFieldId());
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
                JIRASDMapDB[] fieldDb = ao.find(JIRASDMapDB.class, " JIRA_FIELD = ? AND JIRA_SDFIELD = ? ", fieldBean.getJiraFieldName(),
                        fieldBean.getJiraSdFieldName());
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
        JIRASDMapDB[] fieldDB = ao.find(JIRASDMapDB.class, Query.select().order("JIRA_FIELD ASC"));
        createListOfFieldBeans(mappingsList, fieldDB);
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
        
        JIRASDMapDB[] fieldDB = ao.find(JIRASDMapDB.class, " JIRA_FIELD = ? AND JIRA_SDFIELD = ? ",
                fieldBean.getJiraFieldName(),fieldBean.getJiraSdFieldName());
        createListOfFieldBeans(mappingsList, fieldDB);
        return mappingsList;		
	}

    private void createListOfFieldBeans(List<FieldBean> mappingsList, JIRASDMapDB[] fieldDB) {
        FieldBean fieldBean;
        for (JIRASDMapDB fieldDB1 : fieldDB) {
            fieldBean = new FieldBean();
            fieldBean.setJiraFieldName(fieldDB1.getJiraField());
            fieldBean.setJiraSdFieldName(fieldDB1.getJiraSDField());
            fieldBean.setJiraSdFieldId(fieldDB1.getJiraSDFieldId());
            mappingsList.add(fieldBean);
        }
    }
    
}

    