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
        ScenarioSet tests = new ScenarioSet();
        tests.add(0, "");
        tests.add(1, "I");
        tests.add(2, "II");
        tests.add(3, "III");
        tests.add(4, "IV");
        tests.add(5, "V");
        tests.add(6, "VI");
        tests.add(7, "VII");
        tests.add(8, "VIII");
        tests.add(9, "IX");
        tests.add(10, "X");
        tests.add(11, "XI");
        tests.add(12, "XII");
        tests.add(13, "XIII");
        tests.add(14, "XIV");
        tests.add(15, "XV");
        tests.add(16, "XVI");
        tests.add(17, "XVII");
        tests.add(18, "XVIII");
        tests.add(19, "XIX");
        tests.add(20, "XX");
        tests.add(21, "XXI");
        tests.add(24, "XXIV");
        tests.add(29, "XXIX");
        tests.add(30, "XXX");
        tests.add(34, "XXXIV");
        tests.add(39, "XXXIX");
        tests.add(40, "XL");
        tests.add(41, "XLI");
        tests.add(44, "XLIV");
        tests.add(59, "LIX");
        tests.add(70, "LXX");
        tests.add(89, "LXXXIX");
        tests.add(90, "XC");
        tests.add(100, "C");
        tests.add(140, "CXL");
        tests.add(400, "CD");
        tests.add(555, "DLV");
        tests.add(900, "CM");
        tests.add(999, "CMXCIX");
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