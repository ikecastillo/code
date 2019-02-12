package com.dt.plugin.xmattersehandler.test;

import com.dt.plugin.xmattersehandler.impl.EmailParserImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Yagnesh.Bhat on 9/1/2016.
 */
public class EmailParserImplTest {

    private static EmailParserImpl parser;

    @BeforeClass
    public static void setUpBaseClass() {
        //Do the necessary setup here
        parser = new EmailParserImpl();
    }

    @Test
    public void extractIssueKeyFromMailSubject() throws Exception {
        String emailSubject = "New High Incident - Sales and F&I -> Website -> Multiple - External : [ITIM-7683]";
        assertEquals("Issue Key should be ITIM-7683", "ITIM-7683",parser.extractIssueKeyFromMailSubject(emailSubject));
        emailSubject = "New High Incident - Sales and F&I -> Website -> Multiple - External : [ ITIM-7683]";
        assertEquals("Issue Key should be ITIM-7683", "ITIM-7683",parser.extractIssueKeyFromMailSubject(emailSubject));
        emailSubject = "New High Incident - Sales and F&I -> Website -> Multiple - External : [ITIM-7683 ]";
        assertEquals("Issue Key should be ITIM-7683", "ITIM-7683",parser.extractIssueKeyFromMailSubject(emailSubject));
        emailSubject = "New High Incident - Sales and F&I -> Website -> Multiple - External : [ ITIM-7683 ]";
        assertEquals("Issue Key should be ITIM-7683", "ITIM-7683",parser.extractIssueKeyFromMailSubject(emailSubject));
        emailSubject = "New Medium Incident - [DDC XMatters Event] : [ITIM-6551\n" +
                "]";
        assertEquals("Issue Key should be ITIM-6551", "ITIM-6551",parser.extractIssueKeyFromMailSubject(emailSubject));
    }

    @Test
    public void parseAndReturnConfBridgeTypeAndNumbers() throws Exception {
        String parseString = "                  Incident                            Details \n" +
                "     \n" +
                "    Current Status asdfasdfasdfdasf \n" +
                "     \n" +
                "    Customer Impact  \n" +
                "     \n" +
                "    Incident Start 01-Sep-2016 2:00 PM \n" +
                "     \n" +
                "    Incident End  \n" +
                "     \n" +
                "    Incident Duration 1 Hour, 12 Minutes \n" +
                "     \n" +
                "    Clients Impacted External \n" +
                "     \n" +
                "    Solutions Team / Product F&I Solutions (F&I) -> Leads \n" +
                "     \n" +
                "    Incident Status Update \n" +
                "     \n" +
                "    HipChat Room Url No Technical HipChat Room Created \n" +
                "     \n" +
                "    Cause  \n" +
                "     \n" +
                "    Update History <b>@2:31:00 PM EST</b>> sdfgreswtgsdfgdfg<br/><b>@2:30:00 PM EST</b>> asdfqawefadfvadsfqarefadsvds<br/><b>@2:25:00 PM EST</b>> asdfasdfasdfadsf<br/><b>@2:09:00 PM EST</b>> adsfafawevcadasfeawefadscfdasf<br/><b>@2:06:00 PM EST</b>> dasf8asfcvjap9awufeojadsco;lkacd<br/><b>@2:01:00 PM EST</b>> Test new incident - phone numbersTest new incident - phone numbersTest new incident - phone numbers \n" +
                "     \n" +
                "    Management Conference Bridge #  \n" +
                "     \n" +
                "    Incident ID ITIM-6515 \n" +
                "     \n" +
                "     \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Join the conference bridge by clicking  here\n" +
                "    \n" +
                "    \n" +
                "    \n" +
                "    Toll Free Number:  \n" +
                "    +1(877) 823-0708\n" +
                "     \n" +
                "    Bridge Number: 86820998\n" +
                "    \n" +
                "    You can respond to this notification by replying to this email. Replace the subject line of the email with the word RESPONSE followed by a space and then one of the following response choices: Available, Not Available.\n" +
                "    \n" +
                "    Example: RESPONSE Available\n" +
                "    \n";

        Map<String, String> parseConfMap = parser.parseAndReturnConfBridgeTypeAndNumbers(parseString);
        assertEquals("Bridge Type should be Technical","Technical",parseConfMap.get("Bridge Type"));
        assertEquals("Bridge Details should include only Toll Free number and Bridge Number", "Toll Free Number:  \n" +
                "    +1(877) 823-0708\n" +
                "     \n" +
                "    Bridge Number: 86820998", parseConfMap.get("Bridge Details"));


    }

}