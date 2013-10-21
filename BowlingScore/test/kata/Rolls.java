/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

import java.util.Arrays;

public class Rolls {
    private final int[] values;
    
    public Rolls(int[] values) {
        this.values = values;
    }
    
    public FrameType computeFrameType() {
        return getFrameType(values[0], values[1]);
    } 

    public Rolls getActiveSubsetAccordingTo(FrameType frameType) {
        int currentIndex = 0;
        int numberOfRolls = frameType.getNumberOfFrameRolls();
        return this.subset(currentIndex, numberOfRolls);
    }
    
    public Rolls getBonusSubsetAccordingTo(FrameType frameType) {
        int currentIndex = 0;
        int numberOfRolls = frameType.getNumberOfBonusRolls();
        return this.subset(currentIndex, numberOfRolls);
    }
    
    public Rolls getRemainingSubsetAccordingTo(FrameType frameType) {
        int currentIndex = frameType.getNumberOfFrameRolls();
        int numberOfRolls = values.length - currentIndex;
        return this.subset(currentIndex, numberOfRolls);
    }
    
    
    public Rolls subset(int currentIndex, int numberOfRolls) {
        int[] subsetValues = new int[numberOfRolls];
        for (int i = 0; i < numberOfRolls; i++) {
            subsetValues[i] = values[currentIndex+i];
        }
        return new Rolls(subsetValues);
    }
    
    public boolean hasNext() {
        return values.length > 0;
    }
    
    public int getScore() {
        int score = 0;
        for (int i : values) {
            score += i;
        }
        return score;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Arrays.hashCode(this.values);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rolls other = (Rolls) obj;
        if (!Arrays.equals(this.values, other.values)) {
            return false;
        }
        return true;
    }
    
    //~~~~
    
    protected static FrameType getFrameType(int valueOfFirstRoll, int valueOfSecondRoll) {
        FrameType frameType = FrameType.OPEN;
        if (valueOfFirstRoll == 10) {
            frameType = FrameType.STRIKE;
        } else if (valueOfFirstRoll + valueOfSecondRoll == 10) {
            frameType = FrameType.SPARE;
        }

        return frameType;
    }
}
