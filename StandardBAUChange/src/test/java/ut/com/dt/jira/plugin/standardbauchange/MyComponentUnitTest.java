package ut.com.dt.jira.plugin.standardbauchange;

import org.junit.Test;
import com.dt.jira.plugin.standardbauchange.MyPluginComponent;
import com.dt.jira.plugin.standardbauchange.MyPluginComponentImpl;

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