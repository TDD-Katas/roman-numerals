/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class KataTest {
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void score_of_a_spare_equals_current_frame_pins_plus_score_of_next_roll() {
        int curentFramePins = 1;
        int scoreOfNextRoll = 2;
        
        int scoreOfSpare = curentFramePins + scoreOfNextRoll;
        
        assertThat(scoreOfSpare, is(curentFramePins+scoreOfNextRoll));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void score_of_a_strike_equals_current_frame_pins_plus_score_of_next_two_rolls() {
        int curentFramePins = 1;
        int scoreOfNextTwoRolls = 2;
        
        int scoreOfStrike = curentFramePins + scoreOfNextTwoRolls;
        
        assertThat(scoreOfStrike, is(curentFramePins+scoreOfNextTwoRolls));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void score_of_an_open_frame_equals_current_frame_pins() {
        int curentFramePins = 3;
        
        int scoreOfOpenFrame = curentFramePins;
        
        assertThat(scoreOfOpenFrame, is(curentFramePins));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_open_frame_if_sum_of_rolls_different_than_ten() {
        int sumOfFrameRolls = 9;
        
        boolean isOpenFrame = sumOfFrameRolls != 10;
        
        assertTrue(isOpenFrame);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_spare_if_sum_of_rolls_equals_ten() {
        int sumOfFrameRolls = 10;
        
        boolean isSpare = sumOfFrameRolls == 10;
        
        assertTrue(isSpare);
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_strike_if_first_roll_is_ten() {
        int valueOfFirstRoll = 10;
        
        boolean isStrike = valueOfFirstRoll == 10;
        
        assertTrue(isStrike);
    }
}