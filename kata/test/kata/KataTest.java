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
    private static final int VALUE_OF_I = 1;
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

        if (isDominatedBy("C", getUnderminedSymbolBy("C"), roman)) {
            result = computeValueDominatedBy("C", roman);
        } else
        if (isDominatedBy("L", getUnderminedSymbolBy("L"), roman)) {
            result = computeValueDominatedBy("L", roman);
        } else
        if (isDominatedBy("X", getUnderminedSymbolBy("X"), roman)) {
            result = computeValueDominatedBy("X", roman);
        } else 
        if (isDominatedBy("V", getUnderminedSymbolBy("V"), roman)){
            result = computeValueDominatedBy("V", roman);
        } else {
            result = computeValueOfIbasedLiteral(roman);
        }
        
        return result;
    }
    
    private String getUnderminedSymbolBy(String dominantSymbol) {
        if ("C".equals(dominantSymbol)) {
            return "X";
        } else
        if ("L".equals(dominantSymbol)) {
            return "X";
        } else
        if ("X".equals(dominantSymbol)) {
            return "I";
        } else
        if ("V".equals(dominantSymbol)) {
            return "I";
        }
        
        return "I";
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
        int value;
        
        if ("C".equals(symbol)) {
            value = VALUE_OF_C;
        } else
        if ("L".equals(symbol)) {
            value = VALUE_OF_L;
        } else
        if ("X".equals(symbol)) {
            value = VALUE_OF_X;
        } else
        if ("V".equals(symbol)) {
            value = VALUE_OF_V;
        } else {
            value = VALUE_OF_I;
        }
        
        return value;
    }
    
    private int computeValueDominatedBy(String dominantSymbol, String roman) {
        return getValueForSymbol(dominantSymbol)
                - valueBeforeDominantSymbol(dominantSymbol, roman)
                + valueOfAfterDominantSymbol(dominantSymbol, roman);
    }
    
    private int computeValueOfIbasedLiteral(String roman) {
        return roman.length();
    }
}