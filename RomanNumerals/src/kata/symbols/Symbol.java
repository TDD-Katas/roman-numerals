/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata.symbols;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public interface Symbol {

    String getLiteral();

    int getValue();

    boolean canSubstract(Symbol symbol);

    boolean canBePlacedBefore(Symbol symbol);
    
}
