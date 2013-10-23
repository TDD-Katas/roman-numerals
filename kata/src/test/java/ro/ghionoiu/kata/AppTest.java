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
        assertThat(new Symbol("I").getValue(), equalTo(1));
    }
    
    @Test
    public void value_of_symbol_V_is_5() {
        assertThat(new Symbol("V").getValue(), equalTo(5));
    }
    
    @Test
    public void value_of_symbol_X_is_10() {
        assertThat(new Symbol("X").getValue(), equalTo(10));
    }
    
    @Test
    public void value_of_symbol_L_is_50() {
        assertThat(new Symbol("L").getValue(), equalTo(50));
    }
    
    @Test
    public void value_of_symbol_C_is_100() {
        assertThat(new Symbol("C").getValue(), equalTo(100));
    }
    
    @Test
    public void value_of_symbol_D_is_500() {
        assertThat(new Symbol("D").getValue(), equalTo(500));
    }
    
    @Test
    public void value_of_symbol_D_is_1000() {
        assertThat(new Symbol("M").getValue(), equalTo(1000));
    }
    
    @Test
    public void value_of_roman_numeral_with_one_symbol_is_that_symbols_value() {
        String romanNumeral = "X";
        
        final int valueOfRomanNumeral = valueOfNumeral(romanNumeral);
        
        assertThat(valueOfRomanNumeral, is(new Symbol(romanNumeral).getValue()));
    }
    
    //~~~~~~~~~~ Production
    
    protected int valueOfNumeral(String romanNumeral) {
        return new Symbol(romanNumeral).getValue();
    }
}