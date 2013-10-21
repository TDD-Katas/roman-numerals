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
    private Rolls standardRolls;
    private Rolls bonusRolls;

    public Frame(Rolls standardRolls, Rolls bonusRolls) {
        this.standardRolls = standardRolls;
        this.bonusRolls = bonusRolls;
    }

    public int getScore() {
        return standardRolls.getScore() + bonusRolls.getScore();
    }
    
}
