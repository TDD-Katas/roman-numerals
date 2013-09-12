/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package katabowling;

import java.util.Arrays;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BowlingTest {

    @Test
    public void testOneFrameGame() {
        ScoreStrategy scoreStrategy = new OneFrameGameScoreStrategy();
        assertGameScoreEquals(0, rolls(0, 0), scoreStrategy);
        assertGameScoreEquals(1, rolls(1, 0), scoreStrategy);
        assertGameScoreEquals(2, rolls(2, 0), scoreStrategy);
        assertGameScoreEquals(1, rolls(0, 1), scoreStrategy);
        //Spare
        assertGameScoreEquals(11, rolls(1, 9, 1), scoreStrategy);
        //Strike
        assertGameScoreEquals(12, rolls(10, 1, 1), scoreStrategy);
    }

    @Test
    public void testTwoFrameGameOpenFrame() {
        ScoreStrategy scoreStrategy;
        scoreStrategy = new TwoFrameGameScoreStrategy();
        assertGameScoreEquals(0, rolls(0, 0, 0, 0), scoreStrategy);
        assertGameScoreEquals(1, rolls(0, 0, 1, 0), scoreStrategy);
        assertGameScoreEquals(4, rolls(1, 1, 1, 1), scoreStrategy);
        //Spare
        assertGameScoreEquals(13, rolls(1, 9, 1, 1), scoreStrategy);
        assertGameScoreEquals(15, rolls(1, 9, 2, 1), scoreStrategy);
        assertGameScoreEquals(18, rolls(1, 9, 3, 2), scoreStrategy);
        //Strike
        assertGameScoreEquals(16, rolls(10, 2, 1), scoreStrategy);
        assertGameScoreEquals(20, rolls(10, 3, 2), scoreStrategy);
        assertGameScoreEquals(24, rolls(10, 1, 6), scoreStrategy);
        assertGameScoreEquals(32, rolls(10, 1, 9, 2), scoreStrategy);
        assertGameScoreEquals(34, rolls(10, 10, 1, 2), scoreStrategy);
    }

    @Test
    public void testGetStrategyForRolls() {
        Class strategyClass;
        strategyClass = OpenFrameScoreStrategy.class;
        assertGetStrategyForRollsEquals(strategyClass, rolls(0, 0));
        assertGetStrategyForRollsEquals(strategyClass, rolls(1, 1));
        strategyClass = SpareScoreStrategy.class;
        assertGetStrategyForRollsEquals(strategyClass, rolls(1, 9));
        strategyClass = StrikeScoreStrategy.class;
        assertGetStrategyForRollsEquals(strategyClass, rolls(10));
        assertGetStrategyForRollsEquals(strategyClass, rolls(10));
    }

    private void assertGameScoreEquals(int expectedScore, int[] rolls, ScoreStrategy scoreStrategy) {
        int score = scoreStrategy.computeScore(rolls);
        assertThat(score, equalTo(expectedScore));
    }

    private void assertGetStrategyForRollsEquals(Class expectedStrategy, int[] rolls) {
        ScoreStrategy result = getStrategyForRolls(rolls);

        assertTrue("Score strategy is not of required type, "
                + "expected = " + expectedStrategy.getSimpleName()
                + ", found = " + result.getClass().getSimpleName(), expectedStrategy.isInstance(result));
    }

    private void assertGetFrameTypeForRollsEquals(FrameType frameType, int[] rolls) {
        FrameType result = getFrameTypeForRolls(rolls);

        assertThat(result, equalTo(frameType));
    }
    
    
    
    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private FrameType getFrameTypeForRolls(int[] rolls) {
        if (rolls[0] == 10) {
            return FrameType.STRIKE;
        } else if (rolls[0] + rolls[1] == 10) {
            return FrameType.SPARE;
        }

        return FrameType.OPEN;
    }
    
    private ScoreStrategy getStrategyForRolls(int[] rolls) {
        if (rolls[0] == 10) {
            return new StrikeScoreStrategy();
        } else if (rolls[0] + rolls[1] == 10) {
            return new SpareScoreStrategy();
        }

        return new OpenFrameScoreStrategy();
    }

    interface ScoreStrategy {

        int computeScore(int[] rolls);
    }

    class OneFrameGameScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return getFinalFrameScore(rolls);
        }
    }

    class TwoFrameGameScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            ScoreStrategy scoreStrategy = getStrategyForRolls(rolls);
            return scoreStrategy.computeScore(rolls);
        }
    }

    class OpenFrameScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return computeScoreWithOpenFrameFirst(rolls);
        }
    }

    class SpareScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return computeScoreWithSpareFirst(rolls);
        }
    }

    class StrikeScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return computeScoreWithStrikeFirst(rolls);
        }
    }

    private int[] rolls(int... rolls) {
        return rolls;
    }

    enum FrameType {

        OPEN(2, 0),
        SPARE(2, 1),
        STRIKE(1, 2);
        //Members
        int frameSize;
        int bonusRolls;

        private FrameType(int frameSize, int bonusRolls) {
            this.frameSize = frameSize;
            this.bonusRolls = bonusRolls;
        }

        int getFrameSize() {
            return frameSize;
        }

        int getBonusRolls() {
            return bonusRolls;
        }
    }

    private int computeScoreWithOpenFrameFirst(int[] rolls) {
        FrameType frameType = FrameType.OPEN;
        return computeScoreForRolls(frameType, rolls);
    }

    private int computeScoreWithSpareFirst(int[] rolls) {
        FrameType frameType = FrameType.SPARE;
        return computeScoreForRolls(frameType, rolls);
    }

    private int computeScoreWithStrikeFirst(int[] rolls) {
        FrameType frameType = FrameType.STRIKE;
        return computeScoreForRolls(frameType, rolls);
    }

    //~~~~~~~~~~ Score methods ~~~~~~~
    private int computeScoreForRolls(FrameType frameType, int[] rolls) {
        int frameSize = frameType.getFrameSize();
        int numberOfBonusRolls = frameType.getBonusRolls();
        int scoreForCurrentFrame = getScoreForCurrentFrame(frameSize, rolls);
        int[] finalFrame = getNextFrames(rolls, frameSize);
        int bonusRollsScore = computeBonusRollsScore(numberOfBonusRolls, rolls);
        return scoreForCurrentFrame + bonusRollsScore + getFinalFrameScore(finalFrame);
    }

    private int[] getNextFrames(int[] rolls, int frameSize) {
        return Arrays.copyOfRange(rolls, frameSize, rolls.length);
    }

    private int getScoreForCurrentFrame(int frameSize, int[] rolls) {
        int start = 0;
        int end = frameSize;
        return getSumOfRolls(rolls, start, end);
    }

    private int computeBonusRollsScore(int numberOfBonusRolls, int[] rolls) {
        int start = 3 - numberOfBonusRolls;
        int end = 3;
        return getSumOfRolls(rolls, start, end);
    }

    private int getFinalFrameScore(int[] rolls) {
        int start = 0;
        int end = rolls.length;
        return getSumOfRolls(rolls, start, end);
    }

    private int getSumOfRolls(int[] rolls, int start, int end) {
        int score = 0;
        for (int i = start; i < end; i++) {
            score += rolls[i];
        }
        return score;
    }
}