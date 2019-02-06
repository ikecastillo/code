package ut.com.dt.jira.generichandler;

import org.junit.Test;
import com.dt.jira.generichandler.MyPluginComponent;
import com.dt.jira.generichandler.MyPluginComponentImpl;

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