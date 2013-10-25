/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata.symbols;

import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import static kata.symbols.KnownRomanSymbols.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class KnownRomanSymbolsTest {
    
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
    
    //~~~
    
    private int valueOf(Symbol symbol) {
        return symbol.getValue();
    }
}