package it.com.dt.jira.plugin.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.dt.jira.plugin.rest.dtrest;
import com.dt.jira.plugin.rest.dtrestModel;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;

public class dtrestFuncTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() {

        String baseUrl = System.getProperty("baseurl");
        String resourceUrl = baseUrl + "/rest/dt/1.0/message";

        RestClient client = new RestClient();
        Resource resource = client.resource(resourceUrl);

        dtrestModel message = resource.get(dtrestModel.class);

        assertEquals("wrong message","Hello World",message.getMessage());
    }
}
