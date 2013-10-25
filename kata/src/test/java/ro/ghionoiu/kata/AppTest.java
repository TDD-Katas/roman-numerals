/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    public static final int SOME_VALUE = 1;
    public static final int FIRST_POSITION = 0;
    
    @Test
    public void value_of_numeral_is_sum_of_each_value_in_array() {
        ValueArray valueArray = new ValueArray(SOME_VALUE, SOME_VALUE);
        Numeral romanNumeral = new Numeral(valueArray);
        
        final int valueOfRomanNumeral = romanNumeral.getValue();
        
        assertThat(valueOfRomanNumeral, is(valueArray.getValueAt(0) + valueArray.getValueAt(1)));
    }
    
    @Test
    public void interation_context_current_value_is_value_of_position() {
        ValueArray valueArray = new ValueArray(SOME_VALUE);
        int position = FIRST_POSITION;
        
        IterationContext iterationContext = new IterationContext(valueArray, position);
        int currentValue = iterationContext.getCurrentValue();
        
        assertThat(currentValue, is(valueArray.getValueAt(position)));
    }
    
    @Test
    public void interation_context_next_value_for_position_is_value_of_next_position() {
        ValueArray valueArray = new ValueArray(0 , SOME_VALUE);
        int position = FIRST_POSITION;
        
        IterationContext iterationContext = new IterationContext(valueArray, position);
        int nextValue = iterationContext.getNextValue();
        
        assertThat(nextValue, is(valueArray.getValueAt(position + 1)));
    }
    
    @Test
    public void iteation_context_next_value_for_last_position_is_zero() {
        ValueArray valueArray = new ValueArray(SOME_VALUE);
        int lastPosition = valueArray.getSize() - 1;
        
        IterationContext iterationContext = new IterationContext(valueArray, lastPosition);
        int nextValue = iterationContext.getNextValue();
        
        assertThat(nextValue, is(0));
    }
    
    @Test
    public void adjusted_value_is_negative_when_current_value_is_smaller_then_next_value() {
        IterationContext iterationContext = 
                createIterationContext(SOME_VALUE, SOME_VALUE + 1);
        
        int adjustedValue = getAdjustedValue(iterationContext);
        
        assertThat(adjustedValue, is(-SOME_VALUE));
    }
    
    @Test
    public void adjusted_value_is_positive_when_current_value_is_higher_then_next_value() {
        IterationContext iterationContext = 
                createIterationContext(SOME_VALUE, SOME_VALUE - 1);
        
        int adjustedValue = getAdjustedValue(iterationContext);
        
        assertThat(adjustedValue, is(SOME_VALUE));
    }
    
    @Test
    public void adjusted_value_is_positive_when_current_value_is_equal_to_next_value() {
        IterationContext iterationContext = 
                createIterationContext(SOME_VALUE, SOME_VALUE);
        
        int adjustedValue = getAdjustedValue(iterationContext);
        
        assertThat(adjustedValue, is(SOME_VALUE));
    }
    
    //~~~ test utils

    protected Numeral numeralWithValues(int value1, int value2) {
        int[] values = new int[] {
            value1,
            value2
        };
        return new Numeral(new ValueArray(values));
    }

    
    ///~~~~~~~~~~~~~~~~~~~

    protected int getAdjustedValue(IterationContext iterationContext) {
        int currentValue = iterationContext.getCurrentValue();
        int nextValue = iterationContext.getNextValue();
        int adjustedValue = currentValue;
        
        if (currentValue < nextValue) {
            adjustedValue = -currentValue;
        } 
        
        return adjustedValue;
    }

    protected IterationContext createIterationContext(int currentValue, int nextValue) {
        IterationContext iterationContext = mock(IterationContext.class);
        when(iterationContext.getCurrentValue()).thenReturn(currentValue);
        when(iterationContext.getNextValue()).thenReturn(nextValue);
        return iterationContext;
    }
    
    class IterationContext {
        ValueArray valueArray;
        int position;

        public IterationContext(ValueArray valueArray, int position) {
            this.valueArray = valueArray;
            this.position = position;
        }
    
        protected int getCurrentValue() {
            return valueArray.getValueAt(position);
        }

        protected int getNextValue() {
            int nextValue = 0;
            int nextPosition = position + 1;

            if (nextPosition < valueArray.getSize()) {
                nextValue = valueArray.getValueAt(nextPosition);
            }

            return nextValue;
        }
         
    }
}