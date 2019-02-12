package com.dt.remote.pisdtktcreator.utils;

import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.CustomFieldManager;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.customfields.manager.OptionsManager;
import com.atlassian.jira.issue.customfields.option.LazyLoadedOption;
import com.atlassian.jira.issue.customfields.option.Option;
import com.atlassian.jira.issue.fields.CustomField;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * This class is responsible for creating the JSON required for the creation of PISD Ticket
 * in the service desk.
 *
 * Created by yagnesh.bhat on 6/10/2016.
 */
public class ServiceDeskJSONCreator {

    private final Logger log = LoggerFactory.getLogger(ServiceDeskJSONCreator.class);

    /**
     * Creates a hashmap that has entries like this : 11104|Single Select|Environment => QA.
     * This info is needed to construct the JSON for creating the service desk ticket later.
     *
     * @param customFieldsWithIDs
     * @param issue
     * @return hashmap as explained
     */
    public Map<String, String> lookUpCustomFieldsInIssue(String customFieldsWithIDs, Issue issue) {
        log.info("Looking up custom field values");
        Map<String, String> custFieldIDWithValue = new HashMap<>();
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        log.info("Invoked custom field manager");
        String[] mappingsFromAO = customFieldsWithIDs.split("\\|");
        log.info("Mappings split per pipe, size of array is" + mappingsFromAO.length);
        for (String mappingFromAO : mappingsFromAO) {
            String[] temp = mappingFromAO.split("=");
            log.info("Trying to retrieve the value of custom field " + temp[1]);
            CustomField customField = cfm.getCustomFieldObjectByName(temp[1]);
            String customFieldType = customField.getCustomFieldType().getName();
            if (customFieldType.equalsIgnoreCase("Select List (single choice)")) {
                log.info("Found a custom field of type single select , going ahead to retrieve its value and add to hashmap");
                List<String> custFieldValInIssueList = getSingleSelectValue(issue, customField);
                if (custFieldValInIssueList.size() > 0) {
                    String custFieldValInIssue = custFieldValInIssueList.get(0);
                    log.info("Adding cus field mapping " + temp[0] + "|" + customFieldType + "|" + temp[1] +
                            "->" + custFieldValInIssue);
                    custFieldIDWithValue.put(temp[0] + "|" + customFieldType + "|" + temp[1],custFieldValInIssue);
                }
            } else {
                log.info("Found a custom field of type NOT single select , going ahead to retrieve its value and add to hashmap");
                String custFieldValInIssue = (String) issue.getCustomFieldValue(customField);
                log.info("Adding cus field mapping " + temp[0] + "|" + customFieldType + "|" + temp[1] + "->" + custFieldValInIssue);
                custFieldIDWithValue.put(temp[0] + "|" + customFieldType + "|" + temp[1],custFieldValInIssue);
            }

        }

        return custFieldIDWithValue;
    }

    public String constructJSONPayLoadForPISD(int serviceDeskID, int requestTypeID, String summary, String description,
                                               Map<String, String> custFieldIDWithValue) {
        log.info("INSIDE the method to construct JSON Payload for PISD");
        StringBuffer json = new StringBuffer("{\"serviceDeskId\":");
        json.append(serviceDeskID + ",")
                .append("\"requestTypeId\":" + requestTypeID + ",")
                .append("\"requestFieldValues\": {")
                .append("\"summary\":" + "\""+getRefinedFieldValue(summary)+"\",")
                .append("\"description\":" + "\""+getRefinedFieldValue(description)+"\"");

        if (!custFieldIDWithValue.isEmpty()) {
            log.info("Found custom fields to be appended to payload ..");
            json.append(",");
            String subJsonOfCustomFields = appendCustomFieldValuesToJSON(custFieldIDWithValue);
            json.append(subJsonOfCustomFields);
            log.info("Done with appending custom fields to payload");
            /*.append("\"customfield_11203\":" + "\""+issueLenderPartnerName+"\",")
                    .append("\"customfield_11202\":" + "\""+issueLenderPartnerCode+"\"")*/
        }
        json.append("}")
                .append("}");
				log.info("-----------------------JSON CREATION ----------------------------------");
				log.info(json.toString());
				log.info("----------------------JSON Created successfully------------------------");
        return json.toString();
    }

    /**
     * Helper method to initiate refining of the string values to fit the JSON
     * @param fieldValue
     * @return refined Field Value that fits in JSON well
     */
    private String getRefinedFieldValue(String fieldValue) {
        if (fieldValue != null) {
            fieldValue = fieldValue.replace("\"", "\\\"");
            //Take care of new line characters not impacting JSON
            fieldValue = formatnewLinesToFitInJSON(fieldValue);
        } else {
            fieldValue = "";
        }
        return fieldValue;
    }

