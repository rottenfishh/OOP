package ru.nsu.kolodina.simple;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSpeed {
    SimpleNumbers test;
    Multithreading calculate;
    Integer[] arr1;
    Integer[] arr2;
    Integer[] arrBig;
    @BeforeEach
    void setup() {
        test = new SimpleNumbers();
        calculate = new Multithreading();
        arr1 = new Integer[]{20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};
        arr2 = new Integer[] {6, 8, 7, 13, 5, 9, 4};
        arrBig = new Integer[10000000];
        Arrays.fill(arrBig, 3);
        arrBig[10000000 - 1] = 4;
    }
    @Test
    public void testMultithreading2Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertFalse(calculate.hasNotSimple(arr1, 2));
        assertTrue(calculate.hasNotSimple(arr2, 2));
        assertTrue(calculate.hasNotSimple(arrBig, 2));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("2 threads " + time /  1_000_000 + " miliseconds");
    }
    @Test
    public void testMultithreading4Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertFalse(calculate.hasNotSimple(arr1, 4));
        assertTrue(calculate.hasNotSimple(arr2, 4));
        assertTrue(calculate.hasNotSimple(arrBig, 4));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("4 threads " + time /  1_000_000  + " miliseconds");
    }
    @Test
    public void testMultithreading8Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertFalse(calculate.hasNotSimple(arr1, 8));
        assertTrue(calculate.hasNotSimple(arr2, 8));
        assertTrue(calculate.hasNotSimple(arrBig, 8));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("8 threads " + time /  1_000_000 + " miliseconds");
    }
    @Test
    public void testMultithreading20Threads() throws InterruptedException {
        long startTime = System.nanoTime();
        assertFalse(calculate.hasNotSimple(arr1, 20));
        assertTrue(calculate.hasNotSimple(arr2, 20));
        assertTrue(calculate.hasNotSimple(arrBig, 20));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("20 threads " + time /  1_000_000  + " miliseconds");
    }

    @Test
    public void testOneThread() {
        long startTime = System.nanoTime();
        assertFalse(test.hasNotSimpleOneThread(arr1));
        assertTrue(test.hasNotSimpleOneThread(arr2));
        assertTrue(test.hasNotSimpleOneThread(arrBig));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("one thread " + time /  1_000_000  + " miliseconds");
    }

    @Test
    public void testParallelStream() {
        long startTime = System.nanoTime();
        assertFalse(calculate.useParallelStream(arr1));
        assertTrue(calculate.useParallelStream(arr2));
        assertTrue(calculate.useParallelStream(arrBig));
        long endTime = System.nanoTime();
        long time = endTime - startTime;
        System.out.println("parallelStream " + time / 1_000_000  + " miliseconds");
    }
}
