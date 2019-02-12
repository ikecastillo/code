package ut.com.dt.userfromemail;

import org.junit.Test;
import com.dt.userfromemail.MyPluginComponent;
import com.dt.userfromemail.MyPluginComponentImpl;

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