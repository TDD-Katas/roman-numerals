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
    
    
    public static final int PLAIN_ROLL = 1;
    public static final int ROLL_NOT_TEN = 9;
    public static final int TEN = 10;
    public static final int ROLL_ZERO = 0;
    public static final int SOME_VALUE = 1;
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void score_of_a_frame_equals_sum_of_frame_rolls_plus_the_value_of_bonus_rolls() {
        int sumOfFrameRolls = PLAIN_ROLL;
        int valueOfBonusRolls = SOME_VALUE;
        
        int scoreOfFrame = sumOfFrameRolls + valueOfBonusRolls;
        
        assertThat(scoreOfFrame, is(sumOfFrameRolls+valueOfBonusRolls));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void bonus_score_for_spare_equals_score_of_next_roll() {
        String frameType = SPARE;
        int scoreOfNextRoll = SOME_VALUE;
        
        int bonusScoreForSpare = computeBonusScoreForFrame(frameType, 
                scoreOfNextRoll, SOME_VALUE);
        
        assertThat(bonusScoreForSpare, is(scoreOfNextRoll));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void bonus_score_for_strike_equals_score_of_next_two_rolls() {
        String frameType = STRIKE;
        int scoreOfNextRoll = SOME_VALUE;
        int scoreOfSecondNextRoll = SOME_VALUE;
        
        int bonusScoreForStrike = computeBonusScoreForFrame(frameType, 
                scoreOfNextRoll, scoreOfSecondNextRoll);
        
        assertThat(bonusScoreForStrike, is(scoreOfNextRoll+scoreOfSecondNextRoll));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void bonus_score_for_open_frame_is_zero() {
        String frameType = OPEN;
        
        int bonusScoreOfOpenFrame = computeBonusScoreForFrame(frameType, 
                SOME_VALUE, SOME_VALUE);
        
        assertThat(bonusScoreOfOpenFrame, is(0));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_strike_if_first_roll_is_ten() {
        int valueOfFirstRoll = TEN;
        
        String frameType = getFrameType(valueOfFirstRoll, 0);
        
        assertThat(frameType, is(STRIKE));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_spare_if_first_roll_is_not_ten_and_sum_of_rolls_equals_ten() {
        int valueOfFirstRoll = ROLL_NOT_TEN;
        int valueOfSecondRoll = TEN - valueOfFirstRoll;
        
        String frameType = getFrameType(valueOfFirstRoll, valueOfSecondRoll);
        
        assertThat(frameType, is(SPARE));
    }
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void a_frame_is_open_frame_if_first_roll_is_not_ten_and_sum_of_rolls_different_than_ten() {
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;
        
        String frameType = getFrameType(valueOfFirstRoll, valueOfSecondRoll);
        
        assertThat(frameType, is(OPEN));
    }
    
    @Test
    public void sum_of_rolls_for_a_open_frame_equals_sum_of_first_and_second_roll() {
        String frameType = OPEN;
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;
        
        int sumORolls = computeSumOfRollsForFrame(frameType, valueOfFirstRoll, valueOfSecondRoll);
        
        assertThat(sumORolls, is(valueOfFirstRoll + valueOfSecondRoll));
    }
    
    @Test
    public void sum_of_rolls_for_a_spare_frame_equals_sum_of_first_and_second_roll() {
        String frameType = SPARE;
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;
        
        int sumORolls = computeSumOfRollsForFrame(frameType, valueOfFirstRoll, valueOfSecondRoll);
        
        assertThat(sumORolls, is(valueOfFirstRoll + valueOfSecondRoll));
    }
    
    @Test
    public void sum_of_rolls_for_a_strike_frame_equals_value_of_first_roll() {
        String frameType = STRIKE;
        int valueOfFirstRoll = ROLL_NOT_TEN;
        
        int sumORolls = computeSumOfRollsForFrame(frameType, valueOfFirstRoll, ROLL_ZERO);
        
        assertThat(sumORolls, is(valueOfFirstRoll));
    }
    
    //~~~~~~~~~~~
    
    
    protected String getFrameType(int valueOfFirstRoll, int valueOfSecondRoll) {
        String frameType = OPEN;
        if (valueOfFirstRoll == 10) {
            frameType = STRIKE;
        } else 
        if (valueOfFirstRoll + valueOfSecondRoll == 10) {
            frameType = SPARE;
        }
        
        return frameType;
    }

    protected int computeSumOfRollsForFrame(String frameType, int valueOfFirstRoll, int valueOfSecondRoll) {
        int sumOfRolls;
        if (STRIKE.equals(frameType)) {
            sumOfRolls = valueOfFirstRoll;
        } else {
            sumOfRolls = valueOfFirstRoll + valueOfSecondRoll;
        }
        
        return sumOfRolls;
    }

    protected int computeBonusScoreForFrame(String frameType, int scoreOfNextRoll, int scoreOfSecondNextRoll) {
        if (STRIKE.equals(frameType)) {
            return scoreOfNextRoll+scoreOfSecondNextRoll;
        } else 
        if (SPARE.equals(frameType)) {
            return scoreOfNextRoll;
        } else {
            return 0;
        }
    }
}