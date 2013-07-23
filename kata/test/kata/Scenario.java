/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 * Helper class for tests
 */
class Scenario {
    private final String romanValue;
    private final int decimalValue;

    public Scenario(String romanValue, int decimalValue) {
        this.romanValue = romanValue;
        this.decimalValue = decimalValue;
    }

    public String getRomanValue() {
        return romanValue;
    }

    public int getDecimalValue() {
        return decimalValue;
    }
    
}
