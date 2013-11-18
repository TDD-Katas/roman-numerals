/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

import ro.ghionoiu.bowlingscore.frame.Frame;
import ro.ghionoiu.bowlingscore.frame.FrameIdentifier;
import ro.ghionoiu.bowlingscore.rolls.Rolls;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameIdentifierTest {
    private static final int NORMAL_ROLL = 1;
    private static final int TEN = 10;
    private FrameIdentifier instance;
    
    @Before
    public void setUp() {
        instance = new FrameIdentifier();
    }
    
   @Test
    public void first_frame_starts_at_index_0() {
        
        Frame firstFrame = instance.getFrame(0, someRolls());
        
        assertThat(firstFrame.getStartingIndex(), is(0));
    }
    
    @Test
    public void first_frame_has_a_length_of_1_if_its_first_roll_is_10() {
        Rolls rolls = firstRollIsTen();
        
        Frame firstFrame = instance.getFrame(0, rolls);
        
        assertThat(firstFrame.getLength(), is(1));
    }
        
    @Test
    public void first_frame_has_a_length_of_2_if_its_first_roll_is_10() {
        Rolls rolls = firstRollNotTen();
        
        Frame firstFrame = instance.getFrame(0, rolls);
        
        assertThat(firstFrame.getLength(), is(2));
    }
        
    @Test
    public void frame_ends_at_starting_index_plus_length() {
        int startingIndex = 1;
        int length = 2;
        
        Frame frame = new Frame(someRolls(), startingIndex, length);
        
        assertThat(frame.getEndingIndex(), is(startingIndex+length));
    }
    
    @Test
    public void the_Nth_frame_starts_at_previous_frame_ending_index() {
        Rolls rolls = someRolls();
        int currentFrameNumber = 1;
        
        Frame currentFrame = instance.getFrame(currentFrameNumber, rolls);
        
        Frame previousFrame = instance.getFrame(currentFrameNumber-1, rolls);
        assertThat(currentFrame.getStartingIndex(), is(previousFrame.getEndingIndex()));
    }
    
    //~~~~~~~~~~~~~~ Unit Test helpers ~~~~~~~~

    private Rolls someRolls() {
        return Rolls.create(NORMAL_ROLL, NORMAL_ROLL, NORMAL_ROLL, NORMAL_ROLL);
    }
    
    private Rolls firstRollIsTen() {
        return Rolls.create(TEN, NORMAL_ROLL);
    }
    
    private Rolls firstRollNotTen() {
        return Rolls.create(NORMAL_ROLL, NORMAL_ROLL);
    }
}