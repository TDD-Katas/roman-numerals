/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kata;

/**
 *
 * @author Iulian Ghionoiu <iulian.ghionoiu@exenne.ro>
 */
public class AMain {
    
    public static void main(String[] args) {
        RomanNumber romanNumber = new RomanNumber("XXXIV");
        int value = romanNumber.computeIntegerValue();
        
        System.out.println("value = "+value);
    }
            
}
