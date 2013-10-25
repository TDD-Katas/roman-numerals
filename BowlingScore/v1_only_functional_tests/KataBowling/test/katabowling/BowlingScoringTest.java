package katabowling;

import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BowlingScoringTest {

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
    
    private Rolls rolls(int... rolls) {
        return Rolls.create(rolls);
    }
}