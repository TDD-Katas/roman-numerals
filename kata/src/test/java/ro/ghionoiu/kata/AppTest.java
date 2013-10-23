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
        Symbol symbol = getSomeSymbol();
        Numeral romanNumeral = new Numeral(symbol);
        
        final int valueOfRomanNumeral = romanNumeral.getValue();
        
        assertThat(valueOfRomanNumeral, is(symbol.getValue()));
    }

    protected Symbol getSomeSymbol() {
        Symbol symbol = mock(Symbol.class);
        when(symbol.getValue()).thenReturn(SOME_VALUE);
        return symbol;
    }
}