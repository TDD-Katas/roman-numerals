package katabowling;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


//~~~~~~~~~~ Score methods ~~~~~~~

class BowlingScoring {
    private final int gameFrames;
    public BowlingScoring(int gameFrames) {
        this.gameFrames = gameFrames;
    }

    public int score(Rolls rolls) {
        return computeScore(gameFrames, rolls);
    }

    private int computeScore(int framesLeft, Rolls rolls) {
        int score;
        if (framesLeft == 1) {
            score = computeFinalFrameScore(rolls);
        } else {
            score = computeRegularFrameScore(rolls, framesLeft);
        }
        return score;
    }

    private int computeFinalFrameScore(Rolls rolls) {
        return rolls.getSumForAllRolls();
    }

    private int computeRegularFrameScore(Rolls rolls, int framesLeft) {
        int score;
        int totalScoreForCurrentFrame = rolls.computeTotalScoreForFrame();
        Rolls nextRolls = rolls.getNextFramesRolls();
        int scoreForOtherFrames = computeScore(framesLeft - 1, nextRolls);
        score = totalScoreForCurrentFrame + scoreForOtherFrames;
        return score;
    }
    
}
