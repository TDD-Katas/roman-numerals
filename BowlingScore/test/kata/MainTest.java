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
public class MainTest {
    private static final int SOME_VALUE = 1;
    
    @Test
    public void a_game_has_ten_frames() {
        int numberOfFrames = 10;
        
        assertThat(numberOfFrames, is(10));
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
    public void standard_rolls_for_frame_is_the_subset_from_rolls_according_to_frametype() {
        int[] rolls = createSomeRolls();
        int rollsNumber = 2;
        FrameType frameType = frameTypeWithRollsNumber(rollsNumber);
        
        int[] standardFrameRolls = getStandardRolls(frameType, rolls);
        
        assertArrayEquals(
                subsetOfRolls(rolls, 0, rollsNumber), 
                standardFrameRolls);
    }
    
    
    @Test
    public void bonus_rolls_for_frame_is_the_subset_from_next_rolls_according_to_frametype() {
        int[] nextRolls = createSomeRolls();
        int bonusRollsNumber = 2;
        FrameType frameType = frameTypeWithBonusRollsNumber(bonusRollsNumber);
        
        int[] standardFrameRolls = getBonusRolls(frameType, nextRolls);
        
        assertArrayEquals(
                subsetOfRolls(nextRolls, 0, bonusRollsNumber), 
                standardFrameRolls);
    }
    
    @Test
    public void next_rolls_for_frame_are_the_rolls_after_standard_rolls() {
        int[] rolls = createSomeRolls();
        int rollsNumber = 1;
        FrameType frameType = frameTypeWithRollsNumber(rollsNumber);
        
        int[] nextFrameRolls = getNextRolls(frameType, rolls);
        
        assertArrayEquals(
                subsetOfRolls(rolls, rollsNumber, rolls.length-rollsNumber), 
                nextFrameRolls);
    }
    
    //~~~~~~~~~~~
    protected int[] createSomeRolls() {
        int[] rolls = new int[] {1, 2, 3, 4};
        return rolls;
    }
    
    protected Frame createFrameWithScore(int score) {
        Frame frame1 = mock(Frame.class);
        when(frame1.getScore()).thenReturn(score);
        return frame1;
    }
    
    protected FrameType frameTypeWithRollsNumber(int numberOfStandardRolls) {
        FrameType frameType = mock(FrameType.class);
        when(frameType.getNumberOfFrameRolls()).thenReturn(numberOfStandardRolls);
        return frameType;
    }
    
    protected FrameType frameTypeWithBonusRollsNumber(int numberOfBonusRolls) {
        FrameType frameType = mock(FrameType.class);
        when(frameType.getNumberOfBonusRolls()).thenReturn(numberOfBonusRolls);
        return frameType;
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

    protected int[] subsetOfRolls(int[] rolls, int currentIndex, int numberOfRolls) {
        int[] standardFrameRolls = new int[numberOfRolls];
        for (int i = 0; i < numberOfRolls; i++) {
            standardFrameRolls[i] = rolls[currentIndex+i];
        }
        return standardFrameRolls;
    }


    protected int[] getStandardRolls(FrameType frameType, int[] rolls) {
        int currentIndex = 0;
        int numberOfRolls = frameType.getNumberOfFrameRolls();
        return subsetOfRolls(rolls, currentIndex, numberOfRolls);
    }

    protected int[] getNextRolls(FrameType frameType, int[] rolls) {
        int currentIndex = frameType.getNumberOfFrameRolls();
        int numberOfRolls = rolls.length - currentIndex;
        return subsetOfRolls(rolls, currentIndex, numberOfRolls);
    }

    protected int[] getBonusRolls(FrameType frameType, int[] nextRolls) {
        int currentIndex = 0;
        int numberOfRolls = frameType.getNumberOfBonusRolls();
        return subsetOfRolls(nextRolls, currentIndex, numberOfRolls);
    }

}