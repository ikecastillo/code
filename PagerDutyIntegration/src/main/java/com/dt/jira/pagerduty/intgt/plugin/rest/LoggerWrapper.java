package com.dt.jira.pagerduty.intgt.plugin.rest;


import org.apache.log4j.*;

import java.io.File;
import java.io.IOException;


/**
 * A custom class implemented for  logging messages and convenience methods for {@link Logger}.
 * 
 */
public class LoggerWrapper
{
    private final Logger logger;
    private static final String ATLASSIAN_GREENHOPPER = "atlassian-xmatters";
    private static final String MARKER_LINE = "*********************************************************************************\n";

    private static String[] LOGGER_NAMES = {"com.dt.jira.plugin.rest"};

    LoggerWrapper(Logger logger)
    {
    	// createAppenders();
        this.logger = logger;
       
    }
    /**
     * Create LoggerWrapper by passing logger object
     * @param logger <Logger> input parameter
     * @return <LoggerWrapper>
     */
    public static LoggerWrapper with(Logger logger)
    {
        return new LoggerWrapper(logger);
    }
/**
 * Create LoggerWrapper by passing class object
 * @param clazz<Class> - current class name
 * @return <LoggerWrapper>
 */
    public static LoggerWrapper with(Class clazz)
    {
        return with(LogManager.getLogger(clazz));
    }
/**
 *  Create LoggerWrapper by passing class name in string
 * @param name<String> name of the class/package
 * @return<LoggerWrapper>
 */
    public static LoggerWrapper with(String name)
    {
        return with(LogManager.getLogger(name));
    }

    public String getName()
    {
        return logger.getName();
    }
/**
 * set the Log level to Info
 */
    public void setInfoLogLevel()
    {
        Logger.getLogger(logger.getName()).setLevel(Level.INFO);
        Logger.getLogger(logger.getClass()).setLevel(Level.INFO);
    }
/**
 * is Debug is enabled - true - if enabled otherwise false
 * @return true/false
 */
    public boolean isDebugEnabled()
    {
        return logger.isDebugEnabled();
    }
    /**
    * is Info is enabled - true - if enabled otherwise false
    * @return true/false
    */
    public boolean isInfoEnabled()
    {
        return logger.isInfoEnabled();
    }
    /**
     * Method handles the exception
     * @param t<Throwable> input parameter
     */
    public void exception(Throwable t)
    {
        exception(t, LogLevel.ERROR);
    }
    /**
     * Method handles the exception for all the log level
     * @param  t<Throwable> input parameter
     * @param logLevel<LogLevel> input parameter
     */
    public void exception(Throwable t, LogLevel logLevel)
    {
        try
        {
            switch (logLevel)
            {
                case ERROR:
                    logger.error(t.getLocalizedMessage(), t);
                    break;
                case DEBUG:
                    logger.debug(t.getLocalizedMessage(), t);
                    break;
                case WARN:
                    logger.warn(t.getLocalizedMessage(), t);
                    break;
                case INFO:
                    logger.info(t.getLocalizedMessage(), t);
                    break;
                case TRACE:
                    logger.trace(t.getLocalizedMessage(), t);
                    break;
            }

        }
        catch (Throwable t1)
        {
            t.printStackTrace();
            t1.printStackTrace();
            if (t instanceof RuntimeException)
            {
                throw (RuntimeException) t;
            }
            else
            {
                throw new RuntimeException(t);
            }
        }
    }
    /**
     * Method log the error messages with parameters
     * @param message<String> - input messages
     * @param params<Object[]> - parameters
     */
    public void error(String message, Object... params)
    {
            logger.error(createMessage(message, params));
    }
    /**
     * Method log the warning messages with parameters
     * @param message<String> - input messages
     * @param params<Object[]> - parameters
     */
    public void warn(String message, Object... params)
    {
            logger.warn(createMessage(message, params));

    }
    /**
     * Method log the info messages with parameters
     * @param message<String> - input messages
     * @param params<Object[]> - parameters
     */
    public void info(String message, Object... params)
    {
        if (logger.isInfoEnabled())
        {
            logger.info(createMessage(message, params));
        }
    }
    /**
     * Method log the info messages without parameters
     * @param message<String> - input messages
     */
	public void info(String message)
    {
        if (logger.isInfoEnabled())
        {
            logger.info(createMessage(message,null));
        }
    }
	 /**
     * Method log the debug messages with parameters
     * @param message<String> - input messages
     * @param params<Object[]> - parameters
     */
    public void debug(String message, Object... params)
    {
        if (logger.isDebugEnabled())
        {
            logger.debug(createMessage(message, params));
        }
    }
    /**
     * Method log the trace messages with parameters
     * @param message<String> - input messages
     * @param params<Object[]> - parameters
     */
    public void trace(String message, Object... params)
    {
        if (logger.isTraceEnabled())
        {
            logger.trace(createMessage(message, params));
        }
    }
    /**
     * Method log the messages with parameters
     * @param message<String> - input messages
     * @param params<Object[]> - parameters
     */
    public String createMessage(String message, Object[] params)
    {
        try
        {
            return String.format(message, params);
        }
        catch (RuntimeException e)
        {
            logger.error("Unable to format message: " + message, e);
            return "";
        }
    }

    public static enum LogLevel
    {
        ERROR,
        WARN,
        DEBUG,
        INFO,
        TRACE
    }
    /**
     * Create the appenders
     */
    private void createAppenders()
    {
        org.apache.log4j.Appender ourAppender = null;
        for (String loggerName : LOGGER_NAMES)
        {
        	Logger logger = Logger.getLogger(loggerName);

            if (logger.getAppender(ATLASSIAN_GREENHOPPER) == null)
            {
                ourAppender = ourAppender == null ? createOurAppender() : ourAppender;
                logger.addAppender(ourAppender);
            }
        }
    }
    
    private org.apache.log4j.Appender createOurAppender()
    {
        try
        {
            final PatternLayout layout = new PatternLayout("%d %t %p %X{jira.username} %X{jira.request.id} %X{jira.request.assession.id} %X{jira.request.ipaddr} %X{jira.request.url} [%c{4}] %m%n");
            final String fileName = new File("D:\\JiraDev", "atlassian-kk.log").getAbsolutePath();
            final RollingFileAppender appender = new RollingFileAppender(layout, fileName, true);
            appender.setName("atlassian-kk");
            appender.setMaxFileSize("20480KB");
            appender.setMaxBackupIndex(5);

            return appender;

        }
        catch (IOException ioe)
        {
            final IllegalStateException illegalStateException = new IllegalStateException("Unable to initialise GreenHopper log4j support", ioe);
            //log.error(illegalStateException.getMessage());
            throw illegalStateException;
        }
    }
}
