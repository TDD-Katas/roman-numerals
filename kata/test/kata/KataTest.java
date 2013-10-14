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
    public void standard_rolls_for_frame_are_the_rolls_equals_to_frame_type() {
        int[] rolls = new int[] {1, 2, 3};
        int standardRollsNumber = 2;
        
        int[] expectedRolls = new int[] {
            1, 2
        };
        
        int currentIndex = 0;
        int numberOfRolls = standardRollsNumber;
        int[] standardFrameRolls = new int[numberOfRolls];
        standardFrameRolls[0] = rolls[currentIndex];
        standardFrameRolls[1] = rolls[currentIndex+1];
        
        assertArrayEquals(expectedRolls, standardFrameRolls);
    }
    
    @Test
    public void next_rolls_for_frame_are_the_rolls_after_standard_rolls() {
        int[] rolls = new int[] {1, 2, 3, 4};
        int standardRollsNumber = 2;
        
        int[] expectedRolls = new int[] {
            3, 4
        };
        
        int currentIndex = standardRollsNumber;
        int numberOfRolls = rolls.length - standardRollsNumber;
        int[] nextFrameRolls = new int[numberOfRolls];
        for (int i = 0; i < numberOfRolls; i++) {
            nextFrameRolls[i] = rolls[currentIndex+i];
        }
        
        assertArrayEquals(expectedRolls, nextFrameRolls);
    }
    
    @Test
    public void bonus_rolls_for_frame_are_the_rolls_from_the_next_rolls_according_to_frame_type() {
        int[] nextRolls = new int[] {1, 2, 3, 4};
        int bonusRollsNumber = 2;
        
        int[] expectedRolls = new int[] {
            1, 2
        };
        
        int currentIndex = 0;
        int numberOfRolls = bonusRollsNumber;
        int[] standardFrameRolls = new int[numberOfRolls];
        standardFrameRolls[0] = nextRolls[currentIndex];
        standardFrameRolls[1] = nextRolls[currentIndex+1];
        
        assertArrayEquals(expectedRolls, standardFrameRolls);
    }
    
    //~~~~~~~~~~~
    protected Frame createFrameWithScore(int score) {
        Frame frame1 = mock(Frame.class);
        when(frame1.getScore()).thenReturn(score);
        return frame1;
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
        return Utils.sumOfValues(rolls);
    }
}