/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    
    @Test
    public void value_of_I_is_1() {
        String symbol = "I";
        int expectedValue = 1;
        
        int value = getValueOf(symbol);
        
        assertEquals(expectedValue,value);
    }
    
    @Test
    public void value_of_V_is_5() {
        String symbol = "V";
        int expectedValue = 5;
        
        int value = getValueOf(symbol);
        
        assertEquals(expectedValue,value);
    }
    
    @Test
    public void value_of_X_is_10() {
        String symbol = "X";
        int expectedValue = 10;
        
        int value = getValueOf(symbol);
        
        assertEquals(expectedValue,value);
    }

    protected int getValueOf(String symbol) {
        if ("I".equals(symbol)) {
            return 1;
        } else 
        if ("V".equals(symbol)) {
            return 5;
        } else 
        if ("X".equals(symbol)) {
            return 10;
        } 
        
        
        return 1;
    }
}