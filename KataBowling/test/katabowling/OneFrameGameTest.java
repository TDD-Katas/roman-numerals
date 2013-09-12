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
        
        int score = rolls[0];
        
        assertThat(score, equalTo(0));
    }
    
    @Test
    public void testOneAndZeroScoreOne() {
        int [] rolls = new int[] {1,0};
        
        int score = rolls[0];
        
        assertThat(score, equalTo(1));
    }
    
    @Test
    public void testTwoAndZeroScoreTwo() {
        int [] rolls = new int[] {2,0};
        
        int score = rolls[0];
        
        assertThat(score, equalTo(2));
    }
}