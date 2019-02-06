package com.dt.remote.dtchgcreator.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.plugin.PluginAccessor;

public class PropertyUtils {
    
    
    
     //FEILD NAMES
       public static final String SOLUTION_GROUP_PRODUCT ="Solution Group - Product";
       public static final String IMPACTED_FUNCTION="Impacted - Function";
       public static final String ENVIRONMENT="Environment";
       public static final String CHANGE_TYPE="Change Type";
       public static final String RISK="Risk";
       public static final String REPORTERS_EMAIL="Reporter's Contact Email";
       public static final String REPORTERS_DEPARTMENT="Reporter's Department";
       public static final String MANAGERS_NAME="Manager's Name";
       public static final String ROLLBACK_DESC="Roll Back Description";
       public static final String REQ_ST_DATE="Requested Start Date";
       public static final String ROLLBACKPLAN_QUESTION1="What circumstances will necessitate back-out and at what point will a decision to back-out be made?";
        public static final String ROLLBACKPLAN_QUESTION2="Who will make the decision to back-out?";
        public static final String ROLLBACKPLAN_QUESTION3="How will the back-out be performed?";
        public static final String ROLLBACKPLAN_QUESTION4="What contingency measures have been considered?";
        public static final String ROLLBACKPLAN_QUESTION5="What is the expected time required to perform a back-out?";
        public static final String ROLLBACKPLAN_QUESTION6="Are there any manual procedures to be followed?";
        public static final String ROLLBACKPLAN_QUESTION7="Are there any communication requirements in the event of a back-out?";
        public static final String ROLLBACKPLAN_QUESTION8="Has this back-out plan been tested successfully?";
        public static final String RISK_QUESTION1="Number of end users impacted?";
        public static final String RISK_QUESTION2="Criticality of the service to the business?";
        public static final String RISK_QUESTION3="Number of teams involved with implementation?";
        public static final String RISK_QUESTION4="Can it be tested? Can it be backed out?";
    
    
    //FEILD VALUES
    public static final String ISSUE_SUMMARY_VALUE="firewall change request for Critical access";
     public static final String ISSUE_DESC_VALUE="Creating of an issue using project keys and issue type names using the REST API";
    public static final String ROLLBACK_DEFAULT_VALUE="NA";
    public static final String SOLUTION_GROUP_PRODUCT_VALUE ="Solution Group - Product";
       public static final String IMPACTED_FUNCTION_VALUE="Impacted - Function";
       public static final String ENVIRONMENT_VALUE="Environment";
       public static final String CHANGE_TYPE_VALUE="Change Type";
       public static final String RISK_VALUE="Risk";
       public static final String REPORTERS_EMAIL_VALUE="Reporter's Contact Email";
       public static final String REPORTERS_DEPARTMENT_VALUE="Reporter's Department";
       public static final String MANAGERS_NAME_VALUE="Manager's Name";
       public static final String ROLLBACK_DESC_VALUE="Roll Back Description";
       public static final String REQ_ST_DATE_VALUE="Requested Start Date";
       public static final String RISK_QUESTION1_VALUE="Number of end users impacted?";
        public static final String RISK_QUESTION2_VALUE="Criticality of the service to the business?";
        public static final String RISK_QUESTION3_VALUE="Number of teams involved with implementation?";
        public static final String RISK_QUESTION4_VALUE="Can it be tested? Can it be backed out?"; 
    
    
       
    
    
    

    public static final String CONF_FILE = "/DTChgCreator.properties";
    private static final Logger logger = LoggerFactory
            .getLogger(PropertyUtils.class);
    private static Properties prop = getProps();
    private final PluginAccessor pluginAccessor;

    /**
     * constructor
     *
     * @param pluginAccessor
     */
    public PropertyUtils(PluginAccessor pluginAccessor) {
        this.pluginAccessor = pluginAccessor;
    }

    public static Properties getProp() {
        return prop;
    }

    /**
     * Load properties
     *
     * @return
     */
    private static Properties getProps() {
        if (prop == null) {
            prop = new Properties();
            try {
                prop.load(PropertyUtils.class.getResourceAsStream(CONF_FILE));
            } catch (IOException e) {
                logger.error("Can't load " + CONF_FILE);
                //e.printStackTrace();
            }
        }
        return prop;
    }

    /**
     * get value by key from properties file
     * ITSM-ThirdPartyChange-EmailHandler.properties
     *
     * @param key
     * @return
     */
    public static String getPropertyValue(String key) {
        String value = null;
        if (key != null && (!key.isEmpty())) {
            value = prop.getProperty(key.trim());
            if (value != null && (!value.isEmpty())) {
                value = value.trim();
            }
        }
        logger.info(key+"----key-------value-------"+value);
        return value;
    }

}
