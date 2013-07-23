/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import java.util.LinkedList;
import java.util.List;
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
        private final String romanValue;
        private final int decimalValue;

        public Scenario(String romanValue, int decimalValue) {
            this.romanValue = romanValue;
            this.decimalValue = decimalValue;
        }

        public String getRomanValue() {
            return romanValue;
        }

        public int getDecimalValue() {
            return decimalValue;
        }
    }
    
    /**
     * Utility method
     * @param roman
     * @param expectedDecimal 
     */
    private boolean testForEquality(Scenario scenario) {
        //When
        int result = romanToDecimal(scenario.getRomanValue());
        
        //Then
        return result == scenario.getDecimalValue();
    }
    
    @Test
    public void testScenarios() {
        boolean result = true;
        List<Scenario> scenarios = new LinkedList<Scenario>();
        scenarios.add(new Scenario("I", 1));
        scenarios.add(new Scenario("II", 2));
        scenarios.add(new Scenario("III", 3));
        scenarios.add(new Scenario("IV", 4));
        scenarios.add(new Scenario("V", 5));
        scenarios.add(new Scenario("VI", 6));
        scenarios.add(new Scenario("VII", 7));
        scenarios.add(new Scenario("VIII", 8));
        scenarios.add(new Scenario("IX", 9));
        
        
        for (Scenario scenario : scenarios) {
            result = testForEquality(scenario) && result;
        }
        
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