/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

import org.hamcrest.Matcher;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static ro.ghionoiu.bowlingscore.frame.FrameType.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameTypeTest {
    @Test
    public void number_of_bonus_rolls_for_open_frame_is_0() {
        assertNumberOfBonusRollsFor(OPEN, is(0));
    }
    
    @Test
    public void number_of_bonus_rolls_for_spare_is_1() {
        assertNumberOfBonusRollsFor(SPARE, is(1));
    }
    
    @Test
    public void number_of_bonus_rolls_for_strike_is_2() {
        assertNumberOfBonusRollsFor(STRIKE, is(2));
    }

    //~~~~~~~~ Test helpers
    
    protected void assertNumberOfBonusRollsFor(FrameType frameType, Matcher<Integer> expected) {
        int numberOfBonusRolls = frameType.getNumberOfBonusRolls();
        
        assertThat(numberOfBonusRolls, expected);
    }
}