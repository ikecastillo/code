package it.com.dt.jira.xmatters.intgt.plugin.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.dt.jira.xmatters.intgt.plugin.rest.IncidentAlertsRESTModel;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class IncidentAlertsRESTFuncTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    /*@Test
    public void messageIsValid() {

        String baseUrl = System.getProperty("baseurl");
        String resourceUrl = baseUrl + "/rest/incidentalertsrest/1.0/message";

        RestClient client = new RestClient();
        Resource resource = client.resource(resourceUrl);

        IncidentAlertsRESTModel message = resource.get(IncidentAlertsRESTModel.class);

        assertEquals("wrong message","Hello World",message.getJiraIssueKey());
    }*/
}
