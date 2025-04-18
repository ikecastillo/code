package com.dt.jira.xmatters.intgt.plugin.customfield;

import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.impl.GenericTextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;


public class TableCustomField  extends GenericTextCFType
{
	static Logger log = LoggerFactory.getLogger(TableCustomField.class);
	List colList = new ArrayList();

	
	  static final Gson GSON = new GsonBuilder().serializeNulls().create();
	  
    public TableCustomField(CustomFieldValuePersister customFieldValuePersister, GenericConfigManager genericConfigManager) {
        super(customFieldValuePersister, genericConfigManager);
    }
   
    @Nonnull
    protected PersistenceFieldType getDatabaseType()
    {
      return PersistenceFieldType.TYPE_UNLIMITED_TEXT;
    }
    
    public String getDefaultValue(FieldConfig fieldConfig)
    {
      if (super.getDefaultValue(fieldConfig) == null) {
        setDefaultValue(fieldConfig, "");
      }
      return (String)super.getDefaultValue(fieldConfig);
    }
       
    public void createValue(CustomField field, Issue issue, @Nonnull String value)
        {
          log.info("----------------------xMatters updated plugin************** create value is **********--------------" + value);
            try
            {
            	if (value!= null && value.equals("{\"data\": []}")) {
				log.info("-------------------------@@@@@@@@@@@@@@@@ create value------------------"+value);
        			value = "";
        		}else{
				log.info("-------------------------@@@@@@@@@@@@@@@@ create value in else block having null value------------------");
				}
            	
              
            }
            catch (Exception e)
            {
			log.info("-----------error occurs inside createValue method of TableCustomField---------------"+e.getMessage());
              //e.printStackTrace();
            }
          
            this.customFieldValuePersister.createValues(field, issue.getId(), getDatabaseType(), Lists.newArrayList(new Object[]{getDbValueFromObject(value)}));
          
        }
        
        public void updateValue(CustomField customField, Issue issue, String value)
        {
        	
            try
            {
              
            	if (value.equals("{\"data\": []}")) {
        			value = "<html></html>";
        		}
            	
              log.info("------------------------xmatters update value--------------------"+value);
            }
            catch (Exception e)
            {
			log.info("-----------error occurs inside updateValue method of TableCustomField---------------"+e.getMessage());
              //e.printStackTrace();
            }
            this.customFieldValuePersister.updateValues(customField, issue.getId(), getDatabaseType(), Lists.newArrayList(new Object[]{getDbValueFromObject(value)}));
          
        }
        
  /*      public void validateFromParams(CustomFieldParams relevantParams, ErrorCollection errorCollectionToAddTo, FieldConfig config)
        {
          try
          {
            String json = (String)getValueFromCustomFieldParams(relevantParams);
            if (json == null) {
              throw new JsonParseException("Null json string");
            }
            TableConfig tableConfig = getTableConfigFromJSON(json);
            if ((tableConfig.getRowHeaders() == null) || (tableConfig.columns == null) || (tableConfig.getRows() == null)) {
              throw new JsonParseException("Missing fields");
            }
          }
          catch (Exception e)
          {
            errorCollectionToAddTo.addError(config.getCustomField().getId(), e.getMessage(), ErrorCollection.Reason.VALIDATION_FAILED);
          }
        }*/
    
        public static TableConfig getTableConfigFromJSON(String json)
        {        	
        	log.info("---------------xMatters^^^^^^^^^^^String ------------------"+ json);
        	TableConfig tc = (TableConfig)GSON.fromJson(json, TableConfig.class);        	
            return tc;
        }
        
        public static class TableConfig
        {
          
          private List<TimeLine> data = null;
        


		public List<TimeLine> getData() {
			return data;
		}



		public void setData(List<TimeLine> data) {
			this.data = data;
		}



		@Override
      	public String toString() {
      	   return TableCustomField.GSON.toJson(this);
      	}
        
        }
        
        
        public static class TimeLine{
        	private String date="";
        	private String desc="";
        	private String time ="";
        	private String index="";
        	private String status="";
			private String priority="";
        	
			public String getDate() {
				return date;
			}
			public void setDate(String date) {
				this.date = date;
			}
			public String getDesc() {
				return desc;
			}
			public void setDesc(String desc) {
				this.desc = desc;
			}
			public String getIndex() {
				return index;
			}
			public void setIndex(String index) {
				this.index = index;
			}
			public String getTime() {
				return time;
			}
			public void setTime(String time) {
				this.time = time;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public String getPriority() {
				return priority;
			}
			public void setPriority(String priority) {
				this.priority = priority;
			}
			
        }

}
