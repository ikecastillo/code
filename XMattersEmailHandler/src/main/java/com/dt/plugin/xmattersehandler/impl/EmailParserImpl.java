package com.dt.plugin.xmattersehandler.impl;

import com.dt.plugin.xmattersehandler.api.EmailParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the all important Email Parser class thats responsible for parsing the email sent by xmatters and
 * extracting the conference bridge numbers.
 * Created by Yagnesh.Bhat on 9/1/2016.
 */
public class EmailParserImpl implements EmailParser {

    private final Logger log = LoggerFactory.getLogger(EmailParserImpl.class);

    @Override
    public Map<String, String> parseAndReturnConfBridgeTypeAndNumbers(String xMattersEmailBody) {
        log.info("------------------------Mail body is :");
        log.info(xMattersEmailBody);
        Map<String, String> parseConfMap = new HashMap<>();

        if (xMattersEmailBody.contains("HipChat Room Url")) {
            int indexOfHCRoomUrl = xMattersEmailBody.indexOf("HipChat Room Url");
            String bridgeTypeDetector = xMattersEmailBody.substring(indexOfHCRoomUrl);
            //String hipChatRoomLine = substringBefore(bridgeTypeDetector,"\\n");
            String hipChatRoomLine = bridgeTypeDetector.substring(0, bridgeTypeDetector.indexOf("\n"));
            log.info("-----------------EMAILPARSERIMPL : Hipchat room line is " + hipChatRoomLine);
            if(hipChatRoomLine.contains("Technical")) {
                log.info("------------------EMAILPARSERIMPL : Looks like this is Technical Bridge");
                parseConfMap.put("Bridge Type", "Technical");
            } else if (hipChatRoomLine.contains("Management")) {
                log.info("----------------EMAILPARSERIMPL : Looks like this is Management Bridge");
                parseConfMap.put("Bridge Type", "Management");
            }
        }

        if (xMattersEmailBody.contains("Toll Free Number")) {
            int lastIndexOfTollFreeNumber = xMattersEmailBody.lastIndexOf("Toll Free Number");
            String tollFreeNumberDetector = xMattersEmailBody.substring(lastIndexOfTollFreeNumber);
            int indexOFWordYou = tollFreeNumberDetector.indexOf("You");
            log.info("-----------------------------Index of word You is : " + indexOFWordYou);

            //If the word 'You' is found after toll free  number, then probably thats the bridge to
            //pick up.
            if (indexOFWordYou > 0) {
                String bridgeDetails = tollFreeNumberDetector.substring(0,indexOFWordYou).trim();
                log.info("-----------------------EMAILPARSER IMPL : Bridge Details line is :" + bridgeDetails);
                parseConfMap.put("Bridge Details", bridgeDetails);
            }
        }

        return parseConfMap;
    }

    /**
     * Extracts the issue key from the email subject thats sent by XMatters
     *
     * @param xMattersEmailSubject
     * @return issueKey
     */
    @Override
    public String extractIssueKeyFromMailSubject(String xMattersEmailSubject) {
	String parsedEmailSubject = "";
	if(xMattersEmailSubject!=null && xMattersEmailSubject.contains("[") && xMattersEmailSubject.contains("]") ){
	parsedEmailSubject = xMattersEmailSubject.substring(xMattersEmailSubject.lastIndexOf("[") + 1,
                xMattersEmailSubject.lastIndexOf("]")).trim();
				//log.info("--------------parsedEmailSubject:"+parsedEmailSubject);
	}else{
	parsedEmailSubject = "";
	}
        return parsedEmailSubject;
    }
}
