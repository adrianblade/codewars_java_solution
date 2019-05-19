package kyu8.multiply;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MultiplyTest {
    @Test
    public void testSomething() {
        Double multiply = Multiply.multiply(25d, 10d);
        assertThat(multiply, is(250d));
    }
}