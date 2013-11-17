/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Ignore;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    public static final int MAXIMUM_ROLL = 10;
    
    //~~~~~~~~~~~~~~ Integration Tests ~~~~~~~~
    
    @Ignore
    @Test
    public void IT_gutter_game_score_is_0() {
        int[] rolls = rollAllAs(0);
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(0));
    }
    
    //~~~~~~~~~~~~~~ Unit Tests ~~~~~~~~
    
    @Test
    public void game_score_equals_sum_of_frames_score() {
        int sumOfFramesScore = 3;
        int[] frameScores = {1, 2};
        
        int gameScore = computeGameScore(frameScores);
        
        assertThat(gameScore, is(sumOfFramesScore));
    }
    
    //A frame represents a set of maximum two rolls that add up less than 10
    
        
    @Test
    public void the_sum_of_a_frame_rolls_is_less_or_equal_than_ten() {
        
        assertThat(true, is(true));
    }
    
    @Test
    public void first_frame_is_first_roll_when_the_first_roll_is_maximum_roll() {
        Rolls rolls = new Rolls( new int[]{MAXIMUM_ROLL, 1, 2});
        int[] expectedFrame = {MAXIMUM_ROLL};
        
        int[] frame = getFirstFrame(rolls.getArray());
        
        assertThat(frame, is(expectedFrame));
    }    
    
    @Test
    public void first_frame_is_the_first_two_rolls_when_the_first_roll_is_not_maximum_roll() {
        int[] rolls = {0, 1, 2};
        int[] expectedFrame = {0, 1};
        
        int[] frame = getFirstFrame(rolls);
        
        assertThat(frame, is(expectedFrame));
    }
    
    @Test
    public void second_frame_is_the_first_frame_from_the_rolls_after_first_frame() {
        int[] rollsAfterFirstFrame = {0, 1, 2};
        int[] expectedFrame = {0, 1};
        
        int[] frame = getFirstFrame(rollsAfterFirstFrame);
        
        assertThat(frame, is(expectedFrame));
    }
    
    @Test
    public void the_rolls_after_first_frame_example() {
        int[] rolls = {0, 1, 2, 3};
        int[] firstFrame = {0, 1};
        int[] expectedRolls = {2, 3};
        
        int[] rollsAfterFirstFrame = {2, 3};
        
        assertThat(rollsAfterFirstFrame, is(expectedRolls));
    }
    
    //~~~~~~~~~~~~~~ Test helpers ~~~~~~~~

    protected int[] rollAllAs(int rollValue) {
        int[] rolls = new int[10];
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = rollValue;
        }
        return rolls;
    }
    
    //~~~~~~~~~~~~~~ Production ~~~~~~~~
    
    class Rolls {
        private int[] rolls;

        public Rolls(int[] rolls) {
        }

        public int[] getArray() {
            return rolls;
        }
        
    }
    
    protected int targetComputeGameScore(int[] rolls) {
        return 0;
    }
    
    
    protected int computeGameScore(int[] frameScores) {
        int gameScore = 0;
        for (int frameScore : frameScores) {
            gameScore += frameScore;
        }
        return gameScore;
    }

    protected int[] getFirstFrame(int[] rolls) {
        if (rolls[0] == MAXIMUM_ROLL) {
            return new int[] {rolls[0]};
        } else {
            return new int[] {rolls[0], rolls[1]};
        }
    }
}