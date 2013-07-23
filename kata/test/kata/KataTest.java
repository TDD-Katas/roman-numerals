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
        runAllTests(tests);
    }

    /**
     * Convert
     * @param roman
     * @return 
     */
    private int romanToDecimal(String roman) {
        int result = 0;

        if (hasOnlyI(roman)) {
            result = roman.length();
        } else if (containsV(roman)){
            result = computeValueOfVbasedLiteral(roman);
        } else if (containsX(roman)) {
            result = computeValueOfXbasedLiteral(roman);
        }

        return result;
    }
    
    private int valueOfIBefore(String symbol, String romanNumeral) {
        int indexOfSymbol = romanNumeral.indexOf(symbol);
        String front = romanNumeral.substring(0, indexOfSymbol);
        int numerOfIs = front.length();
        
        return numerOfIs*VALUE_OF_I;
    }
    
    private int valueOfIAfter(String symbol, String romanNumeral) {
        int lastIndexOfSymbol = romanNumeral.lastIndexOf(symbol);
        String tail = romanNumeral.substring(lastIndexOfSymbol + 1);
        int numerOfIs = tail.length();
        
        return numerOfIs*VALUE_OF_I;
    }
    
    private boolean containsX(String roman) {
        return roman.contains("X");
    }
    
    private boolean containsV(String roman) {
        return roman.contains("V");
    }
    
    private boolean hasOnlyI(String roman) {
        return roman.matches("I+");
    }

    
    
    private int computeValueOfVbasedLiteral(String roman) {
        return VALUE_OF_V 
                - valueOfIBefore("V", roman)
                + valueOfIAfter("V", roman);
    }

    private int computeValueOfXbasedLiteral(String roman) {
        return VALUE_OF_X 
                - valueOfIBefore("X", roman) 
                + valueOfIAfter("X", roman);
    }
}