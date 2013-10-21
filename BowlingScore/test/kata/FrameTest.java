/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameTest {
    private static final int SOME_VALUE = 1;
    
    @Test
    public void score_of_a_frame_is_score_of_plain_rolls_plus_score_of_bonus_rolls() {
        Rolls plainRolls = createMockRolls();
        Rolls bonusRolls = createMockRolls();
        Frame frame = new Frame(plainRolls, bonusRolls);
        
        int scoreOfFrame = frame.getScore();

        assertThat(scoreOfFrame, is(plainRolls.getScore() + bonusRolls.getScore()));
    }

    protected Rolls createMockRolls() {
        Rolls plainRolls = mock(Rolls.class);
        when(plainRolls.getScore()).thenReturn(SOME_VALUE);
        return plainRolls;
    }
}
