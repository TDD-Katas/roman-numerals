/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AdjustedValuesProviderTest {
    public static final int SOME_VALUE = 1;
    public static final int FIRST_POSITION = 0;
    
    @Test
    public void return_negative_when_current_value_is_smaller_then_next_value() {
        IterationContext iterationContext = 
                mockIterationContext(SOME_VALUE, SOME_VALUE + 1);
        AdjustedValuesProvider adjustedValuesProvider = new AdjustedValuesProvider();
        
        int adjustedValue = adjustedValuesProvider.compute(iterationContext);
        
        assertThat(adjustedValue, is(-SOME_VALUE));
    }
    
    @Test
    public void return_positive_when_current_value_is_higher_then_next_value() {
        IterationContext iterationContext = 
                mockIterationContext(SOME_VALUE, SOME_VALUE - 1);
        AdjustedValuesProvider adjustedValuesProvider = new AdjustedValuesProvider();
        
        int adjustedValue = adjustedValuesProvider.compute(iterationContext);
        
        assertThat(adjustedValue, is(SOME_VALUE));
    }
    
    @Test
    public void return_positive_when_current_value_is_equal_to_next_value() {
        IterationContext iterationContext = 
                mockIterationContext(SOME_VALUE, SOME_VALUE);
        AdjustedValuesProvider adjustedValuesProvider = new AdjustedValuesProvider();
        
        int adjustedValue = adjustedValuesProvider.compute(iterationContext);
        
        assertThat(adjustedValue, is(SOME_VALUE));
    }
    
    //~~~ Test utils

    protected IterationContext mockIterationContext(int currentValue, int nextValue) {
        IterationContext iterationContext = mock(IterationContext.class);
        when(iterationContext.getCurrentValue()).thenReturn(currentValue);
        when(iterationContext.getNextValue()).thenReturn(nextValue);
        return iterationContext;
    }
}