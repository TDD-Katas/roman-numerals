/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
class ContextValueProvider {

    ContextValueProvider() {
    }

    public int computeContextValue(Symbol symbol, Symbol symbolAfter) {
        int contextValue = symbol.getValue();
        boolean symbolIsSubstracted = symbolAfter.canSubstract(symbol);
        if (symbolIsSubstracted) {
            contextValue = -contextValue;
        }
        return contextValue;
    }
    
}
