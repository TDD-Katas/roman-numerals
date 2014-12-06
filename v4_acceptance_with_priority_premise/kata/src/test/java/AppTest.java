import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AppTest {

    private static final String UNRECOGNIZED_CHARACTER = "_";

    @Test
    public void value_of_empty_is_0() {
        assertThat(valueOf(numeral("")), is(0));
    }

    @Test
    public void value_of_I_is_1() {
        assertThat(valueOf(numeral("I")), is(1));
    }

    @Test
    public void value_of_V_is_5() {
        assertThat(valueOf(numeral("V")), is(5));
    }

    @Test
    public void value_of_X_is_10() {
        assertThat(valueOf(numeral("X")), is(10));
    }

    @Test
    public void value_of_L_is_50() {
        assertThat(valueOf(numeral("L")), is(50));
    }

    @Test
    public void value_of_C_is_100() {
        assertThat(valueOf(numeral("C")), is(100));
    }

    @Test
    public void value_of_D_is_500() {
        assertThat(valueOf(numeral("D")), is(500));
    }

    @Test
    public void value_of_M_is_1000() {
        assertThat(valueOf(numeral("M")), is(1000));
    }

    @Test( expected = IllegalCharacterException.class )
    public void throws_exception_for_unrecognized_character() {
        valueOf(numeral(UNRECOGNIZED_CHARACTER));
    }

    @Test
    public void value_of_two_character_numeral_is_their_sum() {
        assertThat(valueOf(numeral("VI")), is(6));
    }

    @Test
    public void value_of_three_character_numeral_is_their_sum() {
        assertThat(valueOf(numeral("XVI")), is(16));
    }

    @Test
    public void value_of_small_literal_before_big_literal_is_substracted() {
        assertThat(valueOf(numeral("IV")), is(4));
    }

    //~~~ Impl

    public static class IllegalCharacterException extends RuntimeException {

    }
    private static class Numeral {

        private String value;
        private Numeral(String value) {
            this.value = value;
        }

        public int length() {
            return value.length();
        }

        public char charAt(int index) {
            return value.charAt(index);
        }
    }

    private static Numeral numeral(String s) {
        return new Numeral(s);
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

    private int valueOf(Numeral numeral) {
        int sum = 0;
        for (int i = 0; i < numeral.length(); i++) {

            int currentValue = valueOfLiteral(numeral.charAt(i));
            int nextValue = 0;

            if (i+1 < numeral.length()) {
                nextValue = valueOfLiteral(numeral.charAt(i + 1));
            }

            if (currentValue < nextValue) {
                sum -= currentValue;
            } else {
                sum += currentValue;
            }
        }
        return sum;
    }
}