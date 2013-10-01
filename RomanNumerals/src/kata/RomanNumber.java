/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import kata.symbols.KnownRomanSymbols;
import kata.symbols.Symbol;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class RomanNumber {
    protected final Symbol[] symbols;

    public RomanNumber(String romanNumber) {
        this.symbols = asListOfSymbols(romanNumber);
    }
    
    protected RomanNumber(Symbol[] romanNumber) {
        this.symbols = romanNumber;
    }
    
    protected Symbol[] getSymbols() {
        return symbols;
    }
    
    public int computeIntegerValue() {
        ContextValueProvider contextValueProvider = new ContextValueProvider();
        return computeIntegerValue(contextValueProvider);
    }
    
    public int computeIntegerValue(ContextValueProvider contextValuesCalculator) {
        int currentValue = 0;
        
        for (int i = 0; i < symbols.length; i++) {
            Symbol current = getElementAt(i, symbols);
            Symbol after = getElementAfter(i, symbols);
            currentValue += contextValuesCalculator.computeContextValue(current, after);
        }
        
        return currentValue;
    }
    
    //~~~
    private static Symbol[] asListOfSymbols(String romanNumber) {
        Symbol[] symbols = new Symbol[romanNumber.length()];
        for (int i = 0; i < romanNumber.length(); i++) {
            String romanChar = String.valueOf(romanNumber.charAt(i));
            symbols[i] = KnownRomanSymbols.asRomanSymbol(romanChar);
        }
        return symbols;
    }
    
    private static Symbol getElementAt(int currentPosition, Symbol[] romanNumeral) {
        return romanNumeral[currentPosition];
    }

    private static Symbol getElementAfter(int currentPosition, Symbol[] romanNumeral) {
        Symbol symbolAfterPosition = null;
        int nextPosition = currentPosition+1;
        if (nextPosition < romanNumeral.length) {
            symbolAfterPosition = getElementAt(nextPosition, romanNumeral);
        }
        
        return symbolAfterPosition;
    }
}
