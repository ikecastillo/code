package ut.com.dt.jira.plugin.event;

import org.junit.Test;
import com.dt.jira.plugin.event.MyPluginComponent;
import com.dt.jira.plugin.event.MyPluginComponentImpl;

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