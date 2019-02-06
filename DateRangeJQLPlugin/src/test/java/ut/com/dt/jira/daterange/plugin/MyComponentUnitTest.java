package ut.com.dt.jira.daterange.plugin;

import org.junit.Test;
import com.dt.jira.daterange.plugin.MyPluginComponent;
import com.dt.jira.daterange.plugin.MyPluginComponentImpl;

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