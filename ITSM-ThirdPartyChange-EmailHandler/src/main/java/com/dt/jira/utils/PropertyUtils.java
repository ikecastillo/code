package com.dt.jira.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.plugin.PluginAccessor;

public class PropertyUtils {

    public static final String CONF_FILE = "/ITSM-ThirdPartyChange-EmailHandler.properties";
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
                e.printStackTrace();
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
        // System.out.println(key+"----key-------value-------"+value);
        return value;
    }

}
