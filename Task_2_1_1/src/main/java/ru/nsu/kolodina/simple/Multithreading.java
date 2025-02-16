package ru.nsu.kolodina.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class Multithreading {
    public static volatile AtomicBoolean flag = new AtomicBoolean(false);

    public boolean hasNotSimple(Integer[] numbers, int numOfThreads) throws InterruptedException {
        flag.set(false);
        if (numOfThreads > numbers.length) {
            numOfThreads = numbers.length - 1;
        }
        thread[] threads = new thread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new thread(numbers, numOfThreads, i);
            threads[i].start();
        }
        for (int i = 0; i < numOfThreads; i++) {
            threads[i].join();
        }
        return flag.get();
    }

    public boolean useParallelStream(Integer[] arr) {
        SimpleNumbers test = new SimpleNumbers();
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        boolean result = (list.parallelStream().anyMatch(a -> !test.isSimple(a)));
        return result;
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
            SimpleNumbers testSimple = new SimpleNumbers();
            int len = numbers.length / numOfThreads;
            int start = len * id;
            int end = (id == numOfThreads - 1) ? numbers.length : len * (id + 1);  // Handle the last thread
            for (int i = start; i < end && !flag.get(); i++) {
                if (!testSimple.isSimple(numbers[i])) {
                    flag.set(true);
                }
            }
        }
    }
}