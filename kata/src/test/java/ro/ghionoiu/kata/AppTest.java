/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

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
    public void adjusted_value_is_negative_when_current_value_is_smaller_then_next_value() {
        int currentValue = 1;
        int nextValue = 10;
        
        int adjustedValue = getAdjustedValue(currentValue, nextValue);
        
        assertThat(adjustedValue, is(-currentValue));
    }
    
    @Test
    public void adjusted_value_is_positive_when_current_value_is_higher_then_next_value() {
        int currentValue = 10;
        int nextValue = 5;
        
        int adjustedValue = getAdjustedValue(currentValue, nextValue);
        
        assertThat(adjustedValue, is(currentValue));
    }
    
    @Test
    public void adjusted_value_is_positive_when_current_value_is_equal_to_next_value() {
        int currentValue = 10;
        int nextValue = 10;
        
        int adjustedValue = getAdjustedValue(currentValue, nextValue);
        
        assertThat(adjustedValue, is(currentValue));
    }
    
    @Test
    public void adjusted_value_is_positive_when_current_value_has_no_next_value() {
        boolean valueHasNoNext = true;
        int currentValue = SOME_VALUE;
        
        int adjustedValue = 0;
        if (valueHasNoNext) {
            adjustedValue = currentValue;
        }
        
        assertThat(adjustedValue, is(currentValue));
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

    enum Sign {
        PLUS(1),
        MINUS(-1);
        
        int mutiplier;

        private Sign(int mutiplier) {
            this.mutiplier = mutiplier;
        }
        
        private int adjust(int value) {
            return mutiplier*value;
        }
        
    }
    
    protected int getAdjustedValue(int currentValue, int nextValue) {
        int adjustedValue = currentValue;
        
        if (currentValue < nextValue) {
            adjustedValue = -currentValue;
        } 
        
        return adjustedValue;
    }
}