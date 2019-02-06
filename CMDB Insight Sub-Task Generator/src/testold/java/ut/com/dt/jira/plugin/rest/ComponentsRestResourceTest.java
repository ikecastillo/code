package ut.com.dt.jira.plugin.rest;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.dt.jira.plugin.rest.ComponentsRestResource;
import com.dt.jira.plugin.rest.ComponentsRestResourceModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.GenericEntity;

public class ComponentsRestResourceTest {

    @Before
    public void setup() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void messageIsValid() {
        ComponentsRestResource resource = new ComponentsRestResource();

        Response response = resource.getMessage();
        final ComponentsRestResourceModel message = (ComponentsRestResourceModel) response.getEntity();

        assertEquals("wrong message","Hello World",message.getMessage());
    }
}