    /**
     * Helper method that deals with the issue of new line characters in value while constructing JSON
     * @param value
     * @return refined value that can fit into JSON
     */
    private String formatnewLinesToFitInJSON(String value) {
        String[] lines = value.split("\r\n|\n");
        StringBuilder newValueBuilder = new StringBuilder();

        for(int i = 0; i< lines.length; i++){
            newValueBuilder.append(lines[i]);
            if(i != (lines.length -1)){
                newValueBuilder.append("\\n");
            }

        }
        value = newValueBuilder.toString();
        lines = value.split("\r\t|\t");
        newValueBuilder = new StringBuilder();

        for(int i = 0; i< lines.length; i++){
            newValueBuilder.append(lines[i]);
            if(i != (lines.length -1)){
                newValueBuilder.append("\\t");
            }
        }
        value = newValueBuilder.toString();
        return value;
    }

    private String appendCustomFieldValuesToJSON(Map<String, String> custFieldIDWithValue) {
        log.info("INSIDE Appending custom field values to JSON");
        StringBuffer subJSON = new StringBuffer();
        Iterator it = custFieldIDWithValue.entrySet().iterator();
        CustomFieldManager cfm = ComponentAccessor.getCustomFieldManager();
        while(it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            log.info("Found a custom field object to add to JSON Payload for PISD ticket creation");
            String pipeSeparatedValues = (String) pair.getKey();
            String[] idAndTypeAndName = pipeSeparatedValues.split("\\|");

            //Array will have entries like this : 11104|Single Select|Environment
            String sdCusFieldID = idAndTypeAndName[0];
            String sdCustFieldType = idAndTypeAndName[1];
            String sdCustFieldName = idAndTypeAndName[2];
            CustomField customField = cfm.getCustomFieldObjectByName(sdCustFieldName);
            log.info("Custom field type of field " + sdCustFieldName + " is " + sdCustFieldType);
            if (sdCustFieldType.equalsIgnoreCase("Select List (single choice)")) {
                log.info("Constructing sub JSON for single select");
                subJSON.append("\"customfield_")
                        .append(sdCusFieldID)
                        .append("\": { \"value\" : \""+ getRefinedFieldValue((String)pair.getValue())+"\"},");
            } else {
                log.info("Construction JSON for non single select");
                subJSON.append("\"customfield_")
                        .append(sdCusFieldID)
                        .append("\":" + "\""+getRefinedFieldValue((String)pair.getValue())+"\",");
            }

        }
        String subJSONStr = subJSON.toString();
        if (subJSONStr != null && subJSONStr.length() > 0 && subJSONStr.charAt(subJSONStr.length()-1)==',') {
            subJSONStr = subJSONStr.substring(0, subJSONStr.length()-1);
        }

        log.info("Sub JSON String created for custom fields is " + subJSONStr);
        return subJSONStr;
    }


    /**
     * Get the value for custom field type is single select
     * @param issue
     * @param customField
     * @return list of value(s) for single select - but its size would always be one
     */
    private List<String> getSingleSelectValue(Issue issue, CustomField customField){
        OptionsManager optionsManager = ComponentAccessor.getOptionsManager();
        List<String> values = new ArrayList<>();

        LazyLoadedOption custFieldVal = (LazyLoadedOption)issue.getCustomFieldValue(customField);

        if(custFieldVal!=null && custFieldVal.getOptionId()!=null ){
            Option custFieldOpt= optionsManager.findByOptionId(custFieldVal.getOptionId());
            if(custFieldOpt!=null && custFieldOpt.getValue()!=null){
                String custFieldValue = custFieldOpt.getValue();
                log.info("Value of field " + customField.getName() + " in the issue "+ issue.getKey() +" is "+ custFieldValue);
                values.add(custFieldValue);
            }
        }
        return values;
    }

    public Map<String, Object> parseResponseAndGetTicketAttribute(HttpResponse response) {
        InputStream inputStream = null;
        try {
            inputStream = response.getEntity().getContent();
            byte[] responseBody =  IOUtils.toByteArray(inputStream);
			log.info("The Response body inside ServicedeskJSONCreator.parseResponseAndGetTicketAttribute is:"+ new String(responseBody));
            if(responseBody!=null && responseBody.length > 0){
                ObjectMapper mapper = new ObjectMapper();
                JsonNode rootNode = mapper.readTree(responseBody);
                JsonNode issueKeyNode = rootNode.get("issueKey");
                Map<String, Object> pisdIssueMap = new HashMap<>();
                if (issueKeyNode != null) {
                    String issueKey = issueKeyNode.asText();
                    log.info("Issue Key from the parser " + issueKey);
                    pisdIssueMap.put("issueKey", issueKey);
                }

                JsonNode issueIdNode = rootNode.get("issueId");
                if (issueIdNode != null) {
                    int issueId = issueIdNode.asInt();
                    log.info("Issue Id found in parser is " + issueId);
                    pisdIssueMap.put("issueId",issueId);
                }

                return pisdIssueMap;

            }
        } catch (IOException e) {
		log.info("Error in parseResponseAndGetTicketAttribute due to : " + e.getMessage());
           // e.printStackTrace();
        }


        return null;
    }


}
