/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class RollsTest {
    private static final int SOME_VALUE = 1;
    
    @Test
    public void score_of_rolls_equals_sum_of_values() {
        Rolls rolls = rollValues(SOME_VALUE , SOME_VALUE);
        
        final int scoreOfRolls = rolls.getScore();
        
        assertThat(scoreOfRolls, is(2*SOME_VALUE));
    }
    
    @Test
    public void rolls_equal_if_the_value_arrays_equal() {
        Rolls firstRoll = rollValues(SOME_VALUE, SOME_VALUE);
        Rolls otherRoll = rollValues(SOME_VALUE, SOME_VALUE);
        
        boolean rollsAreEqual = firstRoll.equals(otherRoll);
        
        assertThat(rollsAreEqual, is(true));
    }
    
    @Test
    public void the_standard_rolls_is_the_subset_from_current_rolls_according_to_frametype() {
        Rolls rolls = rollValues(0, 1, 2, 3);
        int rollsNumber = 2;
        FrameType frameType = frameTypeWithRollsNumber(rollsNumber);
        
        Rolls standardFrameRolls = rolls.getActiveSubsetAccordingTo(frameType);
        
        assertThat(standardFrameRolls, equalTo(rollValues(0, 1)));
    }
    
    @Test
    public void next_rolls_for_frame_are_the_rolls_after_current_frames_rolls() {
        Rolls rolls = rollValues(0, 1, 2, 3);
        int rollsNumber = 2;
        FrameType frameType = frameTypeWithRollsNumber(rollsNumber);
        
        Rolls nextFrameRolls = rolls.getRemainingSubsetAccordingTo(frameType);
        
        assertThat(nextFrameRolls, equalTo(rollValues(2, 3)));
    }
    
     @Test
    public void the_bonus_rolls_is_the_subset_from_next_rolls_according_to_frametype() {
        Rolls nextRolls = rollValues(0, 1, 2, 3);
        int bonusRollsNumber = 2;
        FrameType frameType = frameTypeWithBonusRollsNumber(bonusRollsNumber);
        
        Rolls bonusRolls = nextRolls.getBonusSubsetAccordingTo(frameType);
        
        assertThat(bonusRolls, equalTo(rollValues(0, 1)));
    }   
     
    //~~~~~~~~~~~
    protected Rolls createSomeRolls() {
        int[] values = new int[] {1, 2, 3, 4};
        return new Rolls(values);
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
    
    public Rolls rollValues(int ... value) {
        return new Rolls(value);
    }
}
