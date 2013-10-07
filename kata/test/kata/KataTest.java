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
    
    @Test
    public void score_of_a_frame_equals_sum_of_frame_rolls_plus_the_value_of_bonus_rolls() {
        int sumOfFrameRolls = PLAIN_ROLL;
        int valueOfBonusRolls = SOME_VALUE;
        
        int scoreOfFrame = computeScoreOfFrame(sumOfFrameRolls, valueOfBonusRolls);
        
        assertThat(scoreOfFrame, is(sumOfFrameRolls+valueOfBonusRolls));
    }
    
    @Test
    public void bonus_score_equals_sum_of_bonus_rolls() {
        int numberOfBonusRolls = 2;
        int[] nextRolls = new int[] {
            SOME_VALUE , SOME_VALUE
        };
        
        int bonusScoreForSpare = computeSumOfRolls( 
                numberOfBonusRolls, nextRolls);
        
        assertThat(bonusScoreForSpare, is(TestUtils.sumValues(nextRolls)));
    }
    
    @Test
    public void a_frame_is_strike_if_first_roll_is_ten() {
        int valueOfFirstRoll = TEN;
        
        String frameType = getFrameType(valueOfFirstRoll, 0);
        
        assertThat(frameType, is(STRIKE));
    }
    
    @Test
    public void a_frame_is_spare_if_first_roll_is_not_ten_and_sum_of_rolls_equals_ten() {
        int valueOfFirstRoll = ROLL_NOT_TEN;
        int valueOfSecondRoll = TEN - valueOfFirstRoll;
        
        String frameType = getFrameType(valueOfFirstRoll, valueOfSecondRoll);
        
        assertThat(frameType, is(SPARE));
    }
    
    @Test
    public void a_frame_is_open_frame_if_first_roll_is_not_ten_and_sum_of_rolls_different_than_ten() {
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;
        
        String frameType = getFrameType(valueOfFirstRoll, valueOfSecondRoll);
        
        assertThat(frameType, is(OPEN));
    }
    
    @Test
    public void sum_of_rolls_equals_sum_of_the_frames_rolls() {
        int numberOfFrameRolls = 2;
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;
        int[] rolls = new int[] {valueOfFirstRoll, valueOfSecondRoll};
        
        int sumOfRolls = computeSumOfRolls(numberOfFrameRolls,
               rolls);
        
        assertThat(sumOfRolls, is(valueOfFirstRoll + valueOfSecondRoll));
    }
    
    @Test
    public void score_of_game_equals_sum_of_frames_scores() {
        int[] frameScores = new int[] {
            SOME_VALUE, SOME_VALUE
        };
        
        int scoreOfGame = computeScoreOfGame(frameScores);
        
        assertThat(scoreOfGame, equalTo(TestUtils.sumValues(frameScores)));
    }
    
    @Test
    public void a_open_frame_has_two_rolls() {
        String frameType = OPEN;
        
        int numberOfRolls = getNumberOfFrameRolls(frameType);
        
        assertThat(numberOfRolls, is(2));
    }
    
    @Test
    public void a_spare_frame_has_two_rolls() {
        String frameType = SPARE;
        
        int numberOfRolls = getNumberOfFrameRolls(frameType);
        
        assertThat(numberOfRolls, is(2));
    }
    
    @Test
    public void a_strike_frame_has_one_roll() {
        String frameType = STRIKE;
        
        int numberOfRolls = getNumberOfFrameRolls(frameType);
        
        assertThat(numberOfRolls, is(1));
    }
    
    @Test
    public void number_of_bonus_rolls_for_strike_is_two() {
        String frameType = STRIKE;

        int bonusRolls = getNumberOfBonusRolls(frameType);
        
        assertThat(bonusRolls, is(2));
    }
    
    @Test
    public void number_of_bonus_rolls_for_spare_is_one() {
        String frameType = SPARE;

        int bonusRolls = getNumberOfBonusRolls(frameType);
        
        assertThat(bonusRolls, is(1));
    }
    
    @Test
    public void number_of_bonus_rolls_for_open_frame_is_zero() {
        String frameType = OPEN;

        int bonusRolls = getNumberOfBonusRolls(frameType);
        
        assertThat(bonusRolls, is(0));
    }
    
    //~~~~~~~~~~~
    

    public int computeScoreOfGame(int[] frameScores) {
        int scoreOfGame = 0;
        for (int i : frameScores) {
            scoreOfGame += i;
        }
        return scoreOfGame;
    }
    
    protected int computeSumOfRolls(int numberOfRolls,
            int[] rolls) {
        int sumOfRolls = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            sumOfRolls += rolls[i];
        }
        
        return sumOfRolls;
    }
    
    protected int computeScoreOfFrame(int sumOfFrameRolls, int valueOfBonusRolls) {
        return sumOfFrameRolls + valueOfBonusRolls;
    }
    
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
    

    protected int getNumberOfFrameRolls(String frameType) {
        int numberOfRolls = 2;
        if(STRIKE.equals(frameType)) {
            numberOfRolls = 1;
        }
        
        return numberOfRolls;
    }

    protected int getNumberOfBonusRolls(String frameType) {
        int numberOfBonusRolls = 0;
        
        if (STRIKE.equals(frameType)) {
            numberOfBonusRolls = 2;
        } else
        if (SPARE.equals(frameType)) {
            numberOfBonusRolls = 1;
        }
        
        return numberOfBonusRolls;
    }
}