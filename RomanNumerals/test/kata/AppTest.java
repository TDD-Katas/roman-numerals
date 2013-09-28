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

    protected int valueOf(String symbol) {
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