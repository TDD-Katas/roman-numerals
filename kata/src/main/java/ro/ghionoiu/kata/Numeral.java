/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;


public class Numeral {
    int[] values;
    
    
    public Numeral(int ... values) {
        this.values = values;
    }
    
    public static Numeral fromString(String romanNumber) {
        int[] symbols = new StringToValuesConverter().getValues(romanNumber);
        return new Numeral(symbols);
    }

    public int getValue() {
        int totalValue = 0;
        for (int currentValue : values) {
            totalValue += currentValue;
        }
        
        return totalValue;
    }
    
}
