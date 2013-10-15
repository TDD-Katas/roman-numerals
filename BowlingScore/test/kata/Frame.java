/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
class Frame {
    private int[] standardRolls;
    private int[] bonusRolls;

    public Frame(int[] standardRolls, int[] bonusRolls) {
        this.standardRolls = standardRolls;
        this.bonusRolls = bonusRolls;
    }

    public int[] getStandardRolls() {
        return new int[0];
    }

    public int[] getBonusRolls() {
        return new int[0];
    }

    public int getStandardScore() {
        return KataTest.computeSumOfRolls(getStandardRolls());
    }

    public int getBonusScore() {
        return KataTest.computeSumOfRolls(getBonusRolls());
    }

    public int getScore() {
        return getStandardScore() + getBonusScore();
    }
    
}
