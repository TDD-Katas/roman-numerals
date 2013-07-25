/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class RomanToDecimalConverter {
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

    private RomanSymbol identifyDominatingSymbol(String romanNumeral) 
            throws InvalidRomanNumeralException {
        RomanSymbol dominantSymbol = null;
        for (RomanSymbol romanSymbol : SYMBOL_VALUE_ORDER) {
            if (romanSymbol.dominates(romanNumeral)) {
                dominantSymbol = romanSymbol;
                break;
            }
        }
        
        //Test for exception
        if (dominantSymbol == null) {
            throw new InvalidRomanNumeralException();
        }
        
        return dominantSymbol;
    }

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

        public int getValue() {
            return value;
        }
        
        public boolean dominates(String roman) {
            return roman.startsWith(symbol) || 
                    roman.startsWith(substractionSymbol+symbol);
        }
    }
    
    /**
     * Convert
     * @param romanNumeral
     * @return 
     */
    public int convert(String romanNumeral) throws InvalidRomanNumeralException {
        int result;

        if (romanNumeral.length() == 0) {
            result = 0;
        } else {
            RomanSymbol dominantSymbol = identifyDominatingSymbol(romanNumeral);
            result = computeValueDominatedBy(dominantSymbol, romanNumeral);
        }
        
        return result;
    }
    
    private int valueBeforeDominantSymbol(String symbol, String romanNumeral) 
            throws InvalidRomanNumeralException {
        int indexOfSymbol = romanNumeral.indexOf(symbol);
        String front = romanNumeral.substring(0, indexOfSymbol);
        
        return convert(front);
    }
    
    private int valueAfterDominantSymbol(String symbol, String romanNumeral) 
            throws InvalidRomanNumeralException {
        int indexOfSymbol = romanNumeral.indexOf(symbol);
        String tail = romanNumeral.substring(indexOfSymbol + 1);
        
        return convert(tail);
    }
    
    private int computeValueDominatedBy(RomanSymbol dominantSymbol, String romanNumeral) 
            throws InvalidRomanNumeralException {
        return dominantSymbol.getValue()
                - valueBeforeDominantSymbol(dominantSymbol.getSymbol(), romanNumeral)
                + valueAfterDominantSymbol(dominantSymbol.getSymbol(), romanNumeral);
    }
}
