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
        assertTwoFrameGameScoreEquals(0, rolls(0, 0, 0, 0));
        assertTwoFrameGameScoreEquals(1, rolls(0, 0, 1, 0));
        assertTwoFrameGameScoreEquals(4, rolls(1, 1, 1, 1));
    }
    
    @Test
    public void testTwoFrameGameSpare1() {
        int[] rolls = rolls(1, 9, 1, 1);
        int expectedScore = 13;
        
        int score = computeScoreForSpareInFirstFrame(rolls);

        assertThat(score, equalTo(expectedScore));
    }
    
    @Test
    public void testTwoFrameGameSpare2() {
        int[] rolls = rolls(1, 9, 2, 1);
        int expectedScore = 15;
        
        int score = computeScoreForSpareInFirstFrame(rolls);

        assertThat(score, equalTo(expectedScore));
    }
    
    @Test
    public void testTwoFrameGameSpare3() {
        int[] rolls = rolls(1, 9, 3, 2);
        int expectedScore = 18;
        
        int score = computeScoreForSpareInFirstFrame(rolls);

        assertThat(score, equalTo(expectedScore));
    }
    
    //~~~~~~~~~~~~~~~~ Test helpers ~~~~~~~~~~~
    
    private int[] rolls(int ... rolls) {
        return rolls;
    }
    
    private void assertOneFrameGameScoreEquals(int expectedScore, int[] rolls) {
        int score = computeScoreWhenOpenFrameFirst(rolls);

        assertThat(score, equalTo(expectedScore));
    }
    
    private void assertTwoFrameGameScoreEquals(int expectedScore, int[] rolls) {
        int score = computeScoreWhenOpenFrameFirst(rolls);

        assertThat(score, equalTo(expectedScore));
    }

    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private int computeScoreWhenOpenFrameFirst(int[] rolls) {
        int score = 0;
        for (int i : rolls) {
            score += i;
        }
        
        return score;
    }

    private int computeScoreForSpareInFirstFrame(int[] rolls) {
        return computeScoreWhenOpenFrameFirst(rolls) + rolls[2];
    }
}