package com.dt.jira.xmatters.intgt.plugin.business;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.IssueManager;
import com.atlassian.jira.issue.fields.CustomField;
import org.joda.time.Days;
import org.joda.time.LocalDateTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang.StringUtils.isNotBlank;

/**
 * This important class contains the business logic to determine whether the incident has to be alerted or not.
 * Basically, we compare the xMatters log timings with the current time and only if the difference is <=30, we
 * consider the incident to alert. If the incident needs to be alerted we send its summary to the REST Model,
 * which then pairs it with its corresponding incident key and sends the incident in the list of incidents to
 * be alerted.
 *
 * Created by yagnesh.bhat on 7/14/2015.
 */
public class xMattersLogFilterer {

    private String xMattersLogValue;
    private final Logger logger = LoggerFactory.getLogger(xMattersLogFilterer.class);

    /**
     * Checks whether the incident needs to be alerted based on its xMatters Log Field
     *
     * @param incidentID the ticket number of the incident
     * @param issueManager issue manager object
     *
     * @return summary of an incident to be alerted else null
     */
    public String getSummaryBasedOnXMattersLogs(String incidentID, IssueManager issueManager) {
        Issue issue = issueManager.getIssueObject(incidentID);
        CustomField xMattersLogField = ComponentAccessor.getCustomFieldManager()
                                                        .getCustomFieldObjectByName("XMatters Log");
        boolean sendSummary = false;
        xMattersLogValue =  (String)issue.getCustomFieldValue(xMattersLogField);
        if (isNotBlank(xMattersLogValue)) {
            logger.debug("CHECKING xMATTERS LOGS FOR INCIDENT : " + incidentID );
            sendSummary = areXMLogsLessThanOrEq30mins(xMattersLogValue);
        }

        logger.debug("IS SUMMARY IS " + sendSummary);
        logger.debug("*********************************************************************************************");
        if (sendSummary) {
            return issue.getSummary();
        } else {
            return null;
        }

    }


    /**
     * Helper method for the method getSummaryBasedOnXMattersLogs that decides whether an incident needs to be alerted
     * or not.
     *
     * @param xMattersLogValue
     * @return true or false value that decides whether an incident is an alert or not
     */
    private boolean areXMLogsLessThanOrEq30mins(String xMattersLogValue) {
        Document doc = Jsoup.parse(xMattersLogValue);
        Elements logtimes = doc.select("table td"); //Unfortunately td:nth-child(0) is not supported in Jsoup :(
        List<LocalDateTime> dateTimes = new ArrayList<>();

        DateTimeFormatter formatter = DateTimeFormat.forPattern("MMM dd, YYYY hh:mm aa");
        for(Element element : logtimes) {
            try {
                LocalDateTime dateTime = formatter.parseLocalDateTime(element.text());
                logger.debug("Element parsed successfully is " + element.text());
                dateTimes.add(dateTime);
            } catch (IllegalArgumentException e) {
                continue;
            }
        }

        boolean isIncidentAlert = false;
        LocalDateTime currentTime = LocalDateTime.now();

        if (dateTimes.size() > 0) {
            for (LocalDateTime dateTime : dateTimes) {
                Days days = Days.daysBetween(dateTime, currentTime);
                logger.debug("Days difference is " + Math.abs(days.getDays()));
                //Go to calculate the minutes only if the number of days is 1 or 0
                // (1 because, consider time 12 PM - 1 AM ! )
                if (Math.abs(days.getDays()) <= 1) {
                    Minutes minutes = Minutes.minutesBetween(dateTime , currentTime);
                    logger.debug("Time difference in minutes using Minutes Class is " + Math.abs(minutes.getMinutes()) +
                            " mins.");
                    if (Math.abs(minutes.getMinutes()) >= 30) {
                        isIncidentAlert = true;
                        break;
                    }
                }
            }
        }
        return isIncidentAlert;
    }

}
