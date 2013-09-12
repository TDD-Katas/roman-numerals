/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package katabowling;

import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BowlingTest {

    @Test
    public void testOneFrameGameOpenFrame() {
        assertOneFrameGameScoreEquals(0, rolls(0, 0));
        assertOneFrameGameScoreEquals(1, rolls(1, 0));
        assertOneFrameGameScoreEquals(2, rolls(2, 0));
        assertOneFrameGameScoreEquals(1, rolls(0, 1));
    }
    
    @Test
    public void testOneFrameGameSpare() {
        assertOneFrameGameScoreEquals(11, rolls(1, 9, 1));
    }
    
    @Test
    public void testOneFrameGameStrike() {
        assertOneFrameGameScoreEquals(12, rolls(10, 1, 1));
    }
    
    @Test
    public void testTwoFrameGameOpenFrame() {
        String firstFrameType = "open";
        assertTwoFrameGameFirstFrameOpenScoreEquals(0, rolls(0, 0, 0, 0), firstFrameType);
        assertTwoFrameGameFirstFrameOpenScoreEquals(1, rolls(0, 0, 1, 0), firstFrameType);
        assertTwoFrameGameFirstFrameOpenScoreEquals(4, rolls(1, 1, 1, 1), firstFrameType);
    }
    
    @Test
    public void testTwoFrameGameSpareFirst() {
        String firstFrameType = "spare";
        assertTwoFrameGameFirstFrameSpareScoreEquals(13, rolls(1, 9, 1, 1), firstFrameType);
        assertTwoFrameGameFirstFrameSpareScoreEquals(15, rolls(1, 9, 2, 1), firstFrameType);
        assertTwoFrameGameFirstFrameSpareScoreEquals(18, rolls(1, 9, 3, 2), firstFrameType);
    }
    
    @Test
    public void testTwoFrameGameStrikeFirst() {
        String firstFrameType = "strike";
        assertTwoFrameGameFirstFrameStrikeScoreEquals(16, rolls(10, 2, 1), firstFrameType);
        assertTwoFrameGameFirstFrameStrikeScoreEquals(20, rolls(10, 3, 2), firstFrameType);
        assertTwoFrameGameFirstFrameStrikeScoreEquals(24, rolls(10, 1, 6), firstFrameType);
        assertTwoFrameGameFirstFrameStrikeScoreEquals(32, rolls(10, 1, 9, 2), firstFrameType);
        assertTwoFrameGameFirstFrameStrikeScoreEquals(34, rolls(10, 10, 1, 2), firstFrameType);
    }
    
    
    //~~~~~~~~~~~~~~~~ Test helpers ~~~~~~~~~~~
    
    private int[] rolls(int ... rolls) {
        return rolls;
    }
    
    private void assertOneFrameGameScoreEquals(int expectedScore, int[] rolls) {
        int score = getSumOfRolls(rolls);

        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertTwoFrameGameFirstFrameOpenScoreEquals(int expectedScore, int[] rolls, String firstFrameType) {
        int score;
        if ("open".equals(firstFrameType)) {
            score = getSumOfRolls(rolls);
        } else {
            score = -1;
            fail("Invalid type");
        }

        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertTwoFrameGameFirstFrameSpareScoreEquals(int expectedScore, int[] rolls, String firstFrameType) {
        int score;
        if ("spare".equals(firstFrameType)) {
            score = computeScoreForSpareInFirstFrame(rolls);
        } else {
            score = -1;
            fail("Invalid type");
        }
        
        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertTwoFrameGameFirstFrameStrikeScoreEquals(int expectedScore, int[] rolls, String firstFrameType) {
        int score;
        if ("strike".equals(firstFrameType)) {
            score = computeScoreForStrikeInFirstFrame(rolls);
        } else {
            score = -1;
            fail("Invalid type");
        }
        
        
        assertThat(score, equalTo(expectedScore));
    }
    
    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private int getSumOfRolls(int[] rolls) {
        int score = 0;
        for (int i : rolls) {
            score += i;
        }
        return score;
    }

    private int computeScoreForSpareInFirstFrame(int[] rolls) {
        return getSumOfRolls(rolls) + rolls[2];
    }

    private int computeScoreForStrikeInFirstFrame(int[] rolls) {
        return getSumOfRolls(rolls) + rolls[1] + rolls[2];
    }
}