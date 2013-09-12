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
    public void testTwoZerosScoreZero() {
        assertScoreEquals(0, rolls(0, 0));
    }

    @Test
    public void testOneAndZeroScoreOne() {
        assertScoreEquals(1, rolls(1, 0));
    }

    @Test
    public void testTwoAndZeroScoreTwo() {
        assertScoreEquals(2, rolls(2, 0));
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
        return rolls[0];
    }


}