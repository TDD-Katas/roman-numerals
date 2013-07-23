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
    private static final int VALUE_OF_V = 5;
    private static final int VALUE_OF_I = 1;
    
    private class Scenarion {
        String roman;
        int expectedDecimal;

        public Scenarion(String roman, int expectedDecimal) {
            this.roman = roman;
            this.expectedDecimal = expectedDecimal;
        }

        public String getRoman() {
            return roman;
        }

        public int getExpectedDecimal() {
            return expectedDecimal;
        }
        
        
    }
    
    /**
     * Utility method
     * @param roman
     * @param expectedDecimal 
     */
    private void testToDecimal(Scenarion scenarion) {
        //When
        int result = romanToDecimal(scenarion.getRoman());
        
        //Then
        assertEquals(scenarion.getExpectedDecimal(),result);
    }
    
    @Test
    public void test_I() {
        testToDecimal(new Scenarion("I", 1));
    }
    
    @Test
    public void test_II() {
        testToDecimal(new Scenarion("II", 2));
    }
    
    @Test
    public void test_III() {
        testToDecimal(new Scenarion("III", 3));
    }
    
    @Test
    public void test_IV() {
        testToDecimal(new Scenarion("IV", 4));
    }
    
    @Test
    public void test_V() {
        testToDecimal(new Scenarion("V", 5));
    }
    
    @Test
    public void test_VI() {
        testToDecimal(new Scenarion("VI", 6));
    }
    
    @Test
    public void test_VII() {
        testToDecimal(new Scenarion("VII", 7));
    }
    
    @Test
    public void test_VIII() {
        testToDecimal(new Scenarion("VIII", 8));
    }
    
    @Test
    public void test_IX() {
        testToDecimal(new Scenarion("IX", 9));
    }
    
    /**
     * Convert
     * @param roman
     * @return 
     */
    private int romanToDecimal(String roman) {
        int result;

        if (hasOnlyI(roman)) {
            result = roman.length();
        } else if ("IV".equals(roman)){
            result = computeValueOfVbasedLiteral(roman);
        } else if ("V".equals(roman)){
            result = computeValueOfVbasedLiteral(roman);
        } else if ("VI".equals(roman)){
            result = computeValueOfVbasedLiteral(roman);
        } else if ("VII".equals(roman)){
            result = computeValueOfVbasedLiteral(roman);
        } else if ("VIII".equals(roman)){
            result = computeValueOfVbasedLiteral(roman);
        } else {
            result = 9;
        }

        return result;
    }
    
    private int numberOfIBeforeV(String roman) {
        int indexOfV = roman.indexOf("V");
        String front = roman.substring(0, indexOfV);
        int numerOfIs = front.length();
        
        return numerOfIs;
    }
    
    private int numberOfIAfterV(String roman) {
        int lastIndexOfV = roman.lastIndexOf("V");
        String tail = roman.substring(lastIndexOfV + 1);
        int numerOfIs = tail.length();
        
        return numerOfIs;
    }
    
    private boolean hasOnlyI(String roman) {
        return roman.matches("I+");
    }

    private int computeValueOfVbasedLiteral(String roman) {
        return VALUE_OF_V 
                - numberOfIBeforeV(roman)*VALUE_OF_I
                + numberOfIAfterV(roman)*VALUE_OF_I;
    }
}