
package ro.agrade.jira.qanda.listeners;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Authenticator class for the SMTP server
 *
 * @author Florin Manaila (flo.manaila@gmail.com)
 */
public class SMTPAuthenticator extends Authenticator {

    private String user;
    private String password;

    public SMTPAuthenticator(String username, String password) {
        this.user = username;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }

}
