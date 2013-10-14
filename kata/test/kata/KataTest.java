/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class KataTest {
    public static final int PLAIN_ROLL = 1;
    public static final int ROLL_NOT_TEN = 9;
    public static final int TEN = 10;
    public static final int ROLL_ZERO = 0;
    public static final int SOME_VALUE = 1;

    @Test
    public void score_of_game_equals_sum_of_frames_scores() {
        int firstFrameScore = SOME_VALUE;
        int secondFrameScore = SOME_VALUE;
        Frame[] frames = new Frame[] {
            createFrameWithScore(firstFrameScore),
            createFrameWithScore(secondFrameScore)
        };

        int scoreOfGame = computeScoreOfGame(frames);

        assertThat(scoreOfGame, equalTo(firstFrameScore + secondFrameScore));
    }

    @Test
    public void score_of_a_frame_equals_sum_of_frame_rolls_plus_the_value_of_bonus_rolls() {
        int sumOfFrameRolls = PLAIN_ROLL;
        int valueOfBonusRolls = SOME_VALUE;
        Frame frame = mock(Frame.class);
        when(frame.getSumOfRolls()).thenReturn(sumOfFrameRolls);
        when(frame.getValueOfBonusRolls()).thenReturn(valueOfBonusRolls);
        when(frame.getScore()).thenCallRealMethod();
        
        int scoreOfFrame = frame.getScore();

        assertThat(scoreOfFrame, is(sumOfFrameRolls + valueOfBonusRolls));
    }

    @Test
    public void bonus_score_equals_sum_of_bonus_rolls() {
        int numberOfBonusRolls = 2;
        int[] nextRolls = new int[]{
            SOME_VALUE, SOME_VALUE
        };

        int bonusScoreForSpare = computeSumOfRolls(
                numberOfBonusRolls, nextRolls);

        assertThat(bonusScoreForSpare, is(TestUtils.sumValues(nextRolls)));
    }



    @Test
    public void sum_of_frame_rolls_equals_sum_of_rolls_values() {
        int numberOfFrameRolls = 2;
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;
        int[] rolls = new int[]{valueOfFirstRoll, valueOfSecondRoll};

        int sumOfRolls = computeSumOfFrameRolls(numberOfFrameRolls,
                rolls);

        assertThat(sumOfRolls, is(valueOfFirstRoll + valueOfSecondRoll));
    }



    //~~~~~~~~~~~
    public int computeScoreOfGame(Frame[] frames) {
        int scoreOfGame = 0;
        for (Frame frame : frames) {
            scoreOfGame += frame.getScore();
        }
        return scoreOfGame;
    }
    
    protected int computeSumOfFrameRolls(int numberOfRolls,
            int[] rolls) {
        Frame frame = mock(Frame.class);
        when(frame.getNumberOfRolls()).thenReturn(numberOfRolls);
        return computeSumOfRolls(frame.getNumberOfRolls(), rolls);
    }

    protected int computeSumOfRolls(int numberOfRolls,
            int[] rolls) {
        
        
        int sumOfRolls = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            sumOfRolls += rolls[i];
        }

        return sumOfRolls;
    }

    protected int computeScoreOfFrame(Frame frame) {
        return frame.getSumOfRolls() + frame.getValueOfBonusRolls();
    }

    protected Frame createFrameWithScore(int score) {
        Frame frame1 = mock(Frame.class);
        when(frame1.getScore()).thenReturn(score);
        return frame1;
    }
    
    static class Utility {
        public int computeSumOfRolls(int numberOfRolls,
            int[] rolls) {
            return 0;
        }
    }
    
    
    static class Frame {
        
        public int getNumberOfRolls() {
            return 0;
        }
        
        public int[] getFrameRolls() {
            return null;
        }
        
        public int getSumOfRolls() {
            return 0;
        }
        
        public int getValueOfBonusRolls() {
            return 0;
        }
        
        public int getScore() {
            return getSumOfRolls() + getValueOfBonusRolls();
        }
    }
}