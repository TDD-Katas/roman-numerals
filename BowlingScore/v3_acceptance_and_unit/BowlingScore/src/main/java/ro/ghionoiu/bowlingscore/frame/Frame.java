/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

import static ro.ghionoiu.bowlingscore.frame.FrameType.*;
import ro.ghionoiu.bowlingscore.rolls.Rolls;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Frame {
    Rolls rolls;
    int startingIndex;
    int length;
    FrameTypeDetector frameTypeDetector;

    public Frame(Rolls rolls, int startingIndex, int length) {
        this(rolls, startingIndex, length, new FrameTypeDetector());
    }

    public Frame(Rolls rolls, int startingIndex, int length, FrameTypeDetector frameTypeDetector) {
        this.rolls = rolls;
        this.startingIndex = startingIndex;
        this.length = length;
        this.frameTypeDetector = frameTypeDetector;
    }

    //~~ Location
    public int getStartingIndex() {
        return startingIndex;
    }

    public int getLength() {
        return length;
    }

    public int getEndingIndex() {
        return startingIndex + length;
    }

    //~~ Score
    public int getScore() {
        return getNormalScore() + getBonusScore();
    }

    public int getNormalScore() {
        int startingPosition = this.getStartingIndex();
        int endingPosition = this.getEndingIndex();
        return rolls.getSumOfRolls(startingPosition, endingPosition);
    }

    public int getBonusScore() {
        int endingPosition = this.getEndingIndex();
        FrameType frameType = frameTypeDetector.getType(this);
        int numberOfBonusRolls = frameType.getNumberOfBonusRolls();
        return rolls.getSumOfRolls(endingPosition, endingPosition+numberOfBonusRolls);
    }
    
}
