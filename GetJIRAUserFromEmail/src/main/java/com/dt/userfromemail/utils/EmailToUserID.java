package com.dt.userfromemail.utils;

import com.atlassian.crowd.embedded.api.User;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.user.util.UserManager;
import com.dt.userfromemail.rest.JIRAUserModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yagnesh.Bhat on 4/5/2016.
 */
public class EmailToUserID {

    public static JIRAUserModel getUserIDFromEmail(String userEmail) {
        UserManager userManager = ComponentAccessor.getUserManager();
        Collection<ApplicationUser> users = userManager.getUsers();
        Map<String, String> emailUserIDMap = createEmailUserIdHash(users); //Map used for lookup of email id
        JIRAUserModel userModel = null;

        if (emailUserIDMap.containsKey(userEmail.toLowerCase())) {
            userModel = new JIRAUserModel(emailUserIDMap.get(userEmail.toLowerCase()),userEmail);
        }
        //Check for the coxautoinc email ID first - most users seem to have migrated for now.
        if (userModel == null) {
            //Maybe the user has @dealertrack.com email id in hipchat and @coxautoinc.com in jira..
            if (userEmail.toLowerCase().contains("@dealertrack")) {
                userEmail = userEmail.toLowerCase().replace("@dealertrack", "@coxautoinc");
            } else //Or Maybe the user has @dealer.com email id in hipchat and @coxautoinc.com in jira...
            if (userEmail.toLowerCase().contains("@dealer")) {
                userEmail = userEmail.toLowerCase().replace("@dealer", "@coxautoinc");
            }

            if (emailUserIDMap.containsKey(userEmail)) {
                userModel = new JIRAUserModel(emailUserIDMap.get(userEmail.toLowerCase()), userEmail);
            }
        }
        //This check will happen for the accounts not migrated to coxautoinc email ID
        if (userModel == null) {
            //Maybe the user has @dealer.com email id in hipchat and @dealertrack.com in jira...
            if (userEmail.toLowerCase().contains("@dealer")) {
                userEmail = userEmail.toLowerCase().replace("@dealer", "@dealertrack");
                if (emailUserIDMap.containsKey(userEmail)) {
                    userModel = new JIRAUserModel(emailUserIDMap.get(userEmail.toLowerCase()), userEmail);
                }
            }
        }

        return userModel;
    }


    private static Map<String, String> createEmailUserIdHash(Collection<ApplicationUser> users) {
        Map<String, String> emailUserIDHash = new HashMap<>();
        for (ApplicationUser user : users) {
            emailUserIDHash.put(user.getEmailAddress().toLowerCase(), user.getName());
        }

        return  emailUserIDHash;
    }
}
