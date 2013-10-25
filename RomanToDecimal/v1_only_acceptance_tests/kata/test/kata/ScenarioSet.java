/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import java.util.LinkedList;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class ScenarioSet extends LinkedList<Scenario>{

    public boolean add(int expectedValue, String romanNumeral) {
        return super.add(new Scenario(expectedValue, romanNumeral)); 
    }
}
