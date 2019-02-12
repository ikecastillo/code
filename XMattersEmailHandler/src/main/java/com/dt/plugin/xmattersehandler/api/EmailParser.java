package com.dt.plugin.xmattersehandler.api;

import java.util.Map;

/**
 * Created by Yagnesh.Bhat on 9/1/2016.
 */
public interface EmailParser {
    Map<String, String> parseAndReturnConfBridgeTypeAndNumbers(String xMattersEmailBody);
    String extractIssueKeyFromMailSubject(String xMattersEmailSubject);
}
