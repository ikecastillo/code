package ut.com.dt.jira.daterange.plugin;

import com.atlassian.jira.plugin.jql.function.JqlFunctionModuleDescriptor;
import com.atlassian.jira.web.bean.I18nBean;
import org.junit.Test;
import org.junit.Before;
import org.mockito.Mockito;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.atlassian.query.operand.SingleValueOperand;
import com.atlassian.jira.jql.operand.QueryLiteral;
import com.atlassian.jira.util.MessageSet;
import com.atlassian.jira.JiraDataTypes;
import com.atlassian.query.operand.FunctionOperand;

import java.util.Collections;
import java.util.List;

import com.dt.jira.daterange.plugin.jira.jql.DateRangeJqlFunction;


public class DateRangeJqlFunctionTest
{
    private static final String FUNC_NAME = "getIssuesForTimes";
    protected DateRangeJqlFunction function;

   /* @Before
    public void setup() {
        JqlFunctionModuleDescriptor descriptor = mock(JqlFunctionModuleDescriptor.class);
        I18nBean i18nBean = mock(I18nBean.class);

        when(i18nBean.getText(anyString(), anyString(), anyString(), anyString())).thenReturn("Function 'getIssuesForTimes' expected '1' arguments but received '0'.");
        when(descriptor.getI18nBean()).thenReturn(i18nBean);

        function = new DateRangeJqlFunction();
        function.init(descriptor);
    }*/

  /*  @Test
    public void testDataType() throws Exception
    {
        assertEquals(JiraDataTypes.TEXT, function.getDataType());
    }

    @Test
    public void testValidateEmptyArguments() throws Exception
    {
        final FunctionOperand functionOperand = new FunctionOperand(FUNC_NAME);
        final MessageSet messageSet = function.validate(null, functionOperand, null);
        assertTrue(messageSet.hasAnyErrors());
        assertEquals("Function 'getIssuesForTimes' expected '4' arguments but received '0'.", messageSet.getErrorMessages().iterator().next());
    }

    @Test
    public void testValidateArguments() throws Exception
    {
        final FunctionOperand functionOperand = new FunctionOperand(FUNC_NAME,"four");
        final MessageSet messageSet = function.validate(null, functionOperand, null);
        assertFalse(messageSet.hasAnyErrors());
    }

    @Test
    public void testGetValues()
    {
        List<QueryLiteral> actualList;

        actualList = function.getValues(null, new FunctionOperand(FUNC_NAME, "four"), null);
        assertEquals(Collections.singletonList(createLiteral("four")), actualList);
    }

    @Test
    public void testGetMinimumNumberOfExpectedArguments()
    {
        assertEquals(4, function.getMinimumNumberOfExpectedArguments());
    }
*/
    private QueryLiteral createLiteral(String value)
    {
        return new QueryLiteral(new SingleValueOperand(value), value);
    }

}
