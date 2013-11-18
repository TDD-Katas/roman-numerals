/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.ghionoiu.bowlingscore.rolls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class Rolls {
    private List<Integer> backingList;

    //~~~~~~~~~~~~~ Construction ~~~~~~~~
    
    private Rolls(List<Integer> rolls) {
        this.backingList = rolls;
    }

    public static Rolls create(Integer... rolls) {
        List<Integer> list = Arrays.asList(rolls);
        return new Rolls(list);
    }

    public static Rolls create(List<Integer> rolls) {
        return new Rolls(rolls);
    }
    
    public static class Builder {
        List<Integer> rollsList;

        public Builder() {
            rollsList = new ArrayList<Integer>();
        }

        public void once(int number) {
            rollsList.add(number);
        }

        public void many(int number, int times) {
            for (int i = 0; i < times; i++) {
                once(number);
            }
        }

        public Rolls build() {
            return Rolls.create(rollsList);
        }

    }

    //~~~~~~~~~~~~~ Methods ~~~~~~~~~~
    
    public int getValueAt(int index) {
        return backingList.get(index);
    }

    public int getSumOfRolls(int startingPosition, int endingPosition) {
        int sum = 0;
        for (int i = startingPosition; i < endingPosition; i++) {
            sum += this.getValueAt(i);
        }
        return sum;
    }
    
}
