/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class StringToValueArrayConverter {
    CharacterToValueConverter characterConverter;

    public StringToValueArrayConverter() {
        this.characterConverter = new CharacterToValueConverter();
    }
    
    public StringToValueArrayConverter(CharacterToValueConverter characterConverter) {
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
