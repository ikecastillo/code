package ut.com.dt.jira.multilevelcascadedepth;

import org.junit.Test;
import com.dt.jira.multilevelcascadedepth.api.MyPluginComponent;
import com.dt.jira.multilevelcascadedepth.impl.MyPluginComponentImpl;

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