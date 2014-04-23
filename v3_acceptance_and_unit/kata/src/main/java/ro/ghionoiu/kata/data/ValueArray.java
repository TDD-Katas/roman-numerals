/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata.data;

import java.util.ArrayList;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ValueArray  {
    int[] values;

    public ValueArray(int ... values) {
        this.values = values;
    }
    
    public int getValueAt(int i) {
        return values[i];
    }
    
    public int getSize() {
        return values.length;
    }
}
