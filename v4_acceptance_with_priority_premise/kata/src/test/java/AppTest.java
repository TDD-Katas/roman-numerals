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


//    L	50
//    C	100
//    D	500
//    M 1000

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

        return 10;
    }
}