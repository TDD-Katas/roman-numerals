/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
class RomanSymbol {
    private final String symbol;
    private final String substractionSymbol;
    private final int value;

    public RomanSymbol(String symbol, int value) {
        this(symbol, "", value);
    }

    public RomanSymbol(String symbol, String substractionSymbol, int value) {
        this.symbol = symbol;
        this.substractionSymbol = substractionSymbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public boolean dominates(String roman) {
        return roman.startsWith(symbol) || 
                roman.startsWith(substractionSymbol + symbol);
    }
}
