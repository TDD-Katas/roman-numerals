/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
class FrameType {
    private int numberOfFrameRolls;
    private int numberOfBonusRolls;

    public FrameType(int numberOfFrameRolls, int numberOfBonusRolls) {
        this.numberOfFrameRolls = numberOfFrameRolls;
        this.numberOfBonusRolls = numberOfBonusRolls;
    }

    public int getNumberOfFrameRolls() {
        return numberOfFrameRolls;
    }

    public int getNumberOfBonusRolls() {
        return numberOfBonusRolls;
    }
    
}
