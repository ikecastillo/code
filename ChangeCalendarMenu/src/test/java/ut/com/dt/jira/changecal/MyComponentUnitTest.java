package ut.com.dt.jira.changecal;

import org.junit.Test;
import com.dt.jira.changecal.MyPluginComponent;
import com.dt.jira.changecal.MyPluginComponentImpl;

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