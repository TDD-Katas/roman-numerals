/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

//~~~~~~

import ro.ghionoiu.kata.CharacterToValueConverter;


public class StringToValuesConverter {
    public int[] getValues(String roman) {
        int[] values = new int[roman.length()];
        for (int i = 0; i < values.length; i++) {
            values[i] = new CharacterToValueConverter().getValue(roman.charAt(i));
        }
        return values;
    }
    
}
