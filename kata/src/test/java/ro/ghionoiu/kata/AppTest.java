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
    public void value_of_numeral_with_one_symbol_is_that_symbols_value() {
        int symbolsValue = SOME_VALUE;
        Symbol symbol = getSymbolWith(symbolsValue);
        Numeral romanNumeral = new Numeral(symbol);
        
        final int valueOfRomanNumeral = romanNumeral.getValue();
        
        assertThat(valueOfRomanNumeral, is(symbolsValue));
    }
    
    @Test
    public void value_of_numeral_is_sum_of_symbols_values() {
        int value1 = SOME_VALUE;
        int value2 = SOME_VALUE; 
        
        Symbol[] symbols = new Symbol[] {
            getSymbolWith(value1),
            getSymbolWith(value2)
        };
        Numeral romanNumeral = new Numeral(symbols);
        
        final int valueOfRomanNumeral = romanNumeral.getValue();
        
        assertThat(valueOfRomanNumeral, is(value1 + value2));
    }

    protected Symbol getSymbolWith(int symbolsValue) {
        Symbol symbol = mock(Symbol.class);
        when(symbol.getValue()).thenReturn(symbolsValue);
        return symbol;
    }
}