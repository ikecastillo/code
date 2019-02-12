package ut.com.dt.jira.plugin.servicedesk.standardbauchange;

import org.junit.Test;
import com.dt.jira.plugin.servicedesk.standardbauchange.MyPluginComponent;
import com.dt.jira.plugin.servicedesk.standardbauchange.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}