package ut.com.dt.jira.xmatters.intgt.plugin;

import org.junit.Test;
import com.dt.jira.xmatters.intgt.plugin.MyPluginComponent;
import com.dt.jira.xmatters.intgt.plugin.MyPluginComponentImpl;

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