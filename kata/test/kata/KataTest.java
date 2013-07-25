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
    private static final RomanSymbol ROMAN_M = new RomanSymbol("M", "C", 1000);
    private static final RomanSymbol ROMAN_D = new RomanSymbol("D", "C", 500);
    private static final RomanSymbol ROMAN_C = new RomanSymbol("C", "X", 100);
    private static final RomanSymbol ROMAN_L = new RomanSymbol("L", "X", 50);
    private static final RomanSymbol ROMAN_X = new RomanSymbol("X", "I", 10);
    private static final RomanSymbol ROMAN_V = new RomanSymbol("V", "I", 5);
    private static final RomanSymbol ROMAN_I = new RomanSymbol("I", 1); 
    private static final RomanSymbol[] SYMBOL_VALUE_ORDER = new RomanSymbol[] {
        ROMAN_M,
        ROMAN_D,
        ROMAN_C,
        ROMAN_L,
        ROMAN_X,
        ROMAN_V,
        ROMAN_I
    };

    private static class RomanSymbol {
        private final String symbol;
        private final String substractionSymbol;
        private final int value;

        public RomanSymbol(String symbol, int value) {
            this(symbol, "", value);
        }
        
        public RomanSymbol(String symbol, String substractionSymbol, int value) {
            this.symbol = symbol;
            this.substractionSymbol = substractionSymbol;
            this.value = value;
        }

        public String getSymbol() {
            return symbol;
        }

        public String getSubstractionSymbol() {
            return substractionSymbol;
        }
        
        public int getValue() {
            return value;
        }
        
        public boolean dominates(String roman) {
            return roman.startsWith(symbol) || 
                    roman.startsWith(substractionSymbol+symbol);
        }
    }
    
    private void runSingleTest(Scenario scenario) {
        assertTrue(testForEquality(scenario));
    }
    
    
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
    public void testValidRomanNumbers() {
        List<Scenario> tests = new LinkedList<Scenario>();
        tests.add(new Scenario(0, ""));
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
        tests.add(new Scenario(100, "C"));
        tests.add(new Scenario(140, "CXL"));
        tests.add(new Scenario(400, "CD"));
        tests.add(new Scenario(555, "DLV"));
        tests.add(new Scenario(900, "CM"));
        tests.add(new Scenario(999, "CMXCIX"));
        runAllTests(tests);
    }
    
    @Test
    public void testInvalidRomanNumbers() {
        
    }
    

    /**
     * Convert
     * @param roman
     * @return 
     */
    private int romanToDecimal(String roman) {
        int result;

        if (roman.length() == 0) {
            result = 0;
        } else {
            RomanSymbol dominantSymbol = ROMAN_I;
            for (RomanSymbol romanSymbol : SYMBOL_VALUE_ORDER) {
                if (romanSymbol.dominates(roman)) {
                    dominantSymbol = romanSymbol;
                    break;
                }
            }
            
            result = computeValueDominatedBy(dominantSymbol, roman);
        }
        
        return result;
    }
    
    private int computeValueDominatedBy(RomanSymbol dominantSymbol, String roman) {
        return dominantSymbol.getValue()
                - valueBeforeDominantSymbol(dominantSymbol.getSymbol(), roman)
                + valueAfterDominantSymbol(dominantSymbol.getSymbol(), roman);
    }
    
    private int valueBeforeDominantSymbol(String symbol, String romanNumeral) {
        int indexOfSymbol = romanNumeral.indexOf(symbol);
        String front = romanNumeral.substring(0, indexOfSymbol);
        
        return romanToDecimal(front);
    }
    
    private int valueAfterDominantSymbol(String symbol, String romanNumeral) {
        int indexOfSymbol = romanNumeral.indexOf(symbol);
        String tail = romanNumeral.substring(indexOfSymbol + 1);
        
        return romanToDecimal(tail);
    }
}