/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

import ro.ghionoiu.bowlingscore.rolls.Rolls;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameTypeDetectorTest {
    private FrameTypeDetector instance = new FrameTypeDetector();
    
    @Before
    public void setUp() {
        instance = new FrameTypeDetector();
    }
    
    @Test
    public void frame_is_open_if_normal_score_not_10() {
        Rolls rolls = Rolls.create(3, 2, 1, 0);
        Frame frame = new Frame(rolls, 0, 2);
        
        FrameType frameType = instance.getType(frame);
        
        assertThat(frameType, is(FrameType.OPEN));
    }
    
    @Test
    public void frame_is_spare_if_normal_score_is_10_and_length_is_2() {
        Rolls rolls = Rolls.create(5, 5, 1, 0);
        Frame frame = new Frame(rolls, 0, 2);
        
        FrameType frameType = instance.getType(frame);
        
        assertThat(frameType, is(FrameType.SPARE));
    }
    
    @Test
    public void frame_is_strike_if_normal_score_is_10_and_length_is_1() {
        Rolls rolls = Rolls.create(10, 1, 0);
        Frame frame = new Frame(rolls, 0, 1);
        
        FrameType frameType = instance.getType(frame);
        
        assertThat(frameType, is(FrameType.STRIKE));
    }
}