/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore;

import ro.ghionoiu.bowlingscore.frame.Frame;
import ro.ghionoiu.bowlingscore.frame.FrameIdentifier;
import ro.ghionoiu.bowlingscore.rolls.Rolls;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BowlingGameTest {
    
    
    @Test
    public void game_score_equals_sum_of_frames_score() {
        int[] frameScores = { 1, 10 };
        BowlingGame game = gameWithGivenFrames(frameScores);
        
        int gameScore = game.computeScore(anyRolls());
        
        assertThat(gameScore, is(11));
    }
    
    //~~~~~~~~~ Test helpers
    
    protected Rolls anyRolls() {
        return mock(Rolls.class);
    }
    
    protected FrameIdentifier createMockedFrames(int[] frameScores) {
        FrameIdentifier frameExtractor = mock(FrameIdentifier.class);
        for (int i = 0; i < frameScores.length; i++) {
            int frameScore = frameScores[i];
            Frame frame = mock(Frame.class);
            when(frame.getScore()).thenReturn(frameScore);
            when(frameExtractor.getFrame(eq(i), any(Rolls.class)))
                    .thenReturn(frame);
        }
        return frameExtractor;
    }
    
    protected BowlingGame gameWithGivenFrames(int[] frameScores) {
        FrameIdentifier frameExtractor = createMockedFrames(frameScores);
        BowlingGame game = new BowlingGame(frameScores.length, frameExtractor);
        return game;
    }
}