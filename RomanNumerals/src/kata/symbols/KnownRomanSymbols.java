/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata.symbols;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public final class KnownRomanSymbols {
    public static final Symbol I = new RomanSymbol("I", 1, null);
    public static final Symbol V = new RomanSymbol("V", 5, I);
    public static final Symbol X = new RomanSymbol("X", 10, I);
    public static final Symbol L = new RomanSymbol("L", 50, X);
    public static final Symbol C = new RomanSymbol("C", 100, X);
    public static final Symbol D = new RomanSymbol("D", 500, C);
    public static final Symbol M = new RomanSymbol("M", 1000, C);   
    private static final Symbol[] ROMAN_NUMERALS = {
        I,V,X,L,C,D,M
    };
    
    public static Symbol asRomanSymbol(String character) {
        Symbol convertedSymbol = KnownRomanSymbols.I;
        for (Symbol symbol : KnownRomanSymbols.ROMAN_NUMERALS) {
            if (symbol.getLiteral().equals(character)) {
                convertedSymbol = symbol;
            }
        }
        return convertedSymbol;
    }
    
    private KnownRomanSymbols() {
        
    }
}
