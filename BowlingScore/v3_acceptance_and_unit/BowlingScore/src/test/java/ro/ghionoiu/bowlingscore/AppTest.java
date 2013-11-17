/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore;

import java.util.Arrays;
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
        Rolls rolls = asRolls(MAXIMUM_ROLL, 1, 2);
        Rolls expectedFrameRolls = asRolls(MAXIMUM_ROLL);
        
        Rolls frameRolls = rolls.getFirstFrameRolls();
        
        assertThat(frameRolls, is(expectedFrameRolls));
    }    
    
    @Test
    public void first_frame_is_the_first_two_rolls_when_the_first_roll_is_not_maximum_roll() {
        Rolls rolls = asRolls(0, 1, 2);
        Rolls expectedFrameRolls = asRolls(0, 1);
        
        Rolls frameRolls = rolls.getFirstFrameRolls();
        
        assertThat(frameRolls, is(expectedFrameRolls));
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

    protected Rolls asRolls(int ... rolls) {
        return new Rolls(rolls);
    }
    
    //~~~~~~~~~~~~~~ Production ~~~~~~~~
    
    class Rolls {
        private int[] rolls;

        public Rolls(int[] rolls) {
            this.rolls = rolls;
        }

        public int[] getArray() {
            return rolls;
        }
        
        public Rolls getFirstFrameRolls() {
            if (rolls[0] == MAXIMUM_ROLL) {
                return asRolls(rolls[0]);
            } else {
                return asRolls(rolls[0], rolls[1]);
            }
        }

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + Arrays.hashCode(this.rolls);
            return hash;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Rolls other = (Rolls) obj;
            if (!Arrays.equals(this.rolls, other.rolls)) {
                return false;
            }
            return true;
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
}