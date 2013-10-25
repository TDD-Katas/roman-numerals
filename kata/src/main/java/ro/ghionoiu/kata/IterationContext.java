/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import ro.ghionoiu.kata.ValueArray;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class IterationContext {
    ValueArray valueArray;
    int position;

    public IterationContext(ValueArray valueArray, int position) {
        this.valueArray = valueArray;
        this.position = position;
    }

    public int getCurrentValue() {
        return valueArray.getValueAt(position);
    }

    public int getNextValue() {
        int nextValue = 0;
        int nextPosition = position + 1;
        if (nextPosition < valueArray.getSize()) {
            nextValue = valueArray.getValueAt(nextPosition);
        }
        return nextValue;
    }
    
}
