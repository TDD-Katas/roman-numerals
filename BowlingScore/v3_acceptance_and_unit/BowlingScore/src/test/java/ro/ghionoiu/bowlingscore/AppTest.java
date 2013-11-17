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
    public static final int NORMAL_ROLL = 2;
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
    public void first_frame_starts_at_index_0() {
        Rolls rolls = Rolls.create(NORMAL_ROLL, NORMAL_ROLL);
        
        Frame firstFrame = getFirstFrame(rolls);
        
        assertThat(firstFrame.getStartingIndex(), is(0));
    }
    
    @Test
    public void first_frame_ends_at_index_1_if_first_roll_is_maximum_roll() {
        Rolls rolls = Rolls.create(MAXIMUM_ROLL, NORMAL_ROLL);
        
        Frame firstFrame = getFirstFrame(rolls);
        
        assertThat(firstFrame.getEndingIndex(), is(1));
    }
        
    @Test
    public void first_frame_ends_at_index_2_if_first_roll_is_not_maximum_roll() {
        Rolls rolls = Rolls.create(NORMAL_ROLL, NORMAL_ROLL);
        
        Frame firstFrame = getFirstFrame(rolls);
        
        assertThat(firstFrame.getEndingIndex(), is(2));
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

    
    protected Frame getFirstFrame(Rolls rolls) {
        int startingIndex = getFirstFrameStartingIndex(rolls);
        int endingIndex = getFirstFrameEndingIndex(rolls);
        return new Frame(startingIndex, endingIndex);
    }
    
    protected int getFirstFrameStartingIndex(Rolls rolls) {
        return 0;
    }

    protected int getFirstFrameEndingIndex(Rolls rolls) {
        if (rolls.getArray()[0] == MAXIMUM_ROLL) {
            return 1;
        } else {
            return 2;
        }
    }
    
    static class Frame {
        int startingIndex;
        int endingIndex;

        public Frame(int startingIndex, int endingIndex) {
            this.startingIndex = startingIndex;
            this.endingIndex = endingIndex;
        }

        public int getStartingIndex() {
            return startingIndex;
        }

        public int getEndingIndex() {
            return endingIndex;
        }
        
        
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
    }
}