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
        scoreStrategy = new TwoFrameGameScoreStrategy();
        assertGameScoreEquals(0, rolls(0, 0, 0, 0), scoreStrategy);
        assertGameScoreEquals(1, rolls(0, 0, 1, 0), scoreStrategy);
        assertGameScoreEquals(4, rolls(1, 1, 1, 1), scoreStrategy);
        //Spare
        assertGameScoreEquals(13, rolls(1, 9, 1, 1), scoreStrategy);
        assertGameScoreEquals(15, rolls(1, 9, 2, 1), scoreStrategy);
        assertGameScoreEquals(18, rolls(1, 9, 3, 2), scoreStrategy);
        //Strike
        assertGameScoreEquals(16, rolls(10, 2, 1), scoreStrategy);
        assertGameScoreEquals(20, rolls(10, 3, 2), scoreStrategy);
        assertGameScoreEquals(24, rolls(10, 1, 6), scoreStrategy);
        assertGameScoreEquals(32, rolls(10, 1, 9, 2), scoreStrategy);
        assertGameScoreEquals(34, rolls(10, 10, 1, 2), scoreStrategy);
    }
    
    @Test
    public void testGetStrategyForRolls() {
        Class strategyClass;
        strategyClass = OpenFrameScoreStrategy.class;
        assertGetStrategyForRollsEquals(strategyClass, rolls(0, 0));
        assertGetStrategyForRollsEquals(strategyClass, rolls(1, 1));
        strategyClass = SpareScoreStrategy.class;
        assertGetStrategyForRollsEquals(strategyClass, rolls(1, 9));
        strategyClass = StrikeScoreStrategy.class;
        assertGetStrategyForRollsEquals(strategyClass, rolls(10));
        assertGetStrategyForRollsEquals(strategyClass, rolls(10));
    }
    
    private void assertGameScoreEquals(int expectedScore, int[] rolls, ScoreStrategy scoreStrategy) {
        int score = scoreStrategy.computeScore(rolls);
        assertThat(score, equalTo(expectedScore));
    }
    
   private void assertGetStrategyForRollsEquals(Class expectedStrategy, int[] rolls) {
        ScoreStrategy result = getStrategyForRolls(rolls);
        
        assertTrue("Score strategy is not of required type, "
                + "expected = "+expectedStrategy.getSimpleName()+
                ", found = "+result.getClass().getSimpleName(), expectedStrategy.isInstance(result));
    }

    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    private ScoreStrategy getStrategyForRolls(int[] rolls) {
        if (rolls[0] == 10) {
            return new StrikeScoreStrategy();
        } else
        if (rolls[0] + rolls[1] == 10) {
            return new SpareScoreStrategy();
        }
        
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

    class TwoFrameGameScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            ScoreStrategy scoreStrategy = getStrategyForRolls(rolls);
            return scoreStrategy.computeScore(rolls);
        }
    }
    
    class OpenFrameScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return computeScoreWithOpenFrameFirst(rolls);
        }
    }

    class SpareScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return computeScoreWithSpareFirst(rolls);
        }
    }

    class StrikeScoreStrategy implements ScoreStrategy {

        @Override
        public int computeScore(int[] rolls) {
            return computeScoreWithStrikeFirst(rolls);
        }
    }

    private int[] rolls(int... rolls) {
        return rolls;
    }

    private int computeScoreWithOpenFrameFirst(int[] rolls) {
        int[] finalFrame = Arrays.copyOfRange(rolls, 2, rolls.length);
        return rolls[0] + rolls[1] + getFinalFrameScore(finalFrame);
    }
    
    private int computeScoreWithSpareFirst(int[] rolls) {
        int[] finalFrame = Arrays.copyOfRange(rolls, 2, rolls.length);
        return rolls[0] + rolls[1] + rolls[2] + getFinalFrameScore(finalFrame);
    }
    
    private int computeScoreWithStrikeFirst(int[] rolls) {
            int[] finalFrame = Arrays.copyOfRange(rolls, 1, rolls.length);
            return rolls[0] + rolls[1] + rolls[2] + getFinalFrameScore(finalFrame);
    }
    
    private int getFinalFrameScore(int[] rolls) {
        int score = 0;
        for (int i : rolls) {
            score += i;
        }
        return score;
    }
}