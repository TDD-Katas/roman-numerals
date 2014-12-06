import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void value_of_empty_is_zero() {
        assertThat(valueOf(""), is(0));
    }

    //~~~ Impl

    private int valueOf(String numeral) {
        return 0;
    }
}