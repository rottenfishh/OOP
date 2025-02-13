package ru.nsu.kolodina.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Multithreading {
    public static volatile boolean hasNotSimple = false;
    public boolean hasNotSimple(Integer[] numbers, int numOfThreads) throws InterruptedException {
        if (numOfThreads > numbers.length) {
            numOfThreads = numbers.length;
        }
        thread[] threads = new thread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new thread(numbers, numOfThreads, i);
            threads[i].start();
        }
        for (int i = 0; i < numOfThreads; i++) {
            threads[i].join();
            return hasNotSimple;
        }
        return false;
    }
    public class thread extends Thread {
        Integer[] numbers;
        int numOfThreads;
        int id;
        public thread(Integer[] numbers, int numOfThreads, int id) {
            this.numbers = numbers;
            this.numOfThreads = numOfThreads;
            this.id = id;
        }
        @Override
        public void run() {
            SimpleNumbers test = new SimpleNumbers();
            boolean result = test.hasNotSimple(numbers, numOfThreads, id, true);
            if (!hasNotSimple) {
                hasNotSimple = result;
            }
        }
    }
    public boolean useParallelStream(Integer[] arr) {
        SimpleNumbers test = new SimpleNumbers();
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        boolean result = !(list.parallelStream().allMatch(test::isSimple));
        return result;
    }
}
