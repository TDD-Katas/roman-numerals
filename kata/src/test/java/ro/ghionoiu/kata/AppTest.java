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
public class AppTest {
    
    @Test
    public void value_of_I_is_1() {
        assertThat(valueOf("I"), equalTo(1));
    }
    
    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf("V"), equalTo(5));
    }

    protected int valueOf(String symbol) {
        int valueOfI = 0;
        if (symbol == "I") {
            valueOfI = 1 ;
        } else 
        if (symbol == "V") {
            valueOfI = 5 ;
        }
        return valueOfI;
    }

}