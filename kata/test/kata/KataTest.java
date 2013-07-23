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
    private static final int VALUE_OF_X = 10;
    private static final int VALUE_OF_L = 50;
    private static final int VALUE_OF_C = 100;

    private void runAllTests(List<Scenario> tests) {
        boolean result = true;
        for (Scenario scenario : tests) {
            result = testForEquality(scenario) && result;
        }
        assertTrue(result);
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
        boolean areEqual = result == scenario.getDecimalValue();
        if (!areEqual) {
            System.out.println("["+scenario.getRomanValue()+"] failed - "
                    + "expected "+scenario.getDecimalValue()
                    +" but got "+result);
        }
        
        return areEqual;
    }
    
    @Test
    public void testScenarios() {
        List<Scenario> tests = new LinkedList<Scenario>();
        tests.add(new Scenario(1, "I"));
        tests.add(new Scenario(2, "II"));
        tests.add(new Scenario(3, "III"));
        tests.add(new Scenario(4, "IV"));
        tests.add(new Scenario(5, "V"));
        tests.add(new Scenario(6, "VI"));
        tests.add(new Scenario(7, "VII"));
        tests.add(new Scenario(8, "VIII"));
        tests.add(new Scenario(9, "IX"));
        tests.add(new Scenario(10, "X"));
        tests.add(new Scenario(11, "XI"));
        tests.add(new Scenario(12, "XII"));
        tests.add(new Scenario(13, "XIII"));
        tests.add(new Scenario(14, "XIV"));
        tests.add(new Scenario(15, "XV"));
        tests.add(new Scenario(16, "XVI"));
        tests.add(new Scenario(17, "XVII"));
        tests.add(new Scenario(18, "XVIII"));
        tests.add(new Scenario(19, "XIX"));
        tests.add(new Scenario(20, "XX"));
        tests.add(new Scenario(21, "XXI"));
        tests.add(new Scenario(24, "XXIV"));
        tests.add(new Scenario(29, "XXIX"));
        tests.add(new Scenario(30, "XXX"));
        tests.add(new Scenario(34, "XXXIV"));
        tests.add(new Scenario(39, "XXXIX"));
        tests.add(new Scenario(40, "XL"));
        tests.add(new Scenario(41, "XLI"));
        tests.add(new Scenario(44, "XLIV"));
        tests.add(new Scenario(59, "LIX"));
        tests.add(new Scenario(70, "LXX"));
        tests.add(new Scenario(89, "LXXXIX"));
        tests.add(new Scenario(90, "XC"));
        runAllTests(tests);
    }

    /**
     * Convert
     * @param roman
     * @return 
     */
    private int romanToDecimal(String roman) {
        int result;

        if (isDominatedBy("C", "X", roman)) {
            result = computeValueOfCbasedLiteral(roman);
        } else
        if (isDominatedBy("L", "X", roman)) {
            result = computeValueOfLbasedLiteral(roman);
        } else
        if (isDominatedBy("X", "I", roman)) {
            result = computeValueOfXbasedLiteral(roman);
        } else 
        if (isDominatedBy("V", "I", roman)){
            result = computeValueOfVbasedLiteral(roman);
        } else {
            result = computeValueOfIbasedLiteral(roman);
        }
        
        return result;
    }
    
    private int valueBeforeDominantSymbol(String symbol, String romanNumeral) {
        int indexOfSymbol = romanNumeral.indexOf(symbol);
        String front = romanNumeral.substring(0, indexOfSymbol);
        
        return romanToDecimal(front);
    }
    
    private int valueOfAfterDominantSymbol(String symbol, String romanNumeral) {
        int indexOfSymbol = romanNumeral.indexOf(symbol);
        String tail = romanNumeral.substring(indexOfSymbol + 1);
        
        return romanToDecimal(tail);
    }
    
    
    private boolean isDominatedBy(String symbol, String subdominance, String roman) {
        return roman.startsWith(symbol) || roman.startsWith(subdominance+symbol);
    }
    

    private int getValueForSymbol(String symbol) {
        if ("X".equals(symbol)) {
            return VALUE_OF_X;
        } else
        if ("L".equals(symbol)) {
            return VALUE_OF_L;
        }
        
        return VALUE_OF_V;
    }
    
    private int computeValueOfVbasedLiteral(String roman) {
        return getValueForSymbol(roman)
                - valueBeforeDominantSymbol("V", roman)
                + valueOfAfterDominantSymbol("V", roman);
    }

    private int computeValueOfXbasedLiteral(String roman) {
        return getValueForSymbol("X") 
                - valueBeforeDominantSymbol("X", roman) 
                + valueOfAfterDominantSymbol("X", roman);
    }
    
    private int computeValueOfLbasedLiteral(String roman) {
        return getValueForSymbol("L") 
                - valueBeforeDominantSymbol("L", roman) 
                + valueOfAfterDominantSymbol("L", roman);
    }
    
    private int computeValueOfCbasedLiteral(String roman) {
        return VALUE_OF_C 
                - valueBeforeDominantSymbol("C", roman) 
                + valueOfAfterDominantSymbol("C", roman);
    }

    private int computeValueOfIbasedLiteral(String roman) {
        return roman.length();
    }
}