/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class KataTest {
    
    /**
     * Test of main method, of class Kata.
     */
    @Test
    public void score_of_a_spare_equals_curennt_frame_pins_plus_next_roll_score() {
        int curentFramePins = 1;
        int nextRollScore = 2;
        
        int scoreOfSpare = curentFramePins + nextRollScore;
        
        assertThat(scoreOfSpare, is(curentFramePins+nextRollScore));
    }
}