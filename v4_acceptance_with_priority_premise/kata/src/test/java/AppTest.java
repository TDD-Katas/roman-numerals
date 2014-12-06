import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AppTest {

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

    //~~~ Impl

    private int valueOf(String numeral) {
        if (numeral.equals("")) {
            return 0;
        }
        if (numeral.equals("I")) {
            return 1;
        }
        if (numeral.equals("V")) {
            return 5;
        }
        if (numeral.equals("X")) {
            return 10;
        }
        if (numeral.equals("L")) {
            return 50;
        }
        if (numeral.equals("C")) {
            return 100;
        }
        if (numeral.equals("D")) {
            return 500;
        }
        return 1000;
    }
}