/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import static org.mockito.Mockito.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class KataTest {
    private static final int SOME_VALUE = 1;
    private Frame frame;
    
    
    @Before
    public void setUp() {
        frame = mock(Frame.class);
    }
    
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
        when(frame.getStandardScore()).thenReturn(SOME_VALUE);
        when(frame.getBonusScore()).thenReturn(SOME_VALUE);
        when(frame.getScore()).thenCallRealMethod();
        
        int scoreOfFrame = frame.getScore();

        assertThat(scoreOfFrame, is(frame.getStandardScore() + frame.getBonusScore()));
    }

    @Test
    public void bonus_score_of_frame_equals_sum_of_bonus_rolls() {
        int[] bonusRolls = createSomeRolls();
        when(frame.getBonusRolls()).thenReturn(bonusRolls);
        when(frame.getBonusScore()).thenCallRealMethod();

        int bonusScoreForSpare = frame.getBonusScore();

        assertThat(bonusScoreForSpare, is(TestUtils.sumOfValues(bonusRolls)));
    }



    @Test
    public void plain_score_of_frame_equals_sum_of_standard_rolls() {
        int[] standardRolls = createSomeRolls();
        when(frame.getStandardRolls()).thenReturn(standardRolls);
        when(frame.getStandardScore()).thenCallRealMethod();
        
        int sumOfRolls = frame.getStandardScore();

        assertThat(sumOfRolls, is(TestUtils.sumOfValues(standardRolls)));
    }

    //~~~~~~~~~~~
    protected Frame createFrameWithScore(int score) {
        Frame frame1 = mock(Frame.class);
        when(frame1.getScore()).thenReturn(score);
        return frame1;
    }
    
    protected int[] createSomeRolls() {
        int[] standardRolls = new int[]{
            SOME_VALUE, SOME_VALUE
        };
        return standardRolls;
    }
    
    //~~~~~~~~~~~
    public int computeScoreOfGame(Frame[] frames) {
        int scoreOfGame = 0;
        for (Frame frame : frames) {
            scoreOfGame += frame.getScore();
        }
        return scoreOfGame;
    }
    
    protected static int computeSumOfRolls(int[] rolls) {
        int sumOfRolls = 0;
        for (int i = 0; i < rolls.length; i++) {
            sumOfRolls += rolls[i];
        }

        return sumOfRolls;
    }

    static class Frame {
        private int[] standardRolls;
        private int[] bonusRolls;

        public Frame(int[] standardRolls, int[] bonusRolls) {
            this.standardRolls = standardRolls;
            this.bonusRolls = bonusRolls;
        }
        
        public int[] getStandardRolls() {
            return new int[0];
        }
        
        public int[] getBonusRolls() {
            return new int[0];
        }
        
        public int getStandardScore() {
            return computeSumOfRolls(getStandardRolls());
        }
        
        public int getBonusScore() {
            return computeSumOfRolls(getBonusRolls());
        }
        
        public int getScore() {
            return getStandardScore() + getBonusScore();
        }
    }
}