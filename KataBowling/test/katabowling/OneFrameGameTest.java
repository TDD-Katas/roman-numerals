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
        //Given
        int[] rolls = new int[] {0,0};
        int score = -1;
        
        //When
        score = 0;
        
        //Then
        assertThat(score, equalTo(0));
    }
}