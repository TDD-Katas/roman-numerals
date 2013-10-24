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
    
    @Test
    public void value_of_numeral_is_sum_of_symbols_values() {
        int value1 = SOME_VALUE;
        int value2 = SOME_VALUE; 
        Numeral romanNumeral = constructNumeral(value1, value2);
        
        final int valueOfRomanNumeral = romanNumeral.getValue();
        
        assertThat(valueOfRomanNumeral, is(value1 + value2));
    }

    @Test
    public void when_value_is_smaller_then_next_value_it_is_substracted() {
        int value = 1;
        int nextValue = 10;
        
        Operation operation = getOperation(value, nextValue);
        
        assertThat(operation, is(Operation.SUBSTRACTION));
    }
    
    @Test
    public void when_value_is_higher_then_next_value_it_is_added() {
        int value = 10;
        int nextValue = 5;
        
        Operation operation = getOperation(value, nextValue);
        
        assertThat(operation, is(Operation.ADDITION));
    }
    
    @Test
    public void when_value_is_equal_to_next_value_it_is_added() {
        int value = 10;
        int nextValue = 10;
        
        Operation operation = getOperation(value, nextValue);
        
        assertThat(operation, is(Operation.ADDITION));
    }
    
    @Test
    public void when_value_has_no_next_value_it_is_added() {
        final boolean valueHasNoNext = true;
        
        Operation operation = null;
        if (valueHasNoNext) {
            operation = Operation.ADDITION;
        }
        
        assertThat(operation, is(Operation.ADDITION));
    }
    
    //~~~ test utils

    protected Numeral constructNumeral(int value1, int value2) {
        int[] values = new int[] {
            value1,
            value2
        };
        return new Numeral(values);
    }
    
    ///~~~~~~~~~~~~~~~~~~~

    protected Operation getOperation(int value, int nextValue) {
        Operation operation = null;
        if (value < nextValue) {
            operation = Operation.SUBSTRACTION;
        } else
        if (value > nextValue) {
            operation = Operation.ADDITION;
        } else 
        if (value == nextValue) {
            operation = Operation.ADDITION;
        }
        
        return operation;
    }
    
    
    enum Operation {
        ADDITION,
        SUBSTRACTION
    }
}