/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {

    @Test
    public void value_of_I_is_1() {
        assertThat(valueOf(asRomanSymbol("I")), is(1));
    }

    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf(asRomanSymbol("V")), is(5));
    }

    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf(asRomanSymbol("X")), is(10));
    }

    @Test
    public void value_of_L_is_50() {
        assertThat(valueOf(asRomanSymbol("L")), is(50));
    }

    @Test
    public void value_of_C_is_100() {
        assertThat(valueOf(asRomanSymbol("C")), is(100));
    }

    @Test
    public void value_of_D_is_500() {
        assertThat(valueOf(asRomanSymbol("D")), is(500));
    }

    @Test
    public void value_of_M_is_1000() {
        assertThat(valueOf(asRomanSymbol("M")), is(1000));
    }

    @Test
    public void V_can_substract_I() {
        assertTrue(V.canSubstract(I));
    }

    @Test
    public void X_can_substract_I() {
        assertTrue(X.canSubstract(I));
    }

    @Test
    public void L_can_substract_X() {
        assertTrue(L.canSubstract(X));
    }

    @Test
    public void C_can_substract_X() {
        assertTrue(C.canSubstract(X));
    }

    @Test
    public void D_can_substract_C() {
        assertTrue(D.canSubstract(C));
    }

    @Test
    public void M_can_substract_C() {
        assertTrue(M.canSubstract(C));
    }
    
    @Test
    public void I_cannot_substract_V() {
        assertFalse(I.canSubstract(V));
    }

    @Test
    public void symbol_can_be_placed_before_if_it_is_heigher() {
        Symbol romanSymbolWithHighValue = romanSymbolWithValue(2);
        Symbol romanSymbolWithLowValue = romanSymbolWithValue(1);
        
        assertTrue(romanSymbolWithHighValue.canBePlacedBefore(romanSymbolWithLowValue));
    }
    
    @Test
    public void symbol_can_be_placed_before_if_it_is_equal() {
        Symbol romanSymbol = romanSymbolWithValue(1);
        Symbol romanSymbolWithSameValue = romanSymbolWithValue(1);
        
        assertTrue(romanSymbol.canBePlacedBefore(romanSymbolWithSameValue));
    }

    @Test
    public void symbol_can_be_placed_before_if_is_substractable_symbol() {
        Symbol substractionSymbol = concreteRomanSymbol();
        Symbol romanSymbol = romanSymbolThatSubstractsAll();

       assertTrue(substractionSymbol.canBePlacedBefore(romanSymbol));
    }

    @Test
    public void the_context_value_of_a_non_substracted_symbol_is_its_value() {
        Symbol symbol = romanSymbolWithValue(1);
        Symbol symbolAfter = romanSymbolThatDoesntSubstracts();
        
        int contextValue = computeContextValue(symbol, symbolAfter);
        
       assertThat(contextValue, is(symbol.getValue()));
    }
    
    @Test
    public void the_context_value_of_a_substracted_symbol_is_its_negative_value() {
        Symbol symbol = romanSymbolWithValue(1);
        Symbol symbolAfter = romanSymbolThatSubstractsAll();
        
        int contextValue = computeContextValue(symbol, symbolAfter);
        
       assertThat(contextValue, is(-symbol.getValue()));
    }
    
    @Test
    public void a_symbol_is_substracted_if_placed_before_dominant_symbol() {
        Symbol symbol = concreteRomanSymbol();
        Symbol symbolBefore = romanSymbolThatSubstractsAll();

        boolean isSubstracted = symbolBefore.canSubstract(symbol);
        
       assertTrue(isSubstracted);
    }
    
    @Test
    public void a_roman_numeral_is_list_of_symbols() {
        String symbol1 = "I";
        String symbol2 = "X";
        String romanNumber = symbol1 + symbol2;
        Symbol[] expectedSymbols = new Symbol[] {
            asRomanSymbol(symbol1), 
            asRomanSymbol(symbol2)
        };
        
        Symbol[] symbols = asListOfSymbols(romanNumber);
        
        assertArrayEquals(expectedSymbols, symbols);
    }
    
    
    @Test
    public void value_of_roman_numeral_is_sum_of_context_values_of_symbols() {
        Symbol[] numeral = new Symbol[] {mock(Symbol.class), mock(Symbol.class)};
        int[] contextValues = {1, 2}; 
        ContextValueProvider converter = mock(ContextValueProvider.class);
        int position;
        position = 0;
        when(converter.computeContextValue(eq(numeral[position]), any(Symbol.class))).thenReturn(contextValues[position]);
        position = 1;
        when(converter.computeContextValue(eq(numeral[position]), any(Symbol.class))).thenReturn(contextValues[position]);
        
        int value = computeRomanNumeralValue(numeral, converter);

        assertThat(value, is(contextValues[0]+contextValues[1]));
    }
    
    
    //~~ stubs

    private Symbol concreteRomanSymbol() {
        return new RomanSymbol("", 1, null);
    }

    private void prepareSymbolValue(Symbol leftSymbol, int value) {
        when(leftSymbol.getValue()).thenReturn(value);
    }

    private Symbol romanSymbolWithValue(int value) {
        Symbol symbol = spy(concreteRomanSymbol());
        prepareSymbolValue(symbol, value);
        return symbol;
    }
    
    private Symbol romanSymbolThatSubstractsAll() {
        Symbol symbol = spy(concreteRomanSymbol());
        when(symbol.canSubstract(any(Symbol.class))).thenReturn(true);
        return symbol;
    }
    
    private Symbol romanSymbolThatDoesntSubstracts() {
        Symbol symbol = spy(concreteRomanSymbol());
        when(symbol.canSubstract(any(Symbol.class))).thenReturn(false);
        return symbol;
    }

    //~~~~~~~
    
    private static final Symbol I = new RomanSymbol("I", 1, null);
    private static final Symbol V = new RomanSymbol("V", 5, I);
    private static final Symbol X = new RomanSymbol("X", 10, I);
    private static final Symbol L = new RomanSymbol("L", 50, X);
    private static final Symbol C = new RomanSymbol("C", 100, X);
    private static final Symbol D = new RomanSymbol("D", 500, C);
    private static final Symbol M = new RomanSymbol("M", 1000, C);   
    private static final Symbol[] ROMAN_NUMERALS = {
        I,V,X,L,C,D,M
    };
    
    private Symbol asRomanSymbol(String character) {
        Symbol convertedSymbol = I;
        for (Symbol symbol : ROMAN_NUMERALS) {
            if (symbol.getLiteral().equals(character)) {
                convertedSymbol = symbol;
            }
        }
        return convertedSymbol;
    }

    private int computeRomanNumeralValue(Symbol[] romanNumeral, ContextValueProvider converter) {
        int currentValue = 0;
        
        for (int i = 0; i < romanNumeral.length; i++) {
            Symbol current = getElementAt(i, romanNumeral);
            Symbol after = getElementAfter(i, romanNumeral);
            currentValue += converter.computeContextValue(current, after);
        }
        
        return currentValue;
    }
    
    protected Symbol getElementAt(int currentPosition, Symbol[] romanNumeral) {
        return romanNumeral[currentPosition];
    }

    protected Symbol getElementAfter(int currentPosition, Symbol[] romanNumeral) {
        int nextPosition = currentPosition+1;
        if (nextPosition < romanNumeral.length) {
            return getElementAt(nextPosition, romanNumeral);
        } else {
            return null;
        }
    }
    
    class ContextValueProvider {
        public int computeContextValue(Symbol symbol, Symbol symbolAfter) {
            int contextValue = symbol.getValue();
            boolean symbolIsSubstracted = symbolAfter.canSubstract(symbol);
            if (symbolIsSubstracted) {
                contextValue = -contextValue;
            }

            return contextValue;
        }
    }

    protected int computeContextValue(Symbol symbol, Symbol symbolAfter) {
        ContextValueProvider converter = new ContextValueProvider();
        return converter.computeContextValue(symbol, symbolAfter);
    }

    protected Symbol[] asListOfSymbols(String romanNumber) {
        Symbol[] symbols = new Symbol[romanNumber.length()];
        for (int i = 0; i < romanNumber.length(); i++) {
            String romanChar = String.valueOf(romanNumber.charAt(i));
            symbols[i] = asRomanSymbol(romanChar);
        }
        return symbols;
    }
    
    interface Symbol {
        String getLiteral();
        int getValue();
        boolean canSubstract(Symbol symbol);
        boolean canBePlacedBefore(Symbol symbol);
    }
    
    static class RomanSymbol implements Symbol {
        private final String literal;
        private final int value;
        private final Symbol substractionSymbol;

        public RomanSymbol(String literal, int value, Symbol substractionSymbol) {
            this.literal = literal;
            this.value = value;
            this.substractionSymbol = substractionSymbol;
        }

        public String getLiteral() {
            return literal;
        }
        
        public int getValue() {
            return value;
        }

        public boolean canSubstract(Symbol literalToSubstract) {
            boolean canSubstract = false;
            if (substractionSymbol != null) {
                canSubstract = substractionSymbol.equals(literalToSubstract);
            }
            
            return canSubstract;
        }
        
        public boolean canBePlacedBefore(Symbol rightSymbol) {
            boolean canBePlaced = false;
            if (rightSymbol.canSubstract(this)) {
                canBePlaced = true;
            } else if (this.getValue() >= rightSymbol.getValue()) {
                canBePlaced = true;
            }

            return canBePlaced;
        }
    }

    private int valueOf(Symbol symbol) {
        return symbol.getValue();
    }
}