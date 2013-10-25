/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata.symbols;

import org.junit.Test;
import static org.junit.Assert.*;
import static kata.MocksAndStubsUtils.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class RomanSymbolTest {

    @Test
    public void symbol_can_be_placed_before_other_if_it_is_heigher() {
        Symbol romanSymbolWithHighValue = romanSymbolWithValue(2);
        Symbol romanSymbolWithLowValue = romanSymbolWithValue(1);
        
        assertTrue(romanSymbolWithHighValue.canBePlacedBefore(romanSymbolWithLowValue));
    }
    
    @Test
    public void symbol_can_be_placed_before_other_if_it_is_equal() {
        Symbol romanSymbol = romanSymbolWithValue(1);
        Symbol romanSymbolWithSameValue = romanSymbolWithValue(1);
        
        assertTrue(romanSymbol.canBePlacedBefore(romanSymbolWithSameValue));
    }

    @Test
    public void symbol_can_be_placed_before_other_if_is_substractable_symbol() {
        Symbol substractionSymbol = concreteRomanSymbol();
        Symbol romanSymbol = romanSymbolThatSubstractsAll();

       assertTrue(substractionSymbol.canBePlacedBefore(romanSymbol));
    }
}