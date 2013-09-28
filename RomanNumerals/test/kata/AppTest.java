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
    public void I_is_1() {
        String symbol = "I";
        
        int value = 1;
        
        assertEquals(1,value);
    }
    
    @Test
    public void V_is_5() {
        String symbol = "V";
        
        int value = 5;
        
        assertEquals(5,value);
    }
}