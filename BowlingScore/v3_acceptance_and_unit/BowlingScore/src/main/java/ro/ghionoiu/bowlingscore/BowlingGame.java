/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore;

//~~~~~~~~~~~~~~ Production ~~~~~~~~

import ro.ghionoiu.bowlingscore.frame.Frame;
import ro.ghionoiu.bowlingscore.frame.FrameIdentifier;
import ro.ghionoiu.bowlingscore.rolls.Rolls;


public class BowlingGame {
    public static final int DEFAUT_NUMBER_OF_FRAMES = 10;
    private int numberOfFrames;
    private FrameIdentifier frameIdentifier;

    public BowlingGame() {
        this(DEFAUT_NUMBER_OF_FRAMES, new FrameIdentifier());
    }

    public BowlingGame(FrameIdentifier frameExtractor) {
        this(DEFAUT_NUMBER_OF_FRAMES, frameExtractor);
    }

    public BowlingGame(int numberOfFrames, FrameIdentifier frameIdentifier) {
        this.numberOfFrames = numberOfFrames;
        this.frameIdentifier = frameIdentifier;
    }
    
    //~~~ Public methods

    public int computeScore(Rolls rolls) {
        int gameScore = 0;
        for (int i = 0; i < numberOfFrames; i++) {
            Frame frame = frameIdentifier.getFrame(i, rolls);
            gameScore += frame.getScore();
        }
        return gameScore;
    }
    
}
