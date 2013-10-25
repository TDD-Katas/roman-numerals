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
public class MainTest {
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
    
    //~~~~~~~~~~~
    
    protected Frame createFrameWithScore(int score) {
        Frame frame1 = mock(Frame.class);
        when(frame1.getScore()).thenReturn(score);
        return frame1;
    }
    
    //~~~~~~~~~~~
    public static void main(String[] args) {
        int[] rollValues = new int[]{5, 5, 10, 1, 1};
        
        int gameScore = 0;
        Rolls currentRolls = new Rolls(rollValues);
        
        while (currentRolls.hasNext()) {       
            FrameType frameType = currentRolls.computeFrameType();
            Rolls activeSubset = currentRolls.getActiveSubsetAccordingTo(frameType);
            Rolls nextSubset = currentRolls.getRemainingSubsetAccordingTo(frameType);
            Rolls bonusSubset = nextSubset.getBonusSubsetAccordingTo(frameType);
            gameScore += activeSubset.getScore() + bonusSubset.getScore();
            currentRolls = nextSubset;
        }
        
        System.out.println("gameScore = "+gameScore);
    }
    
    
    public int computeScoreOfGame(Frame[] frames) {
        int scoreOfGame = 0;
        for (Frame frame : frames) {
            scoreOfGame += frame.getScore();
        }
        return scoreOfGame;
    }
}