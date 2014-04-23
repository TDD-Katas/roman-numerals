/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata.converter;

import ro.ghionoiu.kata.data.ValueArray;


public class StringToValueArray {
    CharacterToValue characterConverter;

    public StringToValueArray() {
        this.characterConverter = new CharacterToValue();
    }
    
    public StringToValueArray(CharacterToValue characterConverter) {
        this.characterConverter = characterConverter;
    }
    
    public ValueArray getValueArray(String roman) {
        int[] values = new int[roman.length()];
        for (int i = 0; i < values.length; i++) {
            values[i] = this.characterConverter.getValue(roman.charAt(i));
        }
        return new ValueArray(values);
    }
    
}
