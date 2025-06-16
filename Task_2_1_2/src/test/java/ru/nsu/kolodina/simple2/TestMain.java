package ru.nsu.kolodina.simple2;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

/**
 * needed only to build on git.
 */
public class TestMain {
    @Test
    public void test() {
        SimpleNumbers numbers = new SimpleNumbers();
        assertFalse(numbers.isSimple(6));
    }
}
