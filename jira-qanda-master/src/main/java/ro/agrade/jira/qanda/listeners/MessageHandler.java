
package ro.agrade.jira.qanda.listeners;

import com.atlassian.jira.user.ApplicationUser;

import ro.agrade.jira.qanda.QandAEvent;

/**
 * That's the message handler interface
 *
 * @author Radu Dumitriu (rdumitriu@gmail.com)
 * @since 1.0
 */
public interface MessageHandler {

    /**
     * Handles the message
     *
     * @param user the user
     * @param qaEvent the event
     */
    public abstract void handleMessage(ApplicationUser user, QandAEvent qaEvent);
}
