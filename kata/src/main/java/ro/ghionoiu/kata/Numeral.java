/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class Numeral {
    Symbol symbol;
    public Numeral(Symbol symbol) {
        this.symbol = symbol;
    }

    public int getValue() {
        return symbol.getValue();
    }
    
}
