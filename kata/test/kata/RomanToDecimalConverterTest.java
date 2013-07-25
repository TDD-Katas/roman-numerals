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
public class RomanToDecimalConverterTest {
    /**
     * Method under test
     * @param romanNumeral
     * @return 
     */
    private int convert(String romanNumeral) throws InvalidRomanNumeralException {
        return new RomanToDecimalConverter().convert(romanNumeral);
    }
    
    @Test
    public void testValidRomanNumerals() throws InvalidRomanNumeralException {
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

    @Test(expected = InvalidRomanNumeralException.class)
    public void testInvalidCharacterThrowsExeception() 
            throws InvalidRomanNumeralException {
        convert("A");
    }
    
    @Test(expected = InvalidRomanNumeralException.class)
    public void testInvalidCharacterInNumeralThrowsExeception() 
            throws InvalidRomanNumeralException {
        convert("XXXAIII");
    }
    
    //~~~~~~~~~~~~~~~~~~~ Test utils ~~~~
    
    /**
     * Running multiple scenarios
     * @param tests
     * @throws InvalidRomanNumeralException 
     */
    private void runAllTests(List<Scenario> tests) throws InvalidRomanNumeralException {
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
    private boolean testForEquality(Scenario scenario) throws InvalidRomanNumeralException {
        //When
        int result = convert(scenario.getRomanValue());
        
        //Then
        boolean areEqual = result == scenario.getDecimalValue();
        if (!areEqual) {
            System.out.println("["+scenario.getRomanValue()+"] failed - "
                    + "expected "+scenario.getDecimalValue()
                    +" but got "+result);
        }
        
        return areEqual;
    }
}