/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {

    @Test
    public void value_of_I_is_1() {
        assertThat(valueOf("I"), is(1));
    }

    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf("V"), is(5));
    }

    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf("X"), is(10));
    }

    @Test
    public void value_of_L_is_50() {
        assertThat(valueOf("L"), is(50));
    }

    @Test
    public void value_of_C_is_100() {
        assertThat(valueOf("C"), is(100));
    }

    @Test
    public void value_of_D_is_500() {
        assertThat(valueOf("D"), is(500));
    }

    @Test
    public void value_of_M_is_1000() {
        assertThat(valueOf("M"), is(1000));
    }
    
    @Test
    public void value_is_computed_by_adding_symbols_values() {
        int valueOfSymbol1 = 1;
        int valueOfSymbol2 = 2;
        
        int computedValue = valueOfSymbol1+valueOfSymbol2;
        
        assertThat(computedValue, is(valueOfSymbol1+valueOfSymbol2));
    }
    
    @Test
    public void I_can_be_placed_before_V() {
        String leftSymbol = "I";
        String rightSymbol = "V";
        
        boolean canBePlaced = canSymbolBePlacedBefore(rightSymbol, leftSymbol);
        
        assertThat(canBePlaced, is(true));
    }
    
    @Test
    public void I_can_be_placed_before_X() {
        String leftSymbol = "I";
        String rightSymbol = "X";
        
        boolean canBePlaced = canSymbolBePlacedBefore(rightSymbol, leftSymbol);
        
        assertThat(canBePlaced, is(true));
    }
    
    
    //~~~~~~~

    protected int valueOf(String symbol) {
        int symbolValue = 0;
        if ("I".equals(symbol)) {
            symbolValue = 1;
        } else if ("V".equals(symbol)) {
            symbolValue = 5;
        } else if ("X".equals(symbol)) {
            symbolValue = 10;
        } else if ("L".equals(symbol)) {
            symbolValue = 50;
        } else if ("C".equals(symbol)) {
            symbolValue = 100;
        } else if ("D".equals(symbol)) {
            symbolValue = 500;
        } else if ("M".equals(symbol)) {
            symbolValue = 1000;
        }


        return symbolValue;
    }

    protected boolean canSymbolBePlacedBefore(String rightSymbol, String leftSymbol) {
        if ("I".equals(leftSymbol)) {
            if ("V".equals(rightSymbol)) {
                return true;
            } else
            if ("X".equals(rightSymbol)) {
                return true;
            }
        }
        
        return false;
    }
}