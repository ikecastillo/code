package ut.com.dt.jira.xmatters.intgt.plugin.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.dt.jira.xmatters.intgt.plugin.rest.IncidentAlertsREST;
import com.dt.jira.xmatters.intgt.plugin.rest.IncidentAlertsRESTModel;
import javax.ws.rs.core.Response;

public class IncidentAlertsRESTTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    /*@Test
    public void messageIsValid() {
        IncidentAlertsREST resource = new IncidentAlertsREST();

        Response response = resource.getMessage();
        final IncidentAlertsRESTModel message = (IncidentAlertsRESTModel) response.getEntity();

        assertEquals("wrong message","Hello World",message.getJiraIssueKey());
    }*/
}
