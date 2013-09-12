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
        BowlingScoring bowlingScoring = new BowlingScoring(gameFrames);
        int score = bowlingScoring.score(rolls);
        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertGetFrameTypeForRollsEquals(FrameType frameType, Rolls rolls) {
        FrameType result = rolls.getFrameType();

        assertThat(result, equalTo(frameType));
    }
    
    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    static class Rolls {
        private final int[] rollsArray;
        private final FrameType frameType;

        //~~~~ Construction area
        
        public Rolls(int ... rolls) {
            this.rollsArray = rolls;
            this.frameType = getFrameType();
        }
        
        public static Rolls create(int ... rolls) {
            return new Rolls(rolls);
        }
        
        private FrameType getFrameType() {
            FrameType localFrameType;
            if (rollsArray[0] == 10) {
                localFrameType = FrameType.STRIKE;
            } else if (rollsArray[0] + rollsArray[1] == 10) {
                localFrameType = FrameType.SPARE;
            } else {
                localFrameType = FrameType.OPEN;
            }
            return localFrameType;
        }
        
        public Rolls getNextFramesRolls() {
            int frameSize = frameType.getFrameSize();
            int[] newArray = Arrays.copyOfRange(rollsArray, frameSize, rollsArray.length);
            return Rolls.create(newArray);
        }
        
        //~~~~ Public methods
       
        public int computeTotalScoreForFrame() {
            return getSumForCurrentFrame() + computeBonusRollsScore();
        }
        
        public int getSumForAllRolls() {
            return getSum(0, rollsArray.length);
        }
        
        //~~~~ COmputing methods
        
        private int getSumForCurrentFrame() {
            int start = 0;
            int end = frameType.getFrameSize();
            return getSum(start, end);
        }
        
        private int computeBonusRollsScore() {
            int numberOfBonusRolls = frameType.getBonusRolls();
            
            int start = 3 - numberOfBonusRolls;
            int end = 3;
            return getSum(start, end);
        }
        
        //~~~ Utils
        
        private int getSum(int start, int end) {
            int score = 0;
            for (int i = start; i < end; i++) {
                score += rollsArray[i];
            }
            return score;
        }
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
    
    static class BowlingScoring {
        private final int gameFrames;

        public BowlingScoring(int gameFrames) {
            this.gameFrames = gameFrames;
        }
        
        public int score(Rolls rolls) {
            return computeScore(gameFrames, rolls);
        }
        
        private int computeScore(int framesLeft, Rolls rolls) {
            int score;
            if (framesLeft == 1) {
                score = computeFinalFrameScore(rolls);
            } else {
                score = computeRegularFrameScore(rolls, framesLeft);
            }
            return score;
        }
        
        private int computeFinalFrameScore(Rolls rolls) {
            return rolls.getSumForAllRolls();
        }

        private int computeRegularFrameScore(Rolls rolls, int framesLeft) {
            int score;
            int totalScoreForCurrentFrame = rolls.computeTotalScoreForFrame();
            Rolls nextRolls = rolls.getNextFramesRolls();
            int scoreForOtherFrames = computeScore(framesLeft-1, nextRolls);
            score = totalScoreForCurrentFrame + scoreForOtherFrames;
            return score;
        }
    }
}