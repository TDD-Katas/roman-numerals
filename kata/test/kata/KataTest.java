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
    public static final String OPEN = "open";
    public static final String SPARE = "spare";
    public static final String STRIKE = "strike";
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void score_of_a_frame_equals_number_of_pins_plus_the_value_of_bonus_rolls() {
        int curentFramePins = 1;
        int valueOfBonusRolls = 2;
        
        int scoreOfFrame = curentFramePins + valueOfBonusRolls;
        
        assertThat(scoreOfFrame, is(curentFramePins+valueOfBonusRolls));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void bonus_score_for_spare_equals_score_of_next_roll() {
        String frameType = SPARE;
        int scoreOfNextRoll = 1;
        
        int bonusScoreForSpare = scoreOfNextRoll;
        
        assertThat(bonusScoreForSpare, is(scoreOfNextRoll));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void bonus_score_for_strike_equals_score_of_next_two_rolls() {
        String frameType = STRIKE;
        int scoreOfNextTwoRolls = 1;
        
        int bonusScoreForStrike = scoreOfNextTwoRolls;
        
        assertThat(bonusScoreForStrike, is(scoreOfNextTwoRolls));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void no_bonus_score_for_open_frame() {
        String frameType = OPEN;
        int bonusScoreOfOpenFrame = 0;
        
        assertThat(bonusScoreOfOpenFrame, is(0));
    }
    
    @Test
    public void score_for_roll_equals_number_of_pins_for_roll() {
        int numberOfPins = 2;
        
        int scoreOfRoll = numberOfPins;
                
        assertThat(scoreOfRoll, equalTo(numberOfPins));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_open_frame_if_sum_of_rolls_different_than_ten() {
        int sumOfFrameRolls = 9;
        
        String frameType = getFrameType(1, sumOfFrameRolls);
        
        assertThat(frameType, is(OPEN));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_spare_if_first_roll_is_not_ten_and_sum_of_rolls_equals_ten() {
        int valueOfFirstRoll = 1;
        int sumOfFrameRolls = 10;
        
        String frameType = getFrameType(valueOfFirstRoll, sumOfFrameRolls);
        
        assertThat(frameType, is(SPARE));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_strike_if_first_roll_is_ten() {
        int valueOfFirstRoll = 10;
        
        String frameType = getFrameType(valueOfFirstRoll, 10);
        
        assertThat(frameType, is(STRIKE));
    }
    
    //~~~~~~~~~~~
    
    protected String getFrameType(int valueOfFirstRoll, int sumOfFrameRolls) {
        String frameType;
        if (sumOfFrameRolls != 10) {
            frameType = OPEN;
        } else {
            if (valueOfFirstRoll == 10) {
                frameType = STRIKE;
            } else {
                frameType = SPARE;
            }
        }
        return frameType;
    }
}