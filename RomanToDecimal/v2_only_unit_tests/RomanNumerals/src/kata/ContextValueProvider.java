/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import kata.symbols.Symbol;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ContextValueProvider {

    public int computeContextValue(Symbol symbol, Symbol symbolAfter) {
        int contextValue = symbol.getValue();
        
        if (symbolAfter != null && symbolAfter.canSubstract(symbol)) {
            contextValue = -contextValue;
        }
        return contextValue;
    }
    
}
