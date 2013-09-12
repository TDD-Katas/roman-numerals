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
public class OneFrameGameTest {

    @Test
    public void testOpenFrame() {
        assertScoreEquals(0, rolls(0, 0));
        assertScoreEquals(1, rolls(1, 0));
        assertScoreEquals(2, rolls(2, 0));
        assertScoreEquals(1, rolls(0, 1));
    }
    
    @Test
    public void testSpare() {
        assertScoreEquals(11, rolls(1, 9, 1));
    }
    
    @Test
    public void testStrike() {
        assertScoreEquals(12, rolls(10, 1, 1));
    }
    
    //~~~~~~~~~~~~~~~~ Test helpers ~~~~~~~~~~~
    
    private int[] rolls(int ... rolls) {
        return rolls;
    }
    
    private void assertScoreEquals(int expectedScore, int[] rolls) {
        int score = computeScore(rolls);

        assertThat(score, equalTo(expectedScore));
    }

    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private int computeScore(int[] rolls) {
        int score = 0;
        for (int i : rolls) {
            score += i;
        }
        
        return score;
    }
}