package ru.nsu.kolodina.simple;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing speed of different solutions.
 */
public class TestSpeed {
    SimpleNumbers test;
    Multithreading calculate;
    Integer[] arr1;
    Integer[] arrBig;
    Integer[] arrBig2;

    @BeforeEach
    void setup() {
        test = new SimpleNumbers();
        calculate = new Multithreading();
        arr1 = new Integer[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
            6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        arrBig = new Integer[1000000];
        arrBig2 = new Integer[1000000];
        Arrays.fill(arrBig2, 20165149);
        Arrays.fill(arrBig, 20319251);
        arrBig[1000000 - 1] = 4;
    }

    @Test
    public void testOneThread() {
        long startTime = System.nanoTime();
        assertTrue(test.hasNotSimpleOneThread(arrBig));
        assertFalse(test.hasNotSimpleOneThread(arrBig2));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("one thread " + time / 1_000_000 + " miliseconds");
    }

    @Test
    public void testMultithreading2Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertTrue(calculate.hasNotSimple(arrBig, 2));
        assertFalse(calculate.hasNotSimple(arrBig2, 2));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("2 threads " + time / 1_000_000 + " miliseconds");
    }

    @Test
    public void testMultithreading4Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertTrue(calculate.hasNotSimple(arrBig, 4));
        assertFalse(calculate.hasNotSimple(arrBig2, 4));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("4 threads " + time / 1_000_000 + " miliseconds");
    }

    @Test
    public void testMultithreading8Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertTrue(calculate.hasNotSimple(arrBig, 8));
        assertFalse(calculate.hasNotSimple(arrBig2, 8));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("8 threads " + time / 1_000_000 + " miliseconds");
    }

    @Test
    public void testMultithreading12Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertTrue(calculate.hasNotSimple(arrBig, 12));
        assertFalse(calculate.hasNotSimple(arrBig2, 12));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("12 threads " + time / 1_000_000 + " miliseconds");
    }

    @Test
    public void testMultithreading20Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertTrue(calculate.hasNotSimple(arrBig, 20));
        assertFalse(calculate.hasNotSimple(arrBig2, 20));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("20 threads " + time / 1_000_000 + " miliseconds");
    }

    @Test
    public void testParallelStream() {
        long startTime = System.nanoTime();
        assertTrue(calculate.useParallelStream(arrBig));
        assertFalse(calculate.useParallelStream(arrBig2));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("parallelStream " + time / 1_000_000 + " miliseconds");
    }
}
