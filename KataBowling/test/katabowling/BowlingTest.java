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
        scoreStrategy = new OneFrameGameScoreStrategy();
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

    private void assertGameScoreEquals(int expectedScore, int[] rolls, ScoreStrategy scoreStrategy) {
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