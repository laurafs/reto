package com.adidas.routes.properties;

import java.io.IOException;
import java.util.Properties;

/**
 * Class to manage properties
 * 
 * @author laurafs
 *
 */
public class Configuration {

    Properties properties = null;

    /** Configuration file name */
    public final static String REST_FILE_NAME = "rest.properties";
    public final static String ERROR_FILE_NAME = "errormessages.properties";

    
      private Configuration() {
        this.properties = new Properties();
        try {
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(REST_FILE_NAME));
            properties.load(Configuration.class.getClassLoader().getResourceAsStream(ERROR_FILE_NAME));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }//Configuration

    /**
     * Singleton
     *
     * @return
     */
    public static Configuration getInstance() {
        return ConfigurationHolder.INSTANCE;
    }

    private static class ConfigurationHolder {

        private static final Configuration INSTANCE = new Configuration();
    }

    /**
     * Returns config property
     *
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }
}