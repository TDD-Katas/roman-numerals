/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
interface Symbol {

    String getLiteral();

    int getValue();

    boolean canSubstract(Symbol symbol);

    boolean canBePlacedBefore(Symbol symbol);
    
}
