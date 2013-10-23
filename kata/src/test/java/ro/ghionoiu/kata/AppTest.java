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
    public void value_of_I_is_1() {
        assertThat(valueOf("I"), equalTo(1));
    }
    
    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf("V"), equalTo(5));
    }
    
    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf("X"), equalTo(10));
    }
    
    //~~~~~~~~~~ Production

    
    protected int valueOf(String symbol) {
        int value = 0;
        
        Map<String, Integer> symbolValues = new HashMap<String, Integer>();
        symbolValues.put("I", 1);
        symbolValues.put("V", 5);
        symbolValues.put("X", 10);
        
        if (symbolValues.containsKey(symbol)) {
            value = symbolValues.get(symbol) ;
        }
        return value;
    }

}