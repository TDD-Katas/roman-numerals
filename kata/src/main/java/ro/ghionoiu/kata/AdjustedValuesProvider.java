/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

///~~~~~~~~~~~~~~~~~~~

import ro.ghionoiu.kata.IterationContext;


public class AdjustedValuesProvider {

    public int compute(IterationContext iterationContext) {
        int currentValue = iterationContext.getCurrentValue();
        int nextValue = iterationContext.getNextValue();
        int adjustedValue = currentValue;
        if (currentValue < nextValue) {
            adjustedValue = -currentValue;
        }
        return adjustedValue;
    }
    
}
