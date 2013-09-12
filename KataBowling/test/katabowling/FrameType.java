/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package katabowling;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
 enum FrameType {
    OPEN(2, 0), SPARE(2, 1), STRIKE(1, 2);
    //Members
    int frameSize;
    int bonusRolls;

    private FrameType(int frameSize, int bonusRolls) {
        this.frameSize = frameSize;
        this.bonusRolls = bonusRolls;
    }

    int getFrameSize() {
        return frameSize;
    }

    int getBonusRolls() {
        return bonusRolls;
    }
    
}
