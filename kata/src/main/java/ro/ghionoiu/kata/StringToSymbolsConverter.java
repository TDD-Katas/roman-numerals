/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

//~~~~~~

import ro.ghionoiu.kata.CharacterToValue;


public class StringToSymbolsConverter {

    public CharacterToValue[] convert(String roman) {
        CharacterToValue[] symbols = new CharacterToValue[roman.length()];
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = new CharacterToValue(roman.charAt(i));
        }
        return symbols;
    }
    
}
