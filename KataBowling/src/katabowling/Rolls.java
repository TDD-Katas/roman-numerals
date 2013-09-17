package katabowling;

import java.util.Arrays;


class Rolls {
    private final int[] rollsArray;
    private final FrameType frameType;

    //~~~~ Construction area
    public Rolls(int... rolls) {
        this.rollsArray = rolls;
        this.frameType = getFrameType();
    }

    public static Rolls create(int... rolls) {
        return new Rolls(rolls);
    }

    FrameType getFrameType() {
        FrameType localFrameType;
        if (rollsArray[0] == 10) {
            localFrameType = FrameType.STRIKE;
        } else if (rollsArray[0] + rollsArray[1] == 10) {
            localFrameType = FrameType.SPARE;
        } else {
            localFrameType = FrameType.OPEN;
        }
        return localFrameType;
    }

    public Rolls getNextFramesRolls() {
        int frameSize = frameType.getFrameSize();
        int[] newArray = Arrays.copyOfRange(rollsArray, frameSize, rollsArray.length);
        return Rolls.create(newArray);
    }

    //~~~~ Public methods
    public int computeTotalScoreForFrame() {
        return getSumForCurrentFrame() + computeBonusRollsScore();
    }

    public int getSumForAllRolls() {
        return getSum(0, rollsArray.length);
    }

    //~~~~ COmputing methods
    private int getSumForCurrentFrame() {
        int start = 0;
        int end = frameType.getFrameSize();
        return getSum(start, end);
    }

    private int computeBonusRollsScore() {
        int numberOfBonusRolls = frameType.getBonusRolls();
        int start = 3 - numberOfBonusRolls;
        int end = 3;
        return getSum(start, end);
    }

    //~~~ Utils
    private int getSum(int start, int end) {
        int score = 0;
        for (int i = start; i < end; i++) {
            score += rollsArray[i];
        }
        return score;
    }
    
}
