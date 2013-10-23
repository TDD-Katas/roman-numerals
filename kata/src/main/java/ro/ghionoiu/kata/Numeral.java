/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class Numeral {
    Symbol[] symbols;
    
    
    public Numeral(Symbol ... symbols) {
        this.symbols = symbols;
    }
    
    
    public static Numeral fromString(String romanNumber) {
        Symbol[] symbols = new StringToSymbolsConverter().convert(romanNumber);
        return new Numeral(symbols);
    }

    public int getValue() {
        int value = 0;
        for (Symbol symbol : symbols) {
            value += symbol.getValue();
        }
        
        return value;
    }
    
}
