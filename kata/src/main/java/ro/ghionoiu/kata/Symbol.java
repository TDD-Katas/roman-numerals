/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Symbol {
    private final static Map<String, Integer> SYMBOL_VALUES = new HashMap<String, Integer>();
    static {
        SYMBOL_VALUES.put("I", 1);
        SYMBOL_VALUES.put("V", 5);
        SYMBOL_VALUES.put("X", 10);
        SYMBOL_VALUES.put("L", 50);
        SYMBOL_VALUES.put("C", 100);
        SYMBOL_VALUES.put("D", 500);
        SYMBOL_VALUES.put("M", 1000);
    }
    char character;

    public Symbol(char character) {
        this.character = character;
    }
    
    public int getValue() {
        int value = 0;
        String key = character+"";
        if (SYMBOL_VALUES.containsKey(key)) {
            value = SYMBOL_VALUES.get(key);
        }
        return value;
    }
    
}
