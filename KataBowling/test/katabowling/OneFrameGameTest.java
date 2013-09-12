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
        int [] rolls = new int[] {0,0};
        int expectedScore = 0;
        
        int score = computeScore(rolls);
        
        assertThat(score, equalTo(expectedScore));
    }
    
    @Test
    public void testOneAndZeroScoreOne() {
        int [] rolls = new int[] {1,0};
        int expectedScore = 1;
        
        int score = computeScore(rolls);
        
        assertThat(score, equalTo(expectedScore));
    }
    
    @Test
    public void testTwoAndZeroScoreTwo() {
        int [] rolls = new int[] {2,0};
        int expectedScore = 2;
        
        int score = computeScore(rolls);
        
        assertThat(score, equalTo(expectedScore));
    }
    
    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private int computeScore(int[] rolls) {
        return rolls[0];
    }
}