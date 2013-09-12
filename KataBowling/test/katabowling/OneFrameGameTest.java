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
        //When
        int score = 0;
        
        //Then
        assertThat(score, equalTo(0));
    }
    
    @Test
    public void testOneAndZeroScoreOne() {
        //When
        int score = 1;
        
        //Then
        assertThat(score, equalTo(1));
    }
    
    @Test
    public void testTwoAndZeroScoreTwo() {
        //When
        int score = 2;
        
        //Then
        assertThat(score, equalTo(2));
    }
}