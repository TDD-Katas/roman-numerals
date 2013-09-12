/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package katabowling;

import java.util.Arrays;
import static org.hamcrest.core.IsEqual.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class BowlingTest {

    @Test
    public void testOneFrameGame() {
        ScoreStrategy scoreStrategy = new OneFrameGameScoreStrategy();
        assertGameScoreEquals(0, rolls(0, 0), scoreStrategy);
        assertGameScoreEquals(1, rolls(1, 0), scoreStrategy);
        assertGameScoreEquals(2, rolls(2, 0), scoreStrategy);
        assertGameScoreEquals(1, rolls(0, 1), scoreStrategy);
        //Spare
        assertGameScoreEquals(11, rolls(1, 9, 1), scoreStrategy);
        //Strike
        assertGameScoreEquals(12, rolls(10, 1, 1), scoreStrategy);
    }

    @Test
    public void testTwoFrameGameOpenFrame() {
        ScoreStrategy scoreStrategy;
        scoreStrategy = new OpenFrameScoreStrategy();
        assertGameScoreEquals(0, rolls(0, 0, 0, 0), scoreStrategy);
        assertGameScoreEquals(1, rolls(0, 0, 1, 0), scoreStrategy);
        assertGameScoreEquals(4, rolls(1, 1, 1, 1), scoreStrategy);
        scoreStrategy = new SpareScoreStrategy();
        assertGameScoreEquals(13, rolls(1, 9, 1, 1), scoreStrategy);
        assertGameScoreEquals(15, rolls(1, 9, 2, 1), scoreStrategy);
        assertGameScoreEquals(18, rolls(1, 9, 3, 2), scoreStrategy);
        scoreStrategy = new StrikeScoreStrategy();
        assertGameScoreEquals(16, rolls(10, 2, 1), scoreStrategy);
        assertGameScoreEquals(20, rolls(10, 3, 2), scoreStrategy);
        assertGameScoreEquals(24, rolls(10, 1, 6), scoreStrategy);
        assertGameScoreEquals(32, rolls(10, 1, 9, 2), scoreStrategy);
        assertGameScoreEquals(34, rolls(10, 10, 1, 2), scoreStrategy);
    }
    
    @Test
    public void testGetStrategyForRolls1() {
        ScoreStrategy result = null;
        int[] rolls = rolls(0, 0, 0, 0);
        Class expectedStrategy = OpenFrameScoreStrategy.class;
        
        result = getStrategyForRolls(rolls);
        
        assertTrue(expectedStrategy.isInstance(result));
    }
    
    @Test
    public void testGetStrategyForRolls2() {
        ScoreStrategy result = null;
        int[] rolls = rolls(0, 0, 1, 0);
        Class expectedStrategy = OpenFrameScoreStrategy.class;
        
        result = getStrategyForRolls(rolls);
        
        assertTrue(expectedStrategy.isInstance(result));
    }
    
    @Test
    public void testGetStrategyForRolls3() {
        ScoreStrategy result = null;
        int[] rolls = rolls(1, 1, 1, 1);
        Class expectedStrategy = OpenFrameScoreStrategy.class;
        
        result = getStrategyForRolls(rolls);
        
        assertTrue(expectedStrategy.isInstance(result));
    }
    
    private void assertGameScoreEquals(int expectedScore, int[] rolls, ScoreStrategy scoreStrategy) {
        int score = scoreStrategy.computeScore(rolls);
        assertThat(score, equalTo(expectedScore));
    }

    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private ScoreStrategy getStrategyForRolls(int[] rolls) {
        return new OpenFrameScoreStrategy();
    }

    interface ScoreStrategy {

        int computeScore(int[] rolls);
    }

    class OneFrameGameScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return getFinalFrameScore(rolls);
        }
    }

    class OpenFrameScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            int[] finalFrame = Arrays.copyOfRange(rolls, 2, rolls.length);
            return rolls[0] + rolls[1] + getFinalFrameScore(finalFrame);
        }
    }

    class SpareScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            int[] finalFrame = Arrays.copyOfRange(rolls, 2, rolls.length);
            return rolls[0] + rolls[1] + rolls[2] + getFinalFrameScore(finalFrame);
        }
    }

    class StrikeScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            int[] finalFrame = Arrays.copyOfRange(rolls, 1, rolls.length);
            return rolls[0] + rolls[1] + rolls[2] + getFinalFrameScore(finalFrame);
        }
    }

    private int[] rolls(int... rolls) {
        return rolls;
    }

    private int getFinalFrameScore(int[] rolls) {
        int score = 0;
        for (int i : rolls) {
            score += i;
        }
        return score;
    }
}