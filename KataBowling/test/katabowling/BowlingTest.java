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
        int gameFrames = 1;
        assertGameScoreEquals(0, rolls(0, 0), gameFrames);
        assertGameScoreEquals(1, rolls(1, 0), gameFrames);
        assertGameScoreEquals(2, rolls(2, 0), gameFrames);
        assertGameScoreEquals(1, rolls(0, 1), gameFrames);
        //Spare
        assertGameScoreEquals(11, rolls(1, 9, 1), gameFrames);
        //Strike
        assertGameScoreEquals(12, rolls(10, 1, 1), gameFrames);
    }

    @Test
    public void testTwoFrameGame() {
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
    public void testThreeFrameGame() {
        int gameFrames = 3;
        assertGameScoreEquals(0, rolls(0, 0, 0, 0, 0, 0), gameFrames);
        assertGameScoreEquals(13, rolls(0, 0, 1, 9, 1, 1), gameFrames);
        assertGameScoreEquals(63, rolls(10, 10, 10, 1, 1), gameFrames);
    }
    
    @Test
    public void testPerfectGame() {
        int gameFrames = 10;
        int gameRolls = 12;
        
        int[] rollsArray = new int[gameRolls];
        for (int i = 0; i < rollsArray.length; i++) {
            rollsArray[i] = 10;
        }
        assertGameScoreEquals(300, Rolls.create(rollsArray), gameFrames);
    }
    
    @Test
    public void testNineGame() {
        int gameFrames = 10;
        int gameRolls = 20;
        
        int[] rollsArray = new int[gameRolls];
        for (int i = 0; i < gameRolls/2; i++) {
            rollsArray[2*i] = 9;
            rollsArray[2*i+1] = 0;
        }
        assertGameScoreEquals(90, Rolls.create(rollsArray), gameFrames);
    }
    
    @Test
    public void testFiveGame() {
        int gameFrames = 10;
        int gameRolls = 21;
        
        int[] rollsArray = new int[gameRolls];
        for (int i = 0; i < rollsArray.length; i++) {
            rollsArray[i] = 5;
        }
        assertGameScoreEquals(150, Rolls.create(rollsArray), gameFrames);
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
    
    private void assertGameScoreEquals(int expectedScore, Rolls rolls, int gameFrames) {
        int score = computeScore(gameFrames, rolls);
        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertGetFrameTypeForRollsEquals(FrameType frameType, Rolls rolls) {
        FrameType result = getFrameTypeForRolls(rolls);

        assertThat(result, equalTo(frameType));
    }
    
    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    static class Rolls {
        private final int[] rollsArray;

        public Rolls(int ... rolls) {
            this.rollsArray = rolls;
        }
        
        public static Rolls create(int ... rolls) {
            return new Rolls(rolls);
        }
        
        public int getSum() {
            return getSum(0, rollsArray.length);
        }
        
        public int getSum(int start, int end) {
            int score = 0;
            for (int i = start; i < end; i++) {
                score += rollsArray[i];
            }
            return score;
        }
        
        public FrameType getFrameType() {
            FrameType frameType;
            if (rollsArray[0] == 10) {
                frameType = FrameType.STRIKE;
            } else if (rollsArray[0] + rollsArray[1] == 10) {
                frameType = FrameType.SPARE;
            } else {
                frameType = FrameType.OPEN;
            }

            return frameType;
        }
        
        private Rolls getNextFrames(int frameSize) {
            int[] newArray = Arrays.copyOfRange(rollsArray, frameSize, rollsArray.length);
            return Rolls.create(newArray);
        }
    }
    
    private FrameType getFrameTypeForRolls(Rolls rolls) {
        return rolls.getFrameType();
    }
    
    private Rolls rolls(int... rolls) {
        return Rolls.create(rolls);
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
    
    private int computeScore(int framesLeft, Rolls rolls) {
        if (framesLeft == 1) {
            return getFinalFrameScore(rolls);
        } else {
            FrameType frameType = getFrameTypeForRolls(rolls);
            int frameSize = frameType.getFrameSize();
            int scoreForCurrentFrame = getScoreForCurrentFrame(frameSize, rolls);
            
            int numberOfBonusRolls = frameType.getBonusRolls();
            int bonusRollsScore = computeBonusRollsScore(numberOfBonusRolls, rolls);
            
            Rolls nextRolls = rolls.getNextFrames(frameSize);
            return scoreForCurrentFrame + bonusRollsScore + computeScore(framesLeft-1, nextRolls);
        }
    }

    private int[] getNextFrames(int[] rolls, int frameSize) {
        return Arrays.copyOfRange(rolls, frameSize, rolls.length);
    }

    private int getScoreForCurrentFrame(int frameSize, Rolls rolls) {
        int start = 0;
        int end = frameSize;
        return rolls.getSum(start, end);
    }

    private int computeBonusRollsScore(int numberOfBonusRolls, Rolls rolls) {
        int start = 3 - numberOfBonusRolls;
        int end = 3;
        return rolls.getSum(start, end);
    }

    private int getFinalFrameScore(Rolls rolls) {
        return rolls.getSum();
    }
}