/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameTypeTest {
    private static final int ROLL_NOT_TEN = 9;
    private static final int TEN = 10;
    private static final int ROLL_ZERO = 0;
    
    @Test
    public void number_of_rolls_for_open_frame_is_two() {
        FrameType frameType = OPEN_TYPE;

        int numberOfRolls = frameType.getNumberOfFrameRolls();

        assertThat(numberOfRolls, is(2));
    }

    @Test
    public void number_of_rolls_for_spare_is_two() {
        FrameType frameType = SPARE_TYPE;

        int numberOfRolls = frameType.getNumberOfFrameRolls();

        assertThat(numberOfRolls, is(2));
    }

    @Test
    public void number_of_rolls_for_strike_is_one() {
        FrameType frameType = STRIKE_TYPE;

        int numberOfRolls = frameType.getNumberOfFrameRolls();

        assertThat(numberOfRolls, is(1));
    }

    @Test
    public void number_of_bonus_rolls_for_strike_is_two() {
        FrameType frameType = STRIKE_TYPE;

        int bonusRolls = frameType.getNumberOfBonusRolls();

        assertThat(bonusRolls, is(2));
    }

    @Test
    public void number_of_bonus_rolls_for_spare_is_one() {
        FrameType frameType = SPARE_TYPE;

        int bonusRolls = frameType.getNumberOfBonusRolls();

        assertThat(bonusRolls, is(1));
    }

    @Test
    public void number_of_bonus_rolls_for_open_frame_is_zero() {
        FrameType frameType = OPEN_TYPE;

        int bonusRolls = frameType.getNumberOfBonusRolls();

        assertThat(bonusRolls, is(0));
    }
    
    @Test
    public void a_frame_is_strike_if_first_roll_is_ten() {
        int valueOfFirstRoll = TEN;

        FrameType frameType = getFrameType(valueOfFirstRoll, 0);

        assertThat(frameType, is(STRIKE_TYPE));
    }

    @Test
    public void a_frame_is_spare_if_first_roll_is_not_ten_and_sum_of_rolls_equals_ten() {
        int valueOfFirstRoll = ROLL_NOT_TEN;
        int valueOfSecondRoll = TEN - valueOfFirstRoll;

        FrameType frameType = getFrameType(valueOfFirstRoll, valueOfSecondRoll);

        assertThat(frameType, is(SPARE_TYPE));
    }

    @Test
    public void a_frame_is_open_frame_if_first_roll_is_not_ten_and_sum_of_rolls_different_than_ten() {
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;

        FrameType frameType = getFrameType(valueOfFirstRoll, valueOfSecondRoll);

        assertThat(frameType, is(OPEN_TYPE));
    }
    
    //~~~~~~~~~~~~
    
    private static final FrameType OPEN_TYPE = new FrameType(2, 0);
    private static final FrameType SPARE_TYPE = new FrameType(2, 1);
    private static final FrameType STRIKE_TYPE = new FrameType(1, 2);

    protected FrameType getFrameType(int valueOfFirstRoll, int valueOfSecondRoll) {
        FrameType frameType = OPEN_TYPE;
        if (valueOfFirstRoll == 10) {
            frameType = STRIKE_TYPE;
        } else if (valueOfFirstRoll + valueOfSecondRoll == 10) {
            frameType = SPARE_TYPE;
        }

        return frameType;
    }
}
