/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    
    //~~~~~~~~~~~~~~ Production methods ~~~~~~~~

    protected int computeGameScore(int[] rolls) {
        return 0;
    }
    
    //~~~~~~~~~~~~~~ Test methods ~~~~~~~~
    
    @Test
    public void IT_gutter_game_score_is_0() {
        int[] rolls = new int[10];
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = 0;
        }
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(0));
    }
    
    //~~~~~~~~~~~~~~ Helpers methods ~~~~~~~~
}