/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void symbol_can_be_placed_before_lower_or_equal_symbol() {
        boolean leftSymbolIsHigherOrEqualThanRightSymbol = true;

        boolean canBePlaced = leftSymbolIsHigherOrEqualThanRightSymbol;

        assertThat(canBePlaced, is(true));
    }

    @Test
    public void symbol_can_be_placed_before_substractable_symbol() {
        boolean leftSymbolCanBeSubstractedFromRightSymbol = true;

        boolean canBePlaced = leftSymbolCanBeSubstractedFromRightSymbol;

        assertThat(canBePlaced, is(true));
    }

    @Test
    public void symbol_is_higher_if_value_is_higher() {
        int symbol1Value = 3;
        int symbol2Value = 2;
        
        boolean isHigher = symbol1Value > symbol2Value;

        assertThat(isHigher, is(true));
    }
    
    //~~~~~~~
    enum RomanSymbol {

        I(1, null),
        V(5, I),
        X(10, I),
        L(50, X),
        C(100, X),
        D(500, C),
        M(1000, C);
        int value;
        RomanSymbol substractionLiteral;

        public static RomanSymbol fromString(String symbol) {
            RomanSymbol chosenLiteral = null;
            for (RomanSymbol romanLiteral : RomanSymbol.values()) {
                if (romanLiteral.name().equals(symbol)) {
                    chosenLiteral = romanLiteral;
                }
            }
            return chosenLiteral;
        }

        private RomanSymbol(int value, RomanSymbol substractionLiteral) {
            this.value = value;
            this.substractionLiteral = substractionLiteral;
        }

        int getValue() {
            return value;
        }

        public boolean canSubstract(RomanSymbol literalToSubstract) {
            return substractionLiteral.equals(literalToSubstract);
        }
    }

    protected RomanSymbol romanSymbol(String symbol) {
        return RomanSymbol.fromString(symbol);
    }

    protected int valueOf(String symbol) {
        return romanSymbol(symbol).getValue();
    }

    protected boolean canSubstractSymbol(String leftSymbol, String rightSymbol) {
        RomanSymbol rightLiteral = RomanSymbol.fromString(rightSymbol);
        RomanSymbol leftLiteral = RomanSymbol.fromString(leftSymbol);

        return rightLiteral.canSubstract(leftLiteral);
    }
}