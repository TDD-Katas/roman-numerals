/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class Numeral {
    int[] symbols;
    
    
    public Numeral(int ... symbols) {
        this.symbols = symbols;
    }
    
    
    public static Numeral fromString(String romanNumber) {
        int[] symbols = new StringToSymbolsConverter().convert(romanNumber);
        return new Numeral(symbols);
    }

    public int getValue() {
        int value = 0;
        for (int symbol : symbols) {
            value += symbol;
        }
        
        return value;
    }
    
}
