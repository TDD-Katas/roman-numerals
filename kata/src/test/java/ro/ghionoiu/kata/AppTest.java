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
    public void when_smaller_symbol_precedes_bigger_symbol_its_value_is_substracted() {
        final boolean smallerSymbolPrecedesBiggerSymbol = true;
        
        final boolean valueIsSubstracted = smallerSymbolPrecedesBiggerSymbol;
        
        assertThat(valueIsSubstracted, is(true));
    }

    @Test
    public void symbol_is_smaller_then_other_if_has_a_lower_value() {
        Symbol symbol = getSymbolWith(3);
        Symbol otherSymbol = getSymbolWith(4);
        
        final boolean symbolIsSmaller = symbol.getValue() < otherSymbol.getValue();
        
        assertThat(symbolIsSmaller, is(true));
    }
    
    
    //~~~ test utils
    
    protected Symbol getSymbolWith(int symbolsValue) {
        Symbol symbol = mock(Symbol.class);
        when(symbol.getValue()).thenReturn(symbolsValue);
        return symbol;
    }

    protected Numeral constructNumeral(int value1, int value2) {
        Symbol[] symbols = new Symbol[] {
            getSymbolWith(value1),
            getSymbolWith(value2)
        };
        Numeral romanNumeral = new Numeral(symbols);
        return romanNumeral;
    }
}