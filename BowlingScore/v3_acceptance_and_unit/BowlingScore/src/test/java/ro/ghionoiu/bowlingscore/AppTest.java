/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore;

import java.util.Arrays;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import org.junit.Ignore;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AppTest {
    public static final int NORMAL_ROLL = 2;
    public static final int MAXIMUM_ROLL = 10;
    
    //~~~~~~~~~~~~~~ Integration Tests ~~~~~~~~
    
    @Ignore
    @Test
    public void IT_gutter_game_score_is_0() {
        int[] rolls = rollAllAs(0);
        
        int gameScore = targetComputeGameScore(rolls);
        
        assertThat(gameScore, is(0));
    }
    
    //~~~~~~~~~~~~~~ Unit Tests ~~~~~~~~
    
    @Test
    public void game_score_equals_sum_of_frames_score() {
        Game game = new Game(null);
        FrameExtractorBuilder frameExtractorBuilder = mock(FrameExtractorBuilder.class);
        
        int gameScore = game.computeScore(anyRolls().getArray());
        
        assertThat(gameScore, is(0));
    }
    
    //  __________ Frames definition _____
    
    
    @Test
    public void first_frame_starts_at_index_0() {
        Rolls rolls = anyRolls();
        FrameExtractor frameExtractor = new FrameExtractor(rolls);
        
        Frame firstFrame = frameExtractor.getFirstFrame();
        
        assertThat(firstFrame.getStartingIndex(), is(0));
    }
    
    @Test
    public void first_frame_has_a_length_of_1_if_its_first_roll_is_maximum_value() {
        Rolls rolls = firstRollIsMaximumRoll();
        FrameExtractor frameExtractor = new FrameExtractor(rolls);
        
        Frame firstFrame = frameExtractor.getFirstFrame();
        
        assertThat(firstFrame.getLength(), is(1));
    }
        
    @Test
    public void first_frame_has_a_length_of_2_if_its_first_roll_is_maximum_value() {
        Rolls rolls = firstRollNotMaximumRoll();
        FrameExtractor frameExtractor = new FrameExtractor(rolls);
        
        Frame firstFrame = frameExtractor.getFirstFrame();
        
        assertThat(firstFrame.getLength(), is(2));
    }
    
        
    @Test
    public void frame_ends_at_starting_index_plus_length() {
        int startingIndex = 1;
        int length = 2;
        
        Frame frame = new Frame(startingIndex, length);
        
        assertThat(frame.getEndingIndex(), is(startingIndex+length));
    }
    
    @Test
    public void the_Nth_frame_starts_at_previous_frame_ending_index() {
        Rolls rolls = anyRolls();
        FrameExtractor frameExtractor = new FrameExtractor(rolls);
        int currentFrameNumber = 1;
        
        Frame currentFrame = frameExtractor.getFrame(currentFrameNumber);
        
        Frame previousFrame = frameExtractor.getFrame(currentFrameNumber-1);
        assertThat(currentFrame.getStartingIndex(), is(previousFrame.getEndingIndex()));
    }
    
    //~~~~ Rolls subsets
    
    @Test
    public void frame_normal_rolls_are_rolls_between_starting_and_ending_index() {
        Rolls rolls = Rolls.create(0, 1, 2, 3);
        Frame frame = new Frame(0, 2);
        Rolls expectedNormalRolls = Rolls.create(0, 1);
        
        Rolls normalRolls = rolls.subset(frame.getStartingIndex(), frame.getEndingIndex());
        
        assertThat(normalRolls, is(expectedNormalRolls));
    }
    
    @Test
    public void frame_score_equals_normal_rolls_value() {
        int normalRollsValue = 1;
        
        int frameScore = normalRollsValue;
        
        assertThat(frameScore, is(normalRollsValue));
    }
    
           
    //~~~~~~~~~~~~~~ Test helpers ~~~~~~~~

    protected int[] rollAllAs(int rollValue) {
        int[] rolls = new int[10];
        for (int i = 0; i < rolls.length; i++) {
            rolls[i] = rollValue;
        }
        return rolls;
    }
    protected Rolls anyRolls() {
        return Rolls.create(NORMAL_ROLL, NORMAL_ROLL, NORMAL_ROLL, NORMAL_ROLL);
    }
    
    protected Rolls firstRollIsMaximumRoll() {
        return Rolls.create(MAXIMUM_ROLL, NORMAL_ROLL);
    }
    
    protected Rolls firstRollNotMaximumRoll() {
        return Rolls.create(NORMAL_ROLL, NORMAL_ROLL);
    }

    //~~~~~~~~~~~~~~ Production ~~~~~~~~
    
    
    protected int targetComputeGameScore(int[] rolls) {
        Rolls gameRolls = Rolls.create(rolls);
        
        int gameScore = 0;
        FrameExtractor frameExtractor = new FrameExtractor(gameRolls);
        for (int i = 0; i < 10; i++) {
            Frame frame = frameExtractor.getFrame(0);
            gameScore += frame.getScore();
        }
        
        return gameScore;
    }
    
    static class Game {
        private FrameExtractorBuilder frameExtractorBuilder;
        
        public Game(FrameExtractorBuilder frameExtractorBuilder) {
            this.frameExtractorBuilder = frameExtractorBuilder;
        }
        
        public int computeScore(int[] rolls) {
            Rolls gameRolls = Rolls.create(rolls);

            int gameScore = 0;
            FrameExtractor frameExtractor = frameExtractorBuilder.createExtractorFor(gameRolls);
            for (int i = 0; i < 10; i++) {
                Frame frame = frameExtractor.getFrame(0);
                gameScore += frame.getScore();
            }

            return gameScore;
        }
        
        
        
    }
    
    static class Frame {
        int startingIndex;
        int length;

        public Frame(int startingIndex, int length) {
            this.startingIndex = startingIndex;
            this.length = length;
        }
        

        public int getStartingIndex() {
            return startingIndex;
        }

        public int getLength() {
            return length;
        }
        
        public int getEndingIndex() {
            return startingIndex + length;
        }
        
        public int getScore() {
            throw new NotImplementedException();
        }
    }
    
    static class Rolls {
        private int[] array;
        
        public Rolls(int ... rolls) {
            this.array = rolls;
        }
        
        public static Rolls create(int ... rolls) {
            return new Rolls(rolls);
        }
        
        public int[] getArray() {
            return array;
        }
        
        public Rolls subset(int startingIndex, int endingIndex) {
            return create(Arrays.copyOfRange(array, startingIndex, endingIndex));
        } 
                
        //~~ Equals

        @Override
        public int hashCode() {
            int hash = 3;
            hash = 89 * hash + Arrays.hashCode(this.array);
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
            if (!Arrays.equals(this.array, other.array)) {
                return false;
            }
            return true;
        }
        
        //To string
        @Override
        public String toString() {
            return "Rolls{" + "array=" + Arrays.toString(array) + '}';
        }
    }
    
    static class FrameExtractorBuilder {
        public FrameExtractor createExtractorFor(Rolls rolls) {
            return new FrameExtractor(rolls);
        }
    }
    
    static class FrameExtractor {
        Rolls rolls;

        public FrameExtractor(Rolls rolls) {
            this.rolls = rolls;
        }
        
        
        public Frame getFrame(int frameNumber) {
            if (frameNumber == 0) {
                return getFirstFrame();
            } else {
                Frame previousFrame = getFrame(frameNumber-1);
                int startingIndex = previousFrame.getEndingIndex();
                int length = computeFrameLength(startingIndex);
                return new Frame(startingIndex, length);
            }
        }
        
        public Frame getFirstFrame() {
            int startingIndex = 0;
            int length = computeFrameLength(startingIndex);
            return new Frame(startingIndex, length);
        }
        
        public int computeFrameLength(int startingPosition) {
            if (rolls.getArray()[startingPosition] == MAXIMUM_ROLL) {
                return 1;
            } else {
                return 2;
            }
        }
    }
    
}