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
     * Utility method
     * @param roman
     * @param expectedDecimal 
     */
    private void testToDecimal(String roman, int expectedDecimal) {
        //When
        int result = romanToDecimal(roman);
        
        //Then
        assertEquals(expectedDecimal,result);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_I() {
        testToDecimal("I", getValueForI());
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
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_IV() {
        testToDecimal("IV", 4);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_V() {
        testToDecimal("V", getValueForV());
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_VI() {
        testToDecimal("VI", 6);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_VII() {
        testToDecimal("VII", 7);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_VIII() {
        testToDecimal("VIII", 8);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void test_IX() {
        testToDecimal("IX", 9);
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
            result = getValueForV() - numberOfIBeforeV(roman)*getValueForI();
        } else if ("V".equals(roman)){
            result = getValueForV() - numberOfIBeforeV(roman)*getValueForI();
        } else if ("VI".equals(roman)){
            result = getValueForV() + numberOfIAfterV(roman)*getValueForI();
        } else if ("VII".equals(roman)){
            result = getValueForV() + numberOfIAfterV(roman)*getValueForI();
        } else if ("VIII".equals(roman)){
            result = getValueForV() + numberOfIAfterV(roman)*getValueForI();
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

    private int getValueForV() {
        return 5;
    }

    private int getValueForI() {
        return 1;
    }
}