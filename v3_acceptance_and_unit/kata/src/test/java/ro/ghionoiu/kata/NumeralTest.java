/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import ro.ghionoiu.kata.compute.ValuesAdjuster;
import ro.ghionoiu.kata.compute.IterationContext;
import ro.ghionoiu.kata.data.ValueArray;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class NumeralTest {
    public static final int SOME_VALUE = 1;
    public static final int FIRST_POSITION = 0;
    
    @Test
    public void value_of_numeral_is_sum_of_each_value_adjusted_according_to_context() {
        int adjustedValue = 1;
        int numberOfValues = 2;
        ValuesAdjuster adjustedValuesProvider = mockValueAdjuster(adjustedValue);
        Numeral romanNumeral = new Numeral(valueArrayOfSize(numberOfValues),
                adjustedValuesProvider);
        
        final int valueOfRomanNumeral = romanNumeral.getValue();
        
        assertThat(valueOfRomanNumeral, is(numberOfValues*adjustedValue));
    }
    
    //~~~ test utils
    
    protected Numeral numeralWithSize(int numberOfValues) {
        int[] values = new int[numberOfValues];
        return new Numeral(new ValueArray(values));
    }
    
    protected Numeral numeralWithValues(int value1, int value2) {
        int[] values = new int[] {
            value1,
            value2
        };
        return new Numeral(new ValueArray(values));
    }
    
    protected ValueArray valueArrayOfSize(int numberOfValues) {
        return new ValueArray(new int[numberOfValues]);
    }

    protected ValuesAdjuster mockValueAdjuster(int adjustedValue) {
        ValuesAdjuster adjustedValuesProvider = mock(ValuesAdjuster.class);
        when(adjustedValuesProvider.compute(any(IterationContext.class))).thenReturn(adjustedValue);
        return adjustedValuesProvider;
    }
}