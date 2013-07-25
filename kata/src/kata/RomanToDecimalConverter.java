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
    private static final RomanSymbol[] SYMBOL_VALUE_ORDER = new RomanSymbol[] {
        new RomanSymbol("M", "C", 1000),
        new RomanSymbol("D", "C", 500),
        new RomanSymbol("C", "X", 100),
        new RomanSymbol("L", "X", 50),
        new RomanSymbol("X", "I", 10),
        new RomanSymbol("V", "I", 5),
        new RomanSymbol("I", 1)
    };

    /**
     * Convert
     * @param romanNumeral
     * @return 
     */
    public int computeValueOf(String romanNumeral) throws InvalidRomanNumeralException {
        if (romanNumeral.length() == 0) {
            return 0;
        }
        
        //Convert
        RomanSymbol dominantSymbol = identifyDominatingSymbol(romanNumeral);
        return computeValueDominatedBy(dominantSymbol, romanNumeral);
    }    
    
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

    private int computeValueDominatedBy(RomanSymbol dominantSymbol, String romanNumeral) 
            throws InvalidRomanNumeralException {
        int locationOfDominantSymbol = getLocationOfDominantSymbol(romanNumeral, dominantSymbol);
        String frontNumeral = romanNumeral.substring(0, locationOfDominantSymbol);
        String tailNumeral = romanNumeral.substring(locationOfDominantSymbol + 1);
        return dominantSymbol.getValue()
                - computeValueOf(frontNumeral)
                + computeValueOf(tailNumeral);
    }    

    private int getLocationOfDominantSymbol(String romanNumeral, RomanSymbol dominantSymbol) {
        int locationOfDominantSymbol = romanNumeral.indexOf(dominantSymbol.getSymbol());
        return locationOfDominantSymbol;
    }
}
