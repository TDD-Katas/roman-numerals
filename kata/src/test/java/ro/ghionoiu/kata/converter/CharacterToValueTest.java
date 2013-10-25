/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.kata.converter;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class CharacterToValueTest {
    CharacterToValue instance;

    @Before
    public void setUp() throws Exception {
        instance = new CharacterToValue();
    }
    
    @Test
    public void value_of_I_is_1() {
        assertCharacterValueIs(1, 'I');
    }
    
    @Test
    public void value_of_V_is_5() {
        assertCharacterValueIs(5, 'V');
    }
    
    @Test
    public void value_of_X_is_10() {
        assertCharacterValueIs(10, 'X');
    }
    
    @Test
    public void value_of_L_is_50() {
        assertCharacterValueIs(50, 'L');
    }
    
    @Test
    public void value_of_C_is_100() {
        assertCharacterValueIs(100, 'C');
    }
    
    @Test
    public void value_of_D_is_500() {
        assertCharacterValueIs(500, 'D');
    }
    
    @Test
    public void value_of_D_is_1000() {
        assertCharacterValueIs(1000, 'M');
    }

    
    protected void assertCharacterValueIs(int value, char character) {
        assertThat(instance.getValue(character), equalTo(value));
    }
}