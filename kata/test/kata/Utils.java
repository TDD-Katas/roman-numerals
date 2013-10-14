/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Utils {
    public static int sumOfValues(int[] values) {
        int scoreOfGame = 0;
        for (int i : values) {
            scoreOfGame += i;
        }
        return scoreOfGame;
    }
}
