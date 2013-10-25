/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import ro.ghionoiu.kata.compute.IterationContext;
import ro.ghionoiu.kata.compute.ValuesAdjuster;
import ro.ghionoiu.kata.data.ValueArray;
import ro.ghionoiu.kata.converter.StringToValueArray;


public class Numeral {
    ValueArray valueArray;
    ValuesAdjuster adjustedValuesProvider;
    
    public Numeral(ValueArray valueArray) {
        this(valueArray, new ValuesAdjuster());
    }
    
    public Numeral(ValueArray valueArray, ValuesAdjuster adjustedValuesProvider) {
        this.valueArray = valueArray;
        this.adjustedValuesProvider = adjustedValuesProvider;
    }
    
    public static Numeral fromString(String romanNumber) {
        ValueArray valueArray = new StringToValueArray().getValueArray(romanNumber);
        return new Numeral(valueArray);
    }

    public int getValue() {
        int totalValue = 0;
        
        for (int i = 0; i < valueArray.getSize(); i++) {
            IterationContext iterationContext = new IterationContext(valueArray, i);
            totalValue += adjustedValuesProvider.compute(iterationContext);
        }
        
        return totalValue;
    }
    
}
