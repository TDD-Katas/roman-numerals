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
    
    @Test
    public void IT_gutter_game_score_is_0() {
        int[] rolls = {0,0,0,0,0,0,0,0,0,0};
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(0));
    }

    protected int computeGameScore(int[] rolls) {
        return 0;
    }
}