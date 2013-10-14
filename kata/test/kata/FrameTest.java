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
    private Frame frame;
    
    
    @Before
    public void setUp() {
        frame = mock(Frame.class);
    }
    
    @Test
    public void score_of_a_frame_equals_plain_score_plus_bonus_score() {
        when(frame.getStandardScore()).thenReturn(SOME_VALUE);
        when(frame.getBonusScore()).thenReturn(SOME_VALUE);
        when(frame.getScore()).thenCallRealMethod();
        
        int scoreOfFrame = frame.getScore();

        assertThat(scoreOfFrame, is(frame.getStandardScore() + frame.getBonusScore()));
    }

    @Test
    public void bonus_score_of_frame_equals_sum_of_bonus_rolls() {
        int[] bonusRolls = createSomeRolls();
        when(frame.getBonusRolls()).thenReturn(bonusRolls);
        when(frame.getBonusScore()).thenCallRealMethod();

        int bonusScoreForSpare = frame.getBonusScore();

        assertThat(bonusScoreForSpare, is(TestUtils.sumOfValues(bonusRolls)));
    }



    @Test
    public void plain_score_of_frame_equals_sum_of_standard_rolls() {
        int[] standardRolls = createSomeRolls();
        when(frame.getStandardRolls()).thenReturn(standardRolls);
        when(frame.getStandardScore()).thenCallRealMethod();
        
        int sumOfRolls = frame.getStandardScore();

        assertThat(sumOfRolls, is(TestUtils.sumOfValues(standardRolls)));
    }
    
    protected int[] createSomeRolls() {
        int[] standardRolls = new int[]{
            SOME_VALUE, SOME_VALUE
        };
        return standardRolls;
    }
}
