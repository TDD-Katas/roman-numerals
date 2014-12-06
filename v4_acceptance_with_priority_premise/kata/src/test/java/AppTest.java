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

    //~~~ Impl

    private int valueOf(String numeral) {
        if (numeral == "") {
            return 0;
        }
        return 1;
    }
}