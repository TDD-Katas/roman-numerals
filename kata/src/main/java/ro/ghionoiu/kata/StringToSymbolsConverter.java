/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

//~~~~~~

import ro.ghionoiu.kata.Symbol;


public class StringToSymbolsConverter {

    public Symbol[] convert(String roman) {
        Symbol[] symbols = new Symbol[roman.length()];
        for (int i = 0; i < symbols.length; i++) {
            symbols[i] = new Symbol(roman.charAt(i));
        }
        return symbols;
    }
    
}
