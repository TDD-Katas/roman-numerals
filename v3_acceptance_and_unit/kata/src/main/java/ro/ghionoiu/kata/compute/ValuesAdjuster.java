/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata.compute;

///~~~~~~~~~~~~~~~~~~~


public class ValuesAdjuster {

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
