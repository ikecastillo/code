package ut.com.dt.jira.plugin.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.dt.jira.plugin.rest.dtrest;
import com.dt.jira.plugin.rest.dtrestModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

public class dtrestTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() {
        dtrest resource = new dtrest();

        Response response = resource.getMessage();
        final dtrestModel message = (dtrestModel) response.getEntity();

        assertEquals("wrong message","Hello World",message.getMessage());
    }
}
