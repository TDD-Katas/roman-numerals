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
    public void when_current_value_is_smaller_then_next_value_operator_is_minus() {
        int currentValue = 1;
        int nextValue = 10;
        
        Operator operator = getAdjustmentOperator(currentValue, nextValue);
        
        assertThat(operator, is(Operator.MINUS));
    }
    
    @Test
    public void when_current_value_is_higher_then_next_value_operator_is_plus() {
        int currentValue = 10;
        int nextValue = 5;
        
        Operator operation = getAdjustmentOperator(currentValue, nextValue);
        
        assertThat(operation, is(Operator.PLUS));
    }
    
    @Test
    public void when_current_value_is_equal_to_next_value_operator_is_plus() {
        int currentValue = 10;
        int nextValue = 10;
        
        Operator operation = getAdjustmentOperator(currentValue, nextValue);
        
        assertThat(operation, is(Operator.PLUS));
    }
    
    @Test
    public void when_current_value_has_no_next_value_operator_is_plus() {
        boolean valueHasNoNext = true;
        
        Operator operation = null;
        if (valueHasNoNext) {
            operation = Operator.PLUS;
        }
        
        assertThat(operation, is(Operator.PLUS));
    }
    
    @Test
    public void adjusted_value_is_current_value_multiplied_with_operator() {
        int operator = 1;
        int currentValue = 1;
        
        int adjustedValue = operator*currentValue;
        
        assertThat(adjustedValue, is(operator*currentValue));
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

    enum Operator {
        PLUS,
        MINUS
    }
    
    protected Operator getAdjustmentOperator(int value, int nextValue) {
        Operator operation = Operator.PLUS;
        
        if (value < nextValue) {
            operation = Operator.MINUS;
        } 
        
        return operation;
    }
}