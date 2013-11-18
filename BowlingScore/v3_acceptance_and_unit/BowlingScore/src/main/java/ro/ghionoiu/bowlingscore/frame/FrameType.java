/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
 public enum FrameType {
    OPEN(0), 
    SPARE(1), 
    STRIKE(2);
    
    private int numberOfBonusRolls;

    private FrameType(int numberOfBonusRolls) {
        this.numberOfBonusRolls = numberOfBonusRolls;
    }

    public int getNumberOfBonusRolls() {
        return numberOfBonusRolls;
    }
}
