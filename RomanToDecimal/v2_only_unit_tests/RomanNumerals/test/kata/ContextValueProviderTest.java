/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import kata.symbols.Symbol;
import static kata.MocksAndStubsUtils.concreteRomanSymbol;
import static kata.MocksAndStubsUtils.romanSymbolThatSubstractsAll;
import static kata.MocksAndStubsUtils.romanSymbolThatSubstractsNothing;
import static kata.MocksAndStubsUtils.romanSymbolWithValue;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ContextValueProviderTest {
    
   @Test
    public void the_context_value_of_a_single_symbol_is_its_value() {
        Symbol symbol = romanSymbolWithValue(1);
        Symbol symbolAfter = null;
        
        int contextValue = computeContextValue(symbol, symbolAfter);
        
       assertThat(contextValue, is(symbol.getValue()));
    }
    
   @Test
    public void the_context_value_of_a_non_substracted_symbol_is_its_value() {
        Symbol symbol = romanSymbolWithValue(1);
        Symbol symbolAfter = romanSymbolThatSubstractsNothing();
        
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
    
    //~~~~
    
    protected int computeContextValue(Symbol symbol, Symbol symbolAfter) {
        ContextValueProvider converter = new ContextValueProvider();
        return converter.computeContextValue(symbol, symbolAfter);
    }
}