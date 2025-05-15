package ru.nsu.kolodina.simple2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestMain {
    @Test
    public void test() {
        SimpleNumbers numbers = new SimpleNumbers();
        assertFalse(numbers.isSimple(6));
    }
}
