/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class StringToRoman {

    public static Symbol asRomanSymbol(String character) {
        Symbol convertedSymbol = KnownRomanSymbols.I;
        for (Symbol symbol : KnownRomanSymbols.ROMAN_NUMERALS) {
            if (symbol.getLiteral().equals(character)) {
                convertedSymbol = symbol;
            }
        }
        return convertedSymbol;
    }

    public static Symbol[] asListOfSymbols(String romanNumber) {
        Symbol[] symbols = new Symbol[romanNumber.length()];
        for (int i = 0; i < romanNumber.length(); i++) {
            String romanChar = String.valueOf(romanNumber.charAt(i));
            symbols[i] = asRomanSymbol(romanChar);
        }
        return symbols;
    }
    
}
