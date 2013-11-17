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
        Rolls rolls = anyRolls();
        
        Frame firstFrame = rolls.getFirstFrame();
        
        assertThat(firstFrame.getStartingIndex(), is(0));
    }
    
    @Test
    public void first_frame_has_a_length_of_1_if_its_first_roll_is_maximum_value() {
        Rolls rolls = firstRollIsMaximumRoll();
        
        Frame firstFrame = rolls.getFirstFrame();
        
        assertThat(firstFrame.getLength(), is(1));
    }
        
    @Test
    public void first_frame_has_a_length_of_2_if_its_first_roll_is_maximum_value() {
        Rolls rolls = firstRollNotMaximumRoll();
        
        Frame firstFrame = rolls.getFirstFrame();
        
        assertThat(firstFrame.getLength(), is(2));
    }
    
        
    @Test
    public void frame_ends_at_starting_index_plus_length() {
        int startingIndex = 1;
        int length = 2;
        
        Frame frame = new Frame(startingIndex, length);
        
        assertThat(frame.getEndingIndex(), is(startingIndex+length));
    }
    
    @Test
    public void second_frame_starts_at_first_frame_ending_index() {
        int firstFrameEndingIndex = 2;
        Rolls rolls = firstFrameEndsAt(2);
        
        Frame secondFrame = new Frame(firstFrameEndingIndex, 1);
        
        assertThat(secondFrame.getStartingIndex(), is(firstFrameEndingIndex));
    }
    
    //~~~~~~~~~~~~~~ Test helpers ~~~~~~~~

    protected int[] rollAllAs(int rollValue) {
        int[] rolls = new int[10];
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = rollValue;
        }
        return rolls;
    }
    protected Rolls anyRolls() {
        return Rolls.create(NORMAL_ROLL, NORMAL_ROLL);
    }
    
    protected Rolls firstRollIsMaximumRoll() {
        return Rolls.create(MAXIMUM_ROLL, NORMAL_ROLL);
    }
    
    protected Rolls firstRollNotMaximumRoll() {
        return Rolls.create(NORMAL_ROLL, NORMAL_ROLL);
    }
    
    protected Rolls firstFrameEndsAt(int firstFrameEnding) {
        return Rolls.create(NORMAL_ROLL, NORMAL_ROLL, NORMAL_ROLL, NORMAL_ROLL);
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


    static class Frame {
        int startingIndex;
        int length;

        public Frame(int startingIndex, int length) {
            this.startingIndex = startingIndex;
            this.length = length;
        }
        

        public int getStartingIndex() {
            return startingIndex;
        }

        public int getLength() {
            return length;
        }
        
        public int getEndingIndex() {
            return startingIndex + length;
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
        
        protected Frame getFirstFrame() {
            int startingIndex = getFirstFrameStartingIndex();
            int length = getFirstFrameLength();
            return new Frame(startingIndex, length);
        }

        protected int getFirstFrameStartingIndex() {
            return 0;
        }

        protected int getFirstFrameLength() {
            if (array[0] == MAXIMUM_ROLL) {
                return 1;
            } else {
                return 2;
            }
        }
    }
}