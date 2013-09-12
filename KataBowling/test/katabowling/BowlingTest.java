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
public class BowlingTest {

    @Test
    public void testOneFrameGame() {
        assertOneFrameGameScoreEquals(0, rolls(0, 0));
        assertOneFrameGameScoreEquals(1, rolls(1, 0));
        assertOneFrameGameScoreEquals(2, rolls(2, 0));
        assertOneFrameGameScoreEquals(1, rolls(0, 1));
        //Spare
        assertOneFrameGameScoreEquals(11, rolls(1, 9, 1));
        //Strike
        assertOneFrameGameScoreEquals(12, rolls(10, 1, 1));
    }

    @Test
    public void testTwoFrameGameOpenFrame() {
        {
            ScoreStrategy scoreStrategy = new OneFrameGameScoreStrategy();
            assertTwoFrameGameScoreEquals(0, rolls(0, 0, 0, 0), scoreStrategy);
            assertTwoFrameGameScoreEquals(1, rolls(0, 0, 1, 0), scoreStrategy);
            assertTwoFrameGameScoreEquals(4, rolls(1, 1, 1, 1), scoreStrategy);
        }
        {
            ScoreStrategy scoreStrategy = new SpareScoreStrategy();
            assertTwoFrameGameScoreEquals(13, rolls(1, 9, 1, 1), scoreStrategy);
            assertTwoFrameGameScoreEquals(15, rolls(1, 9, 2, 1), scoreStrategy);
            assertTwoFrameGameScoreEquals(18, rolls(1, 9, 3, 2), scoreStrategy);
        }
        {
            ScoreStrategy scoreStrategy = new StrikeScoreStrategy();
            assertTwoFrameGameScoreEquals(16, rolls(10, 2, 1), scoreStrategy);
            assertTwoFrameGameScoreEquals(20, rolls(10, 3, 2), scoreStrategy);
            assertTwoFrameGameScoreEquals(24, rolls(10, 1, 6), scoreStrategy);
            assertTwoFrameGameScoreEquals(32, rolls(10, 1, 9, 2), scoreStrategy);
            assertTwoFrameGameScoreEquals(34, rolls(10, 10, 1, 2), scoreStrategy);
        }
    }

    private void assertOneFrameGameScoreEquals(int expectedScore, int[] rolls) {
        int score = getSumOfRolls(rolls);

        assertThat(score, equalTo(expectedScore));
    }

    private void assertTwoFrameGameScoreEquals(int expectedScore, int[] rolls, ScoreStrategy scoreStrategy) {
        int score = scoreStrategy.computeScore(rolls);
        assertThat(score, equalTo(expectedScore));
    }

    //~~~~~~~~~~~~~~~~ Production code ~~~~~~~~~~~
    
    enum FirstFrameType {
        OPEN,
        SPARE,
        STRIKE
    }
    
    interface ScoreStrategy {
        int computeScore(int[] rolls);
    }
    
    class OneFrameGameScoreStrategy implements ScoreStrategy {
        @Override
        public int computeScore(int[] rolls) {
            return getSumOfRolls(rolls);
        }
    }
    
    class OpenFrameScoreStrategy implements ScoreStrategy {
        @Override
        public int computeScore(int[] rolls) {
            return getSumOfRolls(rolls);
        }
    }
    
    class SpareScoreStrategy implements ScoreStrategy {
        @Override
        public int computeScore(int[] rolls) {
            return computeScoreForSpareInFirstFrame(rolls);
        }
    }
    
    class StrikeScoreStrategy implements ScoreStrategy {
        @Override
        public int computeScore(int[] rolls) {
            return computeScoreForStrikeInFirstFrame(rolls);
        }
    }
    
    private int[] rolls(int... rolls) {
        return rolls;
    }
    
    private int getSumOfRolls(int[] rolls) {
        int score = 0;
        for (int i : rolls) {
            score += i;
        }
        return score;
    }

    private int computeScoreForSpareInFirstFrame(int[] rolls) {
        return getSumOfRolls(rolls) + rolls[2];
    }

    private int computeScoreForStrikeInFirstFrame(int[] rolls) {
        return getSumOfRolls(rolls) + rolls[1] + rolls[2];
    }
}