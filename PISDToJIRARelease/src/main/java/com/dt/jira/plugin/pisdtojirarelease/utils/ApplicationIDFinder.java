package com.dt.jira.plugin.pisdtojirarelease.utils;

import com.dt.jira.plugin.pisdtojirarelease.Constants.PISDToJiraConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yagnesh.bhat on 4/25/2016.
 * This class contains the methods to find the remote application Id using Rest Client.
 */
public class ApplicationIDFinder {

    private Map<String,String> applicationIDMap; //Map of entries like "Application Name" -> "baseURL|ApplicationID"
    private String sdApplicationName; //This is configured in the plugin settings factory and got from listener or the action
    private String itsmApplicationName;
    private String itsmBaseURL; //This you get it from the listener or the action class as necessary
    private String tokenId; //For Basic Authentication
    private final Logger log = LoggerFactory.getLogger(ApplicationIDFinder.class);

    public ApplicationIDFinder (String sdApplicationName, String itsmApplicationName, String itsmBaseURL, String tokenID) {
        this.sdApplicationName = sdApplicationName;
        this.itsmApplicationName = itsmApplicationName;
        this.itsmBaseURL = itsmBaseURL;
        this.tokenId = tokenID;
        applicationIDMap = new HashMap<String, String>();
    }

    public Map<String, String> createApplicationIDMap() {
        addApplicationIDMapEntryForServiceDesk();


        return applicationIDMap;
    }

/*This method fetcehes ApplicationId Details usnig Rest Callss*/
    private void addApplicationIDMapEntryForServiceDesk() {
        String url =itsmBaseURL+ PISDToJiraConstants.SERVICE_DESK_APPLICATION_ID_LINK_URL_TRAILER;
        log.info("REMOTE PISD URL TO FIND PISD APPLICATION LINK RELATED DATA IS " + url);
        makeHttpGetRequest(url);
        String sdBaseURL = applicationIDMap.get(sdApplicationName).split("\\|")[0];
        log.info("Service Desk base URL to make the second call is " +  sdBaseURL);

        //Making the second REST call from the Service Desk side to get the application link of ITSM from there
        url = sdBaseURL+"/rest/applinks/1.0/applicationlink";
        log.info("REMOTE PISD URL TO FIND PISD APPLICATION LINK RELATED DATA FROM SERVICE DESK IS " + url);
        makeHttpGetRequest(url);
        cleanupApplicationIDMap();
        log.info("Application ID Map is now ready to be dispatched to the listener or Action . Its size is (must be 2) : " +
                applicationIDMap.size());
    }
/*This method removes all entries except sdApplicationName and  itsmApplicationName from the response entry set*/
    private void cleanupApplicationIDMap() {
        Iterator it = applicationIDMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if (pair.getKey().equals(sdApplicationName) || pair.getKey().equals(itsmApplicationName)) {
                log.info("Got hash map entry for " + pair.getKey() + ", retaining it");
                continue;
            }
            it.remove();
        }
    }
    /*This method builds and calls the HTTP service using client*/

    private void makeHttpGetRequest(String url) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-Type", "application/xml");
        httpGet.setHeader("Authorization", "Basic " + tokenId);
        HttpClient defaultHttpClient =null;

        defaultHttpClient = HttpClientBuilder.create().build();

        HttpResponse response = null;
        try {
            response = defaultHttpClient.execute(httpGet);
        } catch (IOException e) {
            log.error("Exception in getting application link details from jira itsm", e);
        }

        if (response != null && response.getEntity().getContentLength() != 0) {
            log.info("Response from getting ITSM Application Links from ITSM Side " + response.getEntity());
            parseResponse(response.getEntity());
        }


    }

    /*This method parses teh response received from Rest*/

    private void parseResponse(HttpEntity entity) {
        String xml = null;
        try {
            xml = EntityUtils.toString(entity);
        } catch (IOException e) {
            log.error("Unable to convert the response to xml String" , e);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        InputSource is;
        try {
            builder = factory.newDocumentBuilder();
            is = new InputSource(new StringReader(xml));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("applicationLinks");
            for (int i = 0 ; i< list.getLength() ; i++) {
                Node node = list.item(i); //There's only one applicationLinks Node at top level
                NodeList childNodes = node.getChildNodes(); //Get Child applicationLinks Node
                for (int j = 0 ; j <childNodes.getLength(); j++) { //Iterate thru every child appln link and parse their children!
                    Node applnLinkNode = childNodes.item(j);
                    NodeList childrenApplnLink = applnLinkNode.getChildNodes();
                    String name = "", id = "",displayURL = "";
                    for (int k = 0; k < childrenApplnLink.getLength(); k++) {
                        Node appLinkPropNode = childrenApplnLink.item(k);
                        if (appLinkPropNode.getNodeName().equals("name")) {
                            name = appLinkPropNode.getTextContent();
                        } else if (appLinkPropNode.getNodeName().equals("id")) {
                            id = appLinkPropNode.getTextContent();
                        } else if (appLinkPropNode.getNodeName().equals("displayUrl")) {
                            displayURL = appLinkPropNode.getTextContent();
                        }
                    }
                    if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(id) && StringUtils.isNotBlank(displayURL)) {
                        log.info("APPLICATION ID FINDER : Making a hashmap entry with the key " + name + " and value " + displayURL + "|" +id);
                        applicationIDMap.put(name,displayURL + "|" +id);
                    }

                }

            }
            log.info("APPLICATION ID FINDER : Created a hashmap of size " + applicationIDMap.size());
        } catch (ParserConfigurationException e)  {
            log.info("Exception in parsing the xml and creating the required hash map : "+e.getMessage());
        } catch(SAXException sa){
            log.info("Exception in parsing the xml and creating the required hash map : "+sa.getMessage());
        } catch(IOException io){
            log.info("Exception in parsing the xml and creating the required hash map : "+io.getMessage());
        }
    }


}
