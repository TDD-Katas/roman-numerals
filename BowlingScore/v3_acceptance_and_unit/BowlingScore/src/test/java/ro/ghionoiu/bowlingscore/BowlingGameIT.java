/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Before;
import ro.ghionoiu.bowlingscore.rolls.Rolls;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BowlingGameIT {
    Rolls.Builder rolls;
    
    @Before
    public void setUp() {
        rolls = new Rolls.Builder();
    }
    
    @Test
    public void IT_gutter_game() {
        rolls.many(0, 20);
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(0));
    }
    
    @Test
    public void IT_all_ones_game() {
        rolls.many(1, 20);
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(20));
    }
    
    @Test
    public void IT_first_frame_spare() {
        rolls.many(5, 2);
        rolls.once(1);
        rolls.many(0, 17);
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(12));
    }
    
    @Test
    public void IT_first_frame_strike() {
        rolls.once(10);
        rolls.many(1, 2);
        rolls.many(0, 17);
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(14));
    }
    
    @Test
    public void IT_perfect_game() {
        rolls.many(10, 12);
        
        int gameScore = computeGameScore(rolls);
        
        assertThat(gameScore, is(300));
    }
    
    //~~~~~~~~~~~~~~ Integration Test helpers ~~~~~~~~
    
    protected int computeGameScore(Rolls.Builder builder) {
        BowlingGame game = new BowlingGame();
        return game.computeScore(builder.build());
    }
}