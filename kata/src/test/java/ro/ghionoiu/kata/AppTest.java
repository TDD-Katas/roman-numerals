/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    
    @Test
    public void value_of_I_is_1() {
        assertThat(valueOf("I"), equalTo(1));
    }
    
    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf("V"), equalTo(5));
    }
    
    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf("X"), equalTo(10));
    }
    
    @Test
    public void value_of_L_is_50() {
        assertThat(valueOf("L"), equalTo(50));
    }
    
    @Test
    public void value_of_C_is_100() {
        assertThat(valueOf("C"), equalTo(100));
    }
    
    @Test
    public void value_of_D_is_500() {
        assertThat(valueOf("D"), equalTo(500));
    }
    
    @Test
    public void value_of_D_is_1000() {
        assertThat(valueOf("M"), equalTo(1000));
    }
    
    @Test
    public void value_of_one_symbol_roman_numeral_is_the_symbols_value() {
        String romanNumeral = "X";
        
        final int valueOfRomanNumeral = valueOf(romanNumeral);
        
        assertThat(valueOfRomanNumeral, is(valueOf(romanNumeral)));
    }
    
    //~~~~~~~~~~ Production

    final static Map<String, Integer> SYMBOL_VALUES = new HashMap<String, Integer>(); 
    static {
        SYMBOL_VALUES.put("I", 1);
        SYMBOL_VALUES.put("V", 5);
        SYMBOL_VALUES.put("X", 10);
        SYMBOL_VALUES.put("L", 50);
        SYMBOL_VALUES.put("C", 100);
        SYMBOL_VALUES.put("D", 500);
        SYMBOL_VALUES.put("M", 1000);
    }
    
    protected int valueOf(String symbol) {
        int value = 0;
        
        if (SYMBOL_VALUES.containsKey(symbol)) {
            value = SYMBOL_VALUES.get(symbol) ;
        }
        
        return value;
    }

}