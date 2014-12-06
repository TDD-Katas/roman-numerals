import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void value_of_empty_is_zero() {
        assertThat(valueOf(""), is(0));
    }

    @Test
    public void value_of_I_is_one() {
        assertThat(valueOf("I"), is(1));
    }

    @Test
    public void value_of_V_is_five() {
        assertThat(valueOf("V"), is(5));
    }

    @Test
    public void value_of_X_is_ten() {
        assertThat(valueOf("X"), is(10));
    }

    //~~~ Impl

    private int valueOf(String numeral) {
        if (numeral == "") {
            return 0;
        }
        if (numeral == "I") {
            return 1;
        }
        if (numeral == "V") {
            return 5;
        }

        return 10;
    }
}