/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import static org.hamcrest.CoreMatchers.is;
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
        assertThat(valueOf(I), is(1));
    }

    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf(V), is(5));
    }

    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf(X), is(10));
    }

    @Test
    public void value_of_L_is_50() {
        assertThat(valueOf(L), is(50));
    }

    @Test
    public void value_of_C_is_100() {
        assertThat(valueOf(C), is(100));
    }

    @Test
    public void value_of_D_is_500() {
        assertThat(valueOf(D), is(500));
    }

    @Test
    public void value_of_M_is_1000() {
        assertThat(valueOf(M), is(1000));
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
    public void the_value_of_a_substracted_symbol_is_used_negated() {
        boolean symbolIsSubstracted = true;
        int absoluteValue = 1;
        
        int valueToBeUsed = -absoluteValue;
        
       assertThat(valueToBeUsed, is(-absoluteValue));
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

    //~~~~~~~
    
    private static final Symbol I = new RomanSymbol("I", 1, null);
    private static final Symbol V = new RomanSymbol("V", 5, I);
    private static final Symbol X = new RomanSymbol("X", 10, I);
    private static final Symbol L = new RomanSymbol("L", 50, X);
    private static final Symbol C = new RomanSymbol("C", 100, X);
    private static final Symbol D = new RomanSymbol("D", 500, C);
    private static final Symbol M = new RomanSymbol("M", 1000, C);   
    
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