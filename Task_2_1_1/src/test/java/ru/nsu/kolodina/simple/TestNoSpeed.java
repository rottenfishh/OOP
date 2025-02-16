package ru.nsu.kolodina.simple;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test if it even works.
 */
public class TestNoSpeed {
    SimpleNumbers test;
    Multithreading calculate;
    Integer[] arr1;
    Integer[] arr2;

    @BeforeEach
    void setup() {
        test = new SimpleNumbers();
        calculate = new Multithreading();
        arr1 = new Integer[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
            6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        arr2 = new Integer[]{6, 8, 7, 13, 5, 9, 4};
    }

    @Test
    public void testMultithreading() throws InterruptedException {
        assertFalse(calculate.hasNotSimple(arr1, 10));
        assertTrue(calculate.hasNotSimple(arr2, 10));
    }

    @Test
    public void testOneThread() {
        assertFalse(test.hasNotSimpleOneThread(arr1));
        assertTrue(test.hasNotSimpleOneThread(arr2));
    }

    @Test
    public void testParallelStream() {
        assertFalse(calculate.useParallelStream(arr1));
        assertTrue(calculate.useParallelStream(arr2));
    }
}
