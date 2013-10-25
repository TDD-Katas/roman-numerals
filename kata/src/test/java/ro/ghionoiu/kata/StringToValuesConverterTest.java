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
public class StringToValuesConverterTest {
    
    @Test
    public void roman_string_is_converted_into_array_of_values() {
        char char1 = 'X';
        char char2 = 'I';
        String roman = createRomanString(char1, char2);
        CharacterToValueConverter characterConverter = createConverter(char1, char2);
        StringToValuesConverter stringConverter = new StringToValuesConverter(characterConverter);
        
        int[] values = stringConverter.getValues(roman);
        
        for (int i = 0; i < roman.length(); i++) {
            int actualValue = values[i];
            int expected = characterConverter.getValue(roman.charAt(i));
            assertThat(actualValue, is(expected));
        }
    }

    protected String createRomanString(char char1, char char2) {
        return char1 + char2 + "";
    }
    
    protected CharacterToValueConverter createConverter(char char1, char char2) {
        CharacterToValueConverter characterConverter = mock(CharacterToValueConverter.class);
        when(characterConverter.getValue(char1)).thenReturn(1);
        when(characterConverter.getValue(char2)).thenReturn(2);
        return characterConverter;
    }

}