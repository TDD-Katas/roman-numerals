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
public class KataTest {
    
    /**
     * Convert
     * @param roman
     * @return 
     */
    private int romanToDecimal(String roman) {
        int result;

        if ("I".equals(roman)) {
            result = 1;
        } else if ("II".equals(roman)) {
            result = 2;
        } else {
            result = 3;
        }


        return result;
    }
    
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_I() {
        testToDecimal("I", 1);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_II() {
        testToDecimal("II", 2);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_III() {
        testToDecimal("III", 3);
    }
    
    private void testToDecimal(String roman, int expectedDecimal) {
        //When
        int result = romanToDecimal(roman);
        
        //Then
        assertEquals(expectedDecimal,result);
    }
}