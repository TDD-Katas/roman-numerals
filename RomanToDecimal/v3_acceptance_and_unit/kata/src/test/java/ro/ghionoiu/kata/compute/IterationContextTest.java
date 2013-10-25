/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata.compute;

import ro.ghionoiu.kata.compute.IterationContext;
import ro.ghionoiu.kata.data.ValueArray;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class IterationContextTest {
    public static final int SOME_VALUE = 1;
    public static final int FIRST_POSITION = 0;
    
    @Test
    public void current_value_is_value_at_position() {
        ValueArray valueArray = new ValueArray(SOME_VALUE);
        int position = FIRST_POSITION;
        
        int currentValue = getCurrentValue(valueArray, position);
        
        assertThat(currentValue, is(valueArray.getValueAt(position)));
    }
    
    @Test
    public void next_value_is_value_at_next_position() {
        ValueArray valueArray = new ValueArray(0 , SOME_VALUE);
        int position = FIRST_POSITION;
        int nextPosition = position + 1;
        
        int nextValue = getNextValue(valueArray, position);
        
        assertThat(nextValue, is(valueArray.getValueAt(nextPosition)));
    }
    
    @Test
    public void next_value_for_last_position_is_zero() {
        ValueArray valueArray = new ValueArray(SOME_VALUE);
        int lastPosition = valueArray.getSize() - 1;
        
        int nextValue = getNextValue(valueArray, lastPosition);
        
        assertThat(nextValue, is(0));
    }

    //~~~~~~~~~
    
    protected int getCurrentValue(ValueArray valueArray, int position) {
        IterationContext iterationContext = new IterationContext(valueArray, position);
        return iterationContext.getCurrentValue();
    }

    protected int getNextValue(ValueArray valueArray, int position) {
        IterationContext iterationContext = new IterationContext(valueArray, position);
        return iterationContext.getNextValue();
    }
}