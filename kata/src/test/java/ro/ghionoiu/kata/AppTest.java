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

    protected int valueOf(String symbol) {
        int value = 0;
        
        Map<String, Integer> valueMap = new HashMap<String, Integer>();
        valueMap.put("I", 1);
        valueMap.put("V", 5);
        
        if ("I".equals(symbol)) {
            value = valueMap.get("I") ;
        } else 
        if ("V".equals(symbol)) {
            value = valueMap.get("V") ;
        }
        return value;
    }

}