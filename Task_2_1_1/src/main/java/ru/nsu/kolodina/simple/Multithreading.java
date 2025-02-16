package ru.nsu.kolodina.simple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * class using multiple threads to find non-prime.
 */
public class Multithreading {
    public static volatile AtomicBoolean flag = new AtomicBoolean(false);

    /**
     * main method. check if it has non-prime.
     *
     * @param numbers array with numbers
     * @param numOfThreads number of threads to run
     * @return true or false
     * @throws InterruptedException everything may happen
     */

    public boolean hasNotSimple(Integer[] numbers, int numOfThreads) throws InterruptedException {
        flag.set(false);
        if (numOfThreads > numbers.length) {
            numOfThreads = numbers.length - 1;
        }
        MyThread[] threads = new MyThread[numOfThreads];
        for (int i = 0; i < numOfThreads; i++) {
            threads[i] = new MyThread(numbers, numOfThreads, i);
            threads[i].start();
        }
        for (int i = 0; i < numOfThreads; i++) {
            threads[i].join();
        }
        return flag.get();
    }

    /**
     * find non-prime using parallelStream.
     *
     * @param arr array with numbers
     * @return true or false
     */
    public boolean useParallelStream(Integer[] arr) {
        SimpleNumbers test = new SimpleNumbers();
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        boolean result = (list.parallelStream().anyMatch(a -> !test.isSimple(a)));
        return result;
    }

    /**
     * creating class of thread to run.
     */
    public class MyThread extends Thread {
        Integer[] numbers;
        int numOfThreads;
        int id;

        /**
         * constructor.
         *
         * @param numbers array
         * @param numOfThreads yes
         * @param id id of thread
         */
        public MyThread(Integer[] numbers, int numOfThreads, int id) {
            this.numbers = numbers;
            this.numOfThreads = numOfThreads;
            this.id = id;
        }

        @Override
        public void run() {
            SimpleNumbers testSimple = new SimpleNumbers();
            int len = numbers.length / numOfThreads;
            int start = len * id;
            int end = (id == numOfThreads - 1)
                    ? numbers.length : len * (id + 1);  // Handle the last thread
            for (int i = start; i < end && !flag.get(); i++) {
                if (!testSimple.isSimple(numbers[i])) {
                    flag.set(true);
                }
            }
        }
    }
}