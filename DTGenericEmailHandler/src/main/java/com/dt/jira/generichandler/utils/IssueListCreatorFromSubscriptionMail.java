package com.dt.jira.generichandler.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Utility class to extract the Issue Keys from subscription mail body. Basically, what we do is in the
 * subscription mail, we pick all the URLs , and then filter out the issue keys from those URLs in a set.
 * Then we return this set to the handler code which takes care of transitioning the issue.
 *
 * Created by yagnesh.bhat on 2/17/2016.
 */
public class IssueListCreatorFromSubscriptionMail {

    public static Set<String> getListOfIssuesFromSubscriptionMail(String mailBody, String projectKey) {
       Set<String> issueURLSet = new HashSet<>();

        //Typical Regex to match the URL - this one is supposed to have passed 350 line unit test!
        //Ref : http://stackoverflow.com/a/5713866/4099976
        Matcher urlMatcher = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL)
                                    .matcher(mailBody);
        while(urlMatcher.find()){
            issueURLSet.add(urlMatcher.group());
        }

        //Pick only the URLs containing the project key
        issueURLSet = issueURLSet.stream()
                .filter(s -> s.contains(projectKey))
                .collect(Collectors.toSet());

        //Then from those URLs, pick out only the issue keys and return that!
        Set<String> issueKeySet = new HashSet<>();
        for (String issueURL : issueURLSet) {
            String issueKey = issueURL.replaceFirst(".*/(\\w+)","$1");
            issueKeySet.add(issueKey);
        }

        return issueKeySet;
    }

    /**
     * A Sample Unit test to check if it picks up the issue-keys successfully.
     * Will probably delete this after the code is prod ready.
     *
     * @param args
     */
    public static void main(String[] args) {
        String mailBody = "approved\n" +
                "    \n" +
                "    Â \n" +
                "    \n" +
                "    From: DTServiceDesk@dealertrack.com [mailto:DTServiceDesk@dealertrack.com]\n" +
                "    Sent: Wednesday, February 17, 2016 2:01 PM\n" +
                "    To: PavanKumar Pinniboyina <PavanKumar.Pinniboyina@coxautoinc.com>\n" +
                "    Subject: Alert : DT Service Desk Clone - Subscription: Critical Access DB tickets awaiting owner approval\n" +
                "    \n" +
                "    Â \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Â   \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Â  \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Issue Subscription\n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Filter: \n" +
                "    \n" +
                "    [1]Critical Access DB tickets awaiting owner approval (2 issues) \n" +
                "    \n" +
                "    \n" +
                "    Subscriber: \n" +
                "    \n" +
                "    Yagnesh.Bhat \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    T \n" +
                "    \n" +
                "    Key \n" +
                "    \n" +
                "    Summary \n" +
                "    \n" +
                "    Assignee \n" +
                "    \n" +
                "    Reporter \n" +
                "    \n" +
                "    P \n" +
                "    \n" +
                "    Status \n" +
                "    \n" +
                "    Resolution \n" +
                "    \n" +
                "    Created \n" +
                "    \n" +
                "    Updated \n" +
                "    \n" +
                "    Due \n" +
                "    \n" +
                "    \n" +
                "    [2]\n" +
                "    \n" +
                "    [3]CSAR-11 \n" +
                "    \n" +
                "    [4]Test close 2 \n" +
                "    \n" +
                "    Unassigned\n" +
                "    \n" +
                "    [5]Yagnesh Bhat\n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Pending Owner Approval\n" +
                "    \n" +
                "    Unresolved\n" +
                "    \n" +
                "    17/Feb/16 \n" +
                "    \n" +
                "    17/Feb/16 \n" +
                "    \n" +
                "    Â  \n" +
                "    \n" +
                "    \n" +
                "    [6]\n" +
                "    \n" +
                "    [7]CSAR-10 \n" +
                "    \n" +
                "    [8]Test close 1 \n" +
                "    \n" +
                "    Unassigned\n" +
                "    \n" +
                "    [9]Yagnesh Bhat\n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Pending Owner Approval\n" +
                "    \n" +
                "    Unresolved\n" +
                "    \n" +
                "    17/Feb/16 \n" +
                "    \n" +
                "    17/Feb/16 \n" +
                "    \n" +
                "    Â  \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    You may edit this subscription [10]here. \n" +
                "    \n" +
                "    \n" +
                "    Â  \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    This message was sent by Atlassian JIRA (v7.0.2#70111-sha1:88534db) \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Â \n" +
                "    ----------------------------------------------------------------------------------------\n" +
                "    [1] http://10.134.9.129:8080/secure/IssueNavigator.jspa?mode=hide&requestId=10300\n" +
                "    [2] http://10.134.9.129:8080/browse/CSAR-11\n" +
                "    [3] http://10.134.9.129:8080/browse/CSAR-11\n" +
                "    [4] http://10.134.9.129:8080/browse/CSAR-11\n" +
                "    [5] http://10.134.9.129:8080/secure/ViewProfile.jspa?name=Yagnesh.Bhat\n" +
                "    [6] http://10.134.9.129:8080/browse/CSAR-10\n" +
                "    [7] http://10.134.9.129:8080/browse/CSAR-10\n" +
                "    [8] http://10.134.9.129:8080/browse/CSAR-10\n" +
                "    [9] http://10.134.9.129:8080/secure/ViewProfile.jspa?name=Yagnesh.Bhat\n" +
                "    [10] http://10.134.9.129:8080/secure/EditSubscription!default.jspa?subId=10100&filterId=10300";

        Set<String> issueKeySet = getListOfIssuesFromSubscriptionMail(mailBody, "CSAR");
        issueKeySet.stream().forEach( s -> System.out.println(s));
    }
}
