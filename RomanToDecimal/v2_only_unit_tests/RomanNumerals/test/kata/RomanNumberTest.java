/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import kata.symbols.Symbol;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import static kata.MocksAndStubsUtils.*;
import static kata.symbols.KnownRomanSymbols.*;


/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class RomanNumberTest {

    @Test
    public void a_roman_number_is_a_list_of_roman_symbols() {
        String symbol1 = "I";
        String symbol2 = "X";
        String romanNumeral = symbol1 + symbol2;
        Symbol[] expectedSymbols = new Symbol[] {
            asRomanSymbol(symbol1), 
            asRomanSymbol(symbol2)
        };
        
        RomanNumber romanNumber = new RomanNumber(romanNumeral);
        
        assertArrayEquals(expectedSymbols, romanNumber.getSymbols());
    }
    
    @Test
    public void value_of_roman_number_is_sum_of_context_values_of_symbols() {
        int[] contextValues = {1, 2}; 
        Symbol[] symbols = mockRomanNumeral(contextValues.length);
        ContextValueProvider valueProvider = mockContextValueProvider(symbols, contextValues);

        RomanNumber romanNumber = new RomanNumber(symbols);
        int value = romanNumber.computeIntegerValue(valueProvider);

        assertThat(value, is(contextValues[0]+contextValues[1]));
    }
}