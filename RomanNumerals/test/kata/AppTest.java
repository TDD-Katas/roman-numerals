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

    protected int valueOf(String symbol) {
        if ("I".equals(symbol)) {
            return 1;
        } else 
        if ("V".equals(symbol)) {
            return 5;
        } else 
        if ("X".equals(symbol)) {
            return 10;
        } else 
        if ("L".equals(symbol)) {
            return 50;
        } else 
        if ("C".equals(symbol)) {
            return 100;
        } else 
        if ("D".equals(symbol)) {
            return 500;
        }
        
        
        return 1;
    }
}