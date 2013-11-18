/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.frame;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class FrameTypeDetector {

    public FrameType getType(Frame frame) {
        FrameType frameType = FrameType.OPEN;
        if (frame.getNormalScore() == 10) {
            frameType = FrameType.SPARE;
            if (frame.getLength() == 1) {
                frameType = FrameType.STRIKE;
            }
        }
        return frameType;
    }
    
}
