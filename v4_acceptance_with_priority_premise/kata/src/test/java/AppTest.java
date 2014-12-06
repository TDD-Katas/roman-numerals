import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AppTest {

    private static final String UNRECOGNIZED_CHARACTER = "_";

    @Test
    public void value_of_empty_is_0() {
        assertThat(valueOf(""), is(0));
    }

    @Test
    public void value_of_I_is_1() {
        assertThat(valueOf("I"), is(1));
    }

    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf("V"), is(5));
    }

    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf("X"), is(10));
    }

    @Test
    public void value_of_L_is_50() {
        assertThat(valueOf("L"), is(50));
    }

    @Test
    public void value_of_C_is_100() {
        assertThat(valueOf("C"), is(100));
    }

    @Test
    public void value_of_D_is_500() {
        assertThat(valueOf("D"), is(500));
    }

    @Test
    public void value_of_M_is_1000() {
        assertThat(valueOf("M"), is(1000));
    }

    @Test( expected = IllegalCharacterException.class )
    public void throws_exception_for_unrecognized_character() {
        valueOf(UNRECOGNIZED_CHARACTER);
    }

    @Test
    public void value_of_two_character_numeral_is_their_sum() {
        assertThat(valueOf("VI"), is(6));
    }

    @Test
    public void value_of_three_character_numeral_is_their_sum() {
        assertThat(valueOf("XVI"), is(16));
    }

    @Test
    public void value_of_small_literal_before_big_literal_is_substracted() {
        assertThat(valueOf("IV"), is(4));
    }

    //~~~ Impl

    public static class IllegalCharacterException extends RuntimeException {
    }


    private static final Map<Character, Integer> VALUES = new HashMap<>();
    static {
        VALUES.put('I', 1);
        VALUES.put('V', 5);
        VALUES.put('X', 10);
        VALUES.put('L', 50);
        VALUES.put('C', 100);
        VALUES.put('D', 500);
        VALUES.put('M', 1000);
    }

    private static int valueOfLiteral(char literal) {
        Integer value = VALUES.get(literal);
        if (value == null) {
            throw new IllegalCharacterException();
        }

        return value;
    }

    private int valueOf(String numeral) {
        int sum = 0;
        for (int i = 0; i < numeral.length(); i++) {

            int currentValue = valueOfLiteral(numeral.charAt(i));

            if (i+1 < numeral.length()) {
                int nextValue = valueOfLiteral(numeral.charAt(i + 1));


                if (currentValue < nextValue) {
                    sum -= currentValue;
                } else {
                    sum += currentValue;
                }
            } else {
                sum += currentValue;
            }
        }
        return sum;
    }
}