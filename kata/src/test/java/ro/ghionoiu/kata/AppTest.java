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
        String symbol = "I";
        
        int valueOfI = 0;
        if (symbol == "I") {
            valueOfI = 1 ;
        }
        
        assertThat(valueOfI, equalTo(1));
    }

}