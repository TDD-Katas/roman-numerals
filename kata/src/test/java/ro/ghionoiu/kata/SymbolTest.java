/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class SymbolTest {
    
    @Test
    public void value_of_I_is_1() {
        assertThat(new Symbol("I").getValue(), equalTo(1));
    }
    
    @Test
    public void value_of_V_is_5() {
        assertThat(new Symbol("V").getValue(), equalTo(5));
    }
    
    @Test
    public void value_of_X_is_10() {
        assertThat(new Symbol("X").getValue(), equalTo(10));
    }
    
    @Test
    public void value_of_L_is_50() {
        assertThat(new Symbol("L").getValue(), equalTo(50));
    }
    
    @Test
    public void value_of_C_is_100() {
        assertThat(new Symbol("C").getValue(), equalTo(100));
    }
    
    @Test
    public void value_of_D_is_500() {
        assertThat(new Symbol("D").getValue(), equalTo(500));
    }
    
    @Test
    public void value_of_D_is_1000() {
        assertThat(new Symbol("M").getValue(), equalTo(1000));
    }
}