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
        int gameFrames = 2;
        assertGameScoreEquals(0, rolls(0, 0, 0, 0), gameFrames);
        assertGameScoreEquals(1, rolls(0, 0, 1, 0), gameFrames);
        assertGameScoreEquals(4, rolls(1, 1, 1, 1), gameFrames);
        //Spare
        assertGameScoreEquals(13, rolls(1, 9, 1, 1), gameFrames);
        assertGameScoreEquals(15, rolls(1, 9, 2, 1), gameFrames);
        assertGameScoreEquals(18, rolls(1, 9, 3, 2), gameFrames);
        //Strike
        assertGameScoreEquals(16, rolls(10, 2, 1), gameFrames);
        assertGameScoreEquals(20, rolls(10, 3, 2), gameFrames);
        assertGameScoreEquals(24, rolls(10, 1, 6), gameFrames);
        assertGameScoreEquals(32, rolls(10, 1, 9, 2), gameFrames);
        assertGameScoreEquals(34, rolls(10, 10, 1, 2), gameFrames);
    }
    
    @Test
    public void testThreeFrameGameOpenFrame() {
        int gameFrames = 3;
        assertGameScoreEquals(0, rolls(0, 0, 0, 0, 0, 0), gameFrames);
        assertGameScoreEquals(13, rolls(0, 0, 1, 9, 1, 1), gameFrames);
    }

    @Test
    public void testGetFrameTypeForRolls() {
        FrameType frameType;
        frameType = FrameType.OPEN;
        assertGetFrameTypeForRollsEquals(frameType, rolls(0, 0));
        assertGetFrameTypeForRollsEquals(frameType, rolls(1, 1));
        frameType = FrameType.SPARE;
        assertGetFrameTypeForRollsEquals(frameType, rolls(1, 9));
        frameType = FrameType.STRIKE;
        assertGetFrameTypeForRollsEquals(frameType, rolls(10));
        assertGetFrameTypeForRollsEquals(frameType, rolls(10));
    }
    
    private void assertGameScoreEquals(int expectedScore, int[] rolls, ScoreStrategy scoreStrategy) {
        int score = scoreStrategy.computeScore(rolls);
        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertGameScoreEquals(int expectedScore, int[] rolls, int gameFrames) {
        int score = computeScore(gameFrames, rolls);
        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertGetFrameTypeForRollsEquals(FrameType frameType, int[] rolls) {
        FrameType result = getFrameTypeForRolls(rolls);

        assertThat(result, equalTo(frameType));
    }
    
    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private FrameType getFrameTypeForRolls(int[] rolls) {
        FrameType frameType;
        if (rolls[0] == 10) {
            frameType = FrameType.STRIKE;
        } else if (rolls[0] + rolls[1] == 10) {
            frameType = FrameType.SPARE;
        } else {
            frameType = FrameType.OPEN;
        }

        return frameType;
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

    //~~~~~~~~~~ Score methods ~~~~~~~
    private int computeScoreForRolls(int[] rolls) {
        FrameType frameType = getFrameTypeForRolls(rolls);
        int frameSize = frameType.getFrameSize();
        int numberOfBonusRolls = frameType.getBonusRolls();
        int scoreForCurrentFrame = getScoreForCurrentFrame(frameSize, rolls);
        int[] finalFrame = getNextFrames(rolls, frameSize);
        int bonusRollsScore = computeBonusRollsScore(numberOfBonusRolls, rolls);
        return scoreForCurrentFrame + bonusRollsScore + getFinalFrameScore(finalFrame);
    }
    
    private int computeScore(int framesLeft, int[] rolls) {
        FrameType frameType = getFrameTypeForRolls(rolls);
        int frameSize = frameType.getFrameSize();
        int numberOfBonusRolls = frameType.getBonusRolls();
        int scoreForCurrentFrame = getScoreForCurrentFrame(frameSize, rolls);
        int[] nextFrame = getNextFrames(rolls, frameSize);
        int bonusRollsScore = computeBonusRollsScore(numberOfBonusRolls, rolls);
        
        if (framesLeft == 2) {
            return scoreForCurrentFrame + bonusRollsScore + getFinalFrameScore(nextFrame);
        } else {
            return scoreForCurrentFrame + bonusRollsScore + computeScore(framesLeft-1, nextFrame);
        }
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