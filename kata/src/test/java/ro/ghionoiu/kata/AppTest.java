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
    public void roman_string_is_a_set_of_symbol() {
        String roman = "XI";
        
        Symbol[] symbols = numeralFromString(roman);
        
        for (int i = 0; i < roman.length(); i++) {
            Symbol symbol = symbols[i];
            char character = roman.charAt(i);
            assertThat(symbol.getCharacter(), is(character));
        }
    }
    
    @Test
    public void value_of_numeral_is_sum_of_symbols_values() {
        int value1 = SOME_VALUE;
        int value2 = SOME_VALUE; 
        Numeral romanNumeral = constructNumeral(value1, value2);
        
        final int valueOfRomanNumeral = romanNumeral.getValue();
        
        assertThat(valueOfRomanNumeral, is(value1 + value2));
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
    
    //~~~~~~

    protected Symbol[] numeralFromString(String roman) {
        Symbol[] symbols = new Symbol[roman.length()];
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = new Symbol(roman.charAt(i));
        }
        return symbols;
    }
}