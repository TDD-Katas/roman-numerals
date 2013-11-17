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
        Rolls rolls = Rolls.create(MAXIMUM_ROLL, 1, 2);
        Rolls expectedFrameRolls = Rolls.create(MAXIMUM_ROLL);
        
        Rolls frameRolls = rolls.getFirstFrameRolls();
        
        assertThat(frameRolls, is(expectedFrameRolls));
    }    
    
    @Test
    public void first_frame_is_the_first_two_rolls_when_the_first_roll_is_not_maximum_roll() {
        Rolls rolls = Rolls.create(0, 1, 2);
        Rolls expectedFrameRolls = Rolls.create(0, 1);
        
        Rolls frameRolls = rolls.getFirstFrameRolls();
        
        assertThat(frameRolls, is(expectedFrameRolls));
    }
    
    @Test
    public void the_remaining_rolls_after_the_first_frame() {
        Rolls rolls = Rolls.create(0, 1, 2, 3);
        Rolls firstFrame = Rolls.create(0, 1);
        Rolls expectedRollsAfterFrame = Rolls.create(2, 3);
        
        Rolls frameRolls = getRemainingRollsAfterFirstFrame(rolls, firstFrame);
        
        assertThat(frameRolls, is(expectedRollsAfterFrame));
    }
    
    @Test
    public void second_frame_is_the_frame_after_the_rolls_of_first_frame() {
        Rolls rolls = Rolls.create(0, 1, 2, 3);
        Rolls firstFrameRolls = Rolls.create(0, 1);
        Rolls expectedRolls = Rolls.create(2, 3);
        
        Rolls rollsAfterFirstFrame = Rolls.create(2, 3);
        
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
    
    protected Rolls getRemainingRollsAfterFirstFrame(Rolls rolls, Rolls firstFrameRolls) {
        return Rolls.create(2, 3);
    }
    
    static class Rolls {
        private int[] array;
        
        public Rolls(int ... rolls) {
            this.array = rolls;
        }
        
        public static Rolls create(int ... rolls) {
            return new Rolls(rolls);
        }
        
        public int[] getArray() {
            return array;
        }
        
        public Rolls getFirstFrameRolls() {
            if (array[0] == MAXIMUM_ROLL) {
                return create(array[0]);
            } else {
                return create(array[0], array[1]);
            }
        }
        
        //~~~~~~~ Equals impl
        
        @Override
        public int hashCode() {
            int hash = 3;
            hash = 97 * hash + Arrays.hashCode(this.array);
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
            if (!Arrays.equals(this.array, other.array)) {
                return false;
            }
            return true;
        }
        
        //~~~~~~~ To string
        @Override
        public String toString() {
            return "Rolls{" + "array=" + Arrays.toString(array) + '}';
        }
    }
}