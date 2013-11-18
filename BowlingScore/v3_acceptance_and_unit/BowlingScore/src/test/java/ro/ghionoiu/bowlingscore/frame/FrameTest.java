/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

import ro.ghionoiu.bowlingscore.rolls.Rolls;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameTest {
    
    @Test
    public void score_is_normal_score_plus_bonus_score() {
        Rolls rolls = Rolls.create(10, 2, 3);
        Frame frame = new Frame(rolls, 0, 1);
        
        int frameScore = frame.getScore();
        
        assertThat(frameScore, is(frame.getNormalScore()+frame.getBonusScore()));
    }
    
    @Test
    public void normal_score_is_sum_of_rolls_between_starting_and_ending_index() {
        Rolls rolls = Rolls.create(3, 2, 1, 0);
        Frame frame = new Frame(rolls, 0, 2);
        int expectedScore = 5;
        
        int normalScore = frame.getNormalScore();
        
        assertThat(normalScore, is(expectedScore));
    }
    
    @Test
    public void bonus_score_is_value_of_the_bonus_rolls() {
        Rolls rolls = Rolls.create(0, 1, 2, 3);
        Frame frame = new Frame(rolls, 0, 2, typeWithTwoBonusRolls());
        
        int bonusScore = frame.getBonusScore();
        
        assertThat(bonusScore, is(rolls.getValueAt(2) + rolls.getValueAt(3)));
    }
    
    //~~~~~~~~~~~~~~~~~~~~~~  Test helpers
    
    private FrameTypeDetector typeWithTwoBonusRolls() {
        FrameTypeDetector frameTypeDetector = mock(FrameTypeDetector.class);
        when(frameTypeDetector.getType(any(Frame.class))).thenReturn(FrameType.STRIKE);
        return frameTypeDetector;
    }
}