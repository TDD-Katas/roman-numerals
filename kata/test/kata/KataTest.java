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
    private static final int ROLL_NOT_TEN = 9;
    private static final int ROLL_ZERO = 0;
    private static final int SOME_VALUE = 1;

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
    public void score_of_a_frame_equals_plain_score_plus_bonus_score() {
        int plainScore = SOME_VALUE;
        int bonusScore = SOME_VALUE;
        Frame frame = mock(Frame.class);
        when(frame.getPlainScore()).thenReturn(plainScore);
        when(frame.getBonusScore()).thenReturn(bonusScore);
        when(frame.getScore()).thenCallRealMethod();
        
        int scoreOfFrame = frame.getScore();

        assertThat(scoreOfFrame, is(plainScore + bonusScore));
    }

    @Test
    public void bonus_score_of_frame_equals_sum_of_bonus_rolls() {
        int numberOfBonusRolls = 2;
        int[] bonusRolls = new int[]{
            SOME_VALUE, SOME_VALUE
        };
        Frame frame = mock(Frame.class);
        when(frame.getNumberOfBonusRolls()).thenReturn(numberOfBonusRolls);
        when(frame.getBonusRolls()).thenReturn(bonusRolls);
        when(frame.getBonusScore()).thenCallRealMethod();

        int bonusScoreForSpare = frame.getBonusScore();

        assertThat(bonusScoreForSpare, is(TestUtils.sumValues(bonusRolls)));
    }



    @Test
    public void plain_score_of_frame_equals_sum_of_standard_rolls() {
        int numberOfFrameRolls = 2;
        int[] standardRolls = new int[]{
            SOME_VALUE, SOME_VALUE
        };
        Frame frame = mock(Frame.class);
        when(frame.getNumberOfRolls()).thenReturn(numberOfFrameRolls);
        when(frame.getFrameRolls()).thenReturn(standardRolls);
        when(frame.getPlainScore()).thenCallRealMethod();
        
        int sumOfRolls = frame.getPlainScore();

        assertThat(sumOfRolls, is(TestUtils.sumValues(standardRolls)));
    }


    //~~~~~~~~~~~
    public int computeScoreOfGame(Frame[] frames) {
        int scoreOfGame = 0;
        for (Frame frame : frames) {
            scoreOfGame += frame.getScore();
        }
        return scoreOfGame;
    }
    
    protected static int computeSumOfRolls(int numberOfRolls,
            int[] rolls) {
        
        
        int sumOfRolls = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            sumOfRolls += rolls[i];
        }

        return sumOfRolls;
    }

    protected int computeScoreOfFrame(Frame frame) {
        return frame.getPlainScore() + frame.getBonusScore();
    }

    protected Frame createFrameWithScore(int score) {
        Frame frame1 = mock(Frame.class);
        when(frame1.getScore()).thenReturn(score);
        return frame1;
    }
    
    static class Frame {
        
        public int getNumberOfBonusRolls() {
            return 0;
        }
        
        public int getNumberOfRolls() {
            return 0;
        }
        
        public int[] getFrameRolls() {
            return new int[0];
        }
        
        public int[] getBonusRolls() {
            return new int[0];
        }
        
        public int getPlainScore() {
            return computeSumOfRolls(getNumberOfRolls(), getFrameRolls());
        }
        
        public int getBonusScore() {
            return computeSumOfRolls(getNumberOfBonusRolls(), getBonusRolls());
        }
        
        public int getScore() {
            return getPlainScore() + getBonusScore();
        }
    }
}