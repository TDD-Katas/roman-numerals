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
        
        int value = getValueOf(symbol);
        
        assertEquals(1,value);
    }
    
    @Test
    public void value_of_V_is_5() {
        String symbol = "V";
        
        int value = getValueOf(symbol);
        
        assertEquals(5,value);
    }
    
    @Test
    public void value_of_X_is_10() {
        String symbol = "X";
        
        int value = getValueOf(symbol);
        
        assertEquals(10,value);
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