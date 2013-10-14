/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class TestUtils {
    public static int sumOfValues(int[] frameScores) {
        int scoreOfGame = 0;
        for (int i : frameScores) {
            scoreOfGame += i;
        }
        return scoreOfGame;
    }
}
