/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class Numeral {
    ValueArray valueArray;
    AdjustedValuesProvider adjustedValuesProvider;
    
    public Numeral(ValueArray valueArray) {
        this(valueArray, new AdjustedValuesProvider());
    }
    
    public Numeral(ValueArray valueArray, AdjustedValuesProvider adjustedValuesProvider) {
        this.valueArray = valueArray;
        this.adjustedValuesProvider = adjustedValuesProvider;
    }
    
    public static Numeral fromString(String romanNumber) {
        ValueArray valueArray = new StringToValueArrayConverter().getValueArray(romanNumber);
        return new Numeral(valueArray);
    }

    public int getValue() {
        int totalValue = 0;
        
        for (int i = 0; i < valueArray.getSize(); i++) {
            totalValue += adjustedValuesProvider.compute(new IterationContext(valueArray, i));
        }
        
        return totalValue;
    }
    
}
