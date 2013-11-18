/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

import ro.ghionoiu.bowlingscore.rolls.Rolls;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameIdentifier {

    public Frame getFrame(int frameNumber, Rolls rolls) {
        int startingIndex;
        if (frameNumber == 0) {
            startingIndex = 0;
        } else {
            Frame previousFrame = getFrame(frameNumber - 1, rolls);
            startingIndex = previousFrame.getEndingIndex();
        }
        return createFrame(rolls, startingIndex);
    }

    private Frame createFrame(Rolls rolls, int startingIndex) {
        int length = computeFrameLength(rolls, startingIndex);
        return new Frame(rolls, startingIndex, length);
    }
    
    private int computeFrameLength(Rolls rolls, int startingPosition) {
        if (rolls.getValueAt(startingPosition) == 10) {
            return 1;
        } else {
            return 2;
        }
    }
}
