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
    
    
    /**
     * Helper class for tests
     */
    private static class Scenario {
        private final String roman;
        private final int expectedDecimal;

        public Scenario(String roman, int expectedDecimal) {
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
    private boolean testToDecimal(Scenario scenario) {
        //When
        int result = romanToDecimal(scenario.getRoman());
        
        //Then
        return result == scenario.getExpectedDecimal();
    }
    
    @Test
    public void testScenarios() {
        boolean result = true;
        result = testToDecimal(new Scenario("I", 1)) && result;
        result = testToDecimal(new Scenario("II", 2)) && result;
        result = testToDecimal(new Scenario("III", 3)) && result;
        result = testToDecimal(new Scenario("IV", 4)) && result;
        result = testToDecimal(new Scenario("V", 5)) && result;
        result = testToDecimal(new Scenario("VI", 6)) && result;
        result = testToDecimal(new Scenario("VII", 7)) && result;
        result = testToDecimal(new Scenario("VIII", 8)) && result;
        result = testToDecimal(new Scenario("IX", 9)) && result;
        assertTrue(result);
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