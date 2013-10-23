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
    public void value_of_symbol_I_is_1() {
        assertThat(valueOfSymbol(new Symbol("I")), equalTo(1));
    }
    
    @Test
    public void value_of_symbol_V_is_5() {
        assertThat(valueOfSymbol(new Symbol("V")), equalTo(5));
    }
    
    @Test
    public void value_of_symbol_X_is_10() {
        assertThat(valueOfSymbol(new Symbol("X")), equalTo(10));
    }
    
    @Test
    public void value_of_symbol_L_is_50() {
        assertThat(valueOfSymbol(new Symbol("L")), equalTo(50));
    }
    
    @Test
    public void value_of_symbol_C_is_100() {
        assertThat(valueOfSymbol(new Symbol("C")), equalTo(100));
    }
    
    @Test
    public void value_of_symbol_D_is_500() {
        assertThat(valueOfSymbol(new Symbol("D")), equalTo(500));
    }
    
    @Test
    public void value_of_symbol_D_is_1000() {
        assertThat(valueOfSymbol(new Symbol("M")), equalTo(1000));
    }
    
    @Test
    public void value_of_roman_numeral_with_one_symbol_is_that_symbols_value() {
        String romanNumeral = "X";
        
        final int valueOfRomanNumeral = valueOfNumeral(romanNumeral);
        
        assertThat(valueOfRomanNumeral, is(valueOfSymbol(new Symbol(romanNumeral))));
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
    
    class Symbol {
        String representation;

        public Symbol(String representation) {
            this.representation = representation;
        }
        
        public int getValue() {
            int value = 0;
            if (SYMBOL_VALUES.containsKey(representation)) {
                value = SYMBOL_VALUES.get(representation) ;
            }
            return value;
        }
    }
    
    protected int valueOfSymbol(Symbol symbol) {
        return symbol.getValue();
    }

    protected int valueOfNumeral(String romanNumeral) {
        return valueOfSymbol(new Symbol(romanNumeral));
    }
}