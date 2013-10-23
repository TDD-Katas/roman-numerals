/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class StringToValuesConverter {
    CharacterToValueConverter characterConverter;

    public StringToValuesConverter() {
        this.characterConverter = new CharacterToValueConverter();
    }
    
    public StringToValuesConverter(CharacterToValueConverter characterConverter) {
        this.characterConverter = characterConverter;
    }
    
    public int[] getValues(String roman) {
        int[] values = new int[roman.length()];
        for (int i = 0; i < values.length; i++) {
            values[i] = this.characterConverter.getValue(roman.charAt(i));
        }
        return values;
    }
    
}
