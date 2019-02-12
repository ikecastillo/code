package ut.com.dt.jirasdfieldmapper;

import org.junit.Test;
import com.dt.jirasdfieldmapper.MyPluginComponent;
import com.dt.jirasdfieldmapper.MyPluginComponentImpl;

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