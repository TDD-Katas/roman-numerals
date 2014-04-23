/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import kata.symbols.Symbol;
import kata.symbols.RomanSymbol;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public final class MocksAndStubsUtils {

    private MocksAndStubsUtils() {
    }
    

    public static Symbol concreteRomanSymbol() {
        return new RomanSymbol("", 1, null);
    }

    public static void prepareSymbolValue(Symbol leftSymbol, int value) {
        when(leftSymbol.getValue()).thenReturn(value);
    }

    public static Symbol romanSymbolWithValue(int value) {
        Symbol symbol = spy(concreteRomanSymbol());
        prepareSymbolValue(symbol, value);
        return symbol;
    }
    
    public static Symbol romanSymbolThatSubstractsAll() {
        Symbol symbol = spy(concreteRomanSymbol());
        when(symbol.canSubstract(any(Symbol.class))).thenReturn(true);
        return symbol;
    }
    
    public static Symbol romanSymbolThatSubstractsNothing() {
        Symbol symbol = spy(concreteRomanSymbol());
        when(symbol.canSubstract(any(Symbol.class))).thenReturn(false);
        return symbol;
    }

    public static ContextValueProvider mockContextValueProvider(Symbol[] numeral, int[] contextValues) {
        ContextValueProvider converter = mock(ContextValueProvider.class);
        Symbol expectedSymbol;
        int returnValue;
        for (int i = 0; i < numeral.length; i++) {
            expectedSymbol = numeral[i];
            returnValue = contextValues[i];
            when(converter.computeContextValue(eq(expectedSymbol), any(Symbol.class)))
                    .thenReturn(returnValue);
        }
        return converter;
    }
    
    public static Symbol[] mockRomanNumeral(int sizeOfNumeral) {
        Symbol[] numeral = new Symbol[sizeOfNumeral];
        for (int i = 0; i < numeral.length; i++) {
            numeral[i] = mock(Symbol.class);
        }
        return numeral;
    }
}
