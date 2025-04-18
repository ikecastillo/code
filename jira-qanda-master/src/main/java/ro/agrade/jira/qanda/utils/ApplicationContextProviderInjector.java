package ro.agrade.jira.qanda.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Manual Spring - step 1
 *
 * @author Florin Manaila (florin.manaila@gmail.com)
 */
public class ApplicationContextProviderInjector implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextProvider.setApplicationContext(applicationContext);
    }

}
