/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class Numeral {
    ValueArray valueArray;
    
    
    public Numeral(ValueArray valueArray) {
        this.valueArray = valueArray;
    }
    
    public static Numeral fromString(String romanNumber) {
        ValueArray valueArray = new StringToValueArrayConverter().getValueArray(romanNumber);
        return new Numeral(valueArray);
    }

    public int getValue() {
        int totalValue = 0;
        
        for (int i = 0; i < valueArray.getSize(); i++) {
            totalValue += valueArray.getValueAt(i);
        }
        
        return totalValue;
    }
    
}
