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
public class KataTest {
    public static final int PLAIN_ROLL = 1;
    public static final int ROLL_NOT_TEN = 9;
    public static final int TEN = 10;
    public static final int ROLL_ZERO = 0;
    public static final int SOME_VALUE = 1;

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

    @Test
    public void score_of_a_frame_equals_sum_of_frame_rolls_plus_the_value_of_bonus_rolls() {
        int sumOfFrameRolls = PLAIN_ROLL;
        int valueOfBonusRolls = SOME_VALUE;
        Frame frame = mock(Frame.class);
        when(frame.getSumOfRolls()).thenReturn(sumOfFrameRolls);
        when(frame.getValueOfBonusRolls()).thenReturn(valueOfBonusRolls);
        when(frame.getScore()).thenCallRealMethod();
        
        int scoreOfFrame = frame.getScore();

        assertThat(scoreOfFrame, is(sumOfFrameRolls + valueOfBonusRolls));
    }

    @Test
    public void bonus_score_equals_sum_of_bonus_rolls() {
        int numberOfBonusRolls = 2;
        int[] nextRolls = new int[]{
            SOME_VALUE, SOME_VALUE
        };

        int bonusScoreForSpare = computeSumOfRolls(
                numberOfBonusRolls, nextRolls);

        assertThat(bonusScoreForSpare, is(TestUtils.sumValues(nextRolls)));
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

    @Test
    public void sum_of_rolls_equals_sum_of_the_frames_rolls() {
        int numberOfFrameRolls = 2;
        int valueOfFirstRoll = ROLL_ZERO;
        int valueOfSecondRoll = ROLL_NOT_TEN;
        int[] rolls = new int[]{valueOfFirstRoll, valueOfSecondRoll};

        int sumOfRolls = computeSumOfRolls(numberOfFrameRolls,
                rolls);

        assertThat(sumOfRolls, is(valueOfFirstRoll + valueOfSecondRoll));
    }

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

    //~~~~~~~~~~~
    public int computeScoreOfGame(Frame[] frames) {
        int scoreOfGame = 0;
        for (Frame frame : frames) {
            scoreOfGame += frame.getScore();
        }
        return scoreOfGame;
    }

    protected int computeSumOfRolls(int numberOfRolls,
            int[] rolls) {
        int sumOfRolls = 0;
        for (int i = 0; i < numberOfRolls; i++) {
            sumOfRolls += rolls[i];
        }

        return sumOfRolls;
    }

    protected int computeScoreOfFrame(Frame frame) {
        return frame.getSumOfRolls() + frame.getValueOfBonusRolls();
    }

    protected Frame createFrameWithScore(int score) {
        Frame frame1 = mock(Frame.class);
        when(frame1.getScore()).thenReturn(score);
        return frame1;
    }
    
    static class Frame {
        
        public int getSumOfRolls() {
            return 0;
        }
        
        public int getValueOfBonusRolls() {
            return 0;
        }
        
        public int getScore() {
            return getSumOfRolls() + getValueOfBonusRolls();
        }
    }

    static class FrameType {

        private int numberOfFrameRolls;
        private int numberOfBonusRolls;

        public FrameType(int numberOfFrameRolls, int numberOfBonusRolls) {
            this.numberOfFrameRolls = numberOfFrameRolls;
            this.numberOfBonusRolls = numberOfBonusRolls;
        }

        public int getNumberOfFrameRolls() {
            return numberOfFrameRolls;
        }

        public int getNumberOfBonusRolls() {
            return numberOfBonusRolls;
        }
    }
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