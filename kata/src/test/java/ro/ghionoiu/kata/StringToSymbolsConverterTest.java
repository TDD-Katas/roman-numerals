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
public class StringToSymbolsConverterTest {
    
    @Test
    public void roman_string_is_converted_into_set_of_symbols() {
        String roman = "XI";
        
        CharacterToValue[] symbols = new StringToSymbolsConverter().convert(roman);
        
        for (int i = 0; i < roman.length(); i++) {
            CharacterToValue symbol = symbols[i];
            char character = roman.charAt(i);
            assertThat(symbol.getCharacter(), is(character));
        }
    }
}