/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class Numeral {
    CharacterToValue[] symbols;
    
    
    public Numeral(CharacterToValue ... symbols) {
        this.symbols = symbols;
    }
    
    
    public static Numeral fromString(String romanNumber) {
        CharacterToValue[] symbols = new StringToSymbolsConverter().convert(romanNumber);
        return new Numeral(symbols);
    }

    public int getValue() {
        int value = 0;
        for (CharacterToValue symbol : symbols) {
            value += symbol.getValue();
        }
        
        return value;
    }
    
}
