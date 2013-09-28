/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import static org.hamcrest.CoreMatchers.*;
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
        assertThat(valueOf("I"), is(1));
    }

    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf("V"), is(5));
    }

    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf("X"), is(10));
    }

    @Test
    public void value_of_L_is_50() {
        assertThat(valueOf("L"), is(50));
    }

    @Test
    public void value_of_C_is_100() {
        assertThat(valueOf("C"), is(100));
    }

    @Test
    public void value_of_D_is_500() {
        assertThat(valueOf("D"), is(500));
    }

    @Test
    public void value_of_M_is_1000() {
        assertThat(valueOf("M"), is(1000));
    }

    @Test
    public void value_is_computed_by_adding_symbols_values() {
        int valueOfSymbol1 = 1;
        int valueOfSymbol2 = 2;

        int computedValue = valueOfSymbol1 + valueOfSymbol2;

        assertThat(computedValue, is(valueOfSymbol1 + valueOfSymbol2));
    }

    @Test
    public void V_can_substract_I() {
        assertTrue(romanSymbol("V").canSubstract(romanSymbol("I")));
    }

    @Test
    public void X_can_substract_I() {
        assertTrue(romanSymbol("X").canSubstract(romanSymbol("I")));
    }

    @Test
    public void L_can_substract_X() {
        assertTrue(romanSymbol("L").canSubstract(romanSymbol("X")));
    }

    @Test
    public void C_can_substract_X() {
        assertTrue(romanSymbol("C").canSubstract(romanSymbol("X")));
    }

    @Test
    public void D_can_substract_C() {
        assertTrue(romanSymbol("D").canSubstract(romanSymbol("C")));
    }

    @Test
    public void M_can_substract_C() {
        assertTrue(romanSymbol("M").canSubstract(romanSymbol("C")));
    }

    @Test
    public void symbol_can_be_placed_before_if_it_is_heigher() {
        Symbol leftSymbol = spy(concreteRomanSymbol());
        Symbol rightSymbol = mock(Symbol.class);
        when(leftSymbol.getValue()).thenReturn(2);
        when(rightSymbol.getValue()).thenReturn(1);
        
        boolean canBePlaced = leftSymbol.canBePlacedBefore(rightSymbol);

        assertThat(canBePlaced, is(true));
    }
    
    @Test
    public void symbol_can_be_placed_before_if_it_is_equal() {
        Symbol leftSymbol = spy(concreteRomanSymbol());
        Symbol rightSymbol = mock(Symbol.class);
        when(leftSymbol.getValue()).thenReturn(1);
        when(rightSymbol.getValue()).thenReturn(1);
        
        boolean canBePlaced = leftSymbol.canBePlacedBefore(rightSymbol);

        assertThat(canBePlaced, is(true));
    }

    @Test
    public void symbol_can_be_placed_before_if_is_substractable_symbol() {
        Symbol leftSymbol = spy(concreteRomanSymbol());
        Symbol rightSymbol = mock(Symbol.class);
        when(rightSymbol.canSubstract(leftSymbol)).thenReturn(true);

        boolean canBePlaced = leftSymbol.canBePlacedBefore(rightSymbol);

        assertThat(canBePlaced, is(true));
    }


    protected Symbol concreteRomanSymbol() {
        return RomanSymbolFactory.fromString("I");
    }
    
    //~~~~~~~
    
    interface Symbol {
        String getLiteral();
        int getValue();
        boolean canSubstract(Symbol symbol);
        boolean canBePlacedBefore(Symbol symbol);
    }
    
    static class RomanSymbolFactory {
        private static final Symbol I = new RomanSymbol("I", 1, null);
        private static final Symbol V = new RomanSymbol("V", 5, I);
        private static final Symbol X = new RomanSymbol("X", 10, I);
        private static final Symbol L = new RomanSymbol("L", 50, X);
        private static final Symbol C = new RomanSymbol("C", 100, X);
        private static final Symbol D = new RomanSymbol("D", 500, C);
        private static final Symbol M = new RomanSymbol("M", 1000, C);
        private static final Symbol[] ROMAN_SYMBOLS = {
            I,V,X,L,C,D,M
        };

        private RomanSymbolFactory() {
        }
        
        public static Symbol fromString(String symbol) {
            Symbol chosenSymbol = null;
            for (Symbol romanLiteral : ROMAN_SYMBOLS) {
                if (romanLiteral.getLiteral().equals(symbol)) {
                    chosenSymbol = romanLiteral;
                }
            }
            return chosenSymbol;
        }
        
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
            return substractionSymbol.equals(literalToSubstract);
        }
        
        public boolean canBePlacedBefore(Symbol rightSymbol) {
            if (rightSymbol.canSubstract(this)) {
                return true;
            } else if (this.getValue() >= rightSymbol.getValue()) {
                return true;
            }

            return false;
        }
    }

    protected Symbol romanSymbol(String symbol) {
        return RomanSymbolFactory.fromString(symbol);
    }

    protected int valueOf(String symbol) {
        return romanSymbol(symbol).getValue();
    }
}