package ut.com.dt.remote.pisdtktcreator;

import org.junit.Test;
import com.dt.remote.pisdtktcreator.MyPluginComponent;
import com.dt.remote.pisdtktcreator.MyPluginComponentImpl;

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