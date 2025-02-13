package ru.nsu.kolodina.simple;

import java.util.List;

import static java.lang.Math.sqrt;

public class SimpleNumbers {
    public boolean isSimple(int number) {
        for (int i = 2; i <= sqrt(number) + 1; i++) {
            if (number % i == 0 && number != i) {
                return false;
            }
        }
        return true;
    }
    public boolean hasNotSimple(Integer[] numbersList, int step, int start, boolean multipleThreads) {
        boolean flag;
        if (multipleThreads) {
            flag = !Multithreading.hasNotSimple;
        } else {
            flag = true;
        }
        for (int i = start; i < numbersList.length && flag; i += step) {
            System.out.println(numbersList[i]);
            if (!isSimple(numbersList[i])) {
                return true;
            }
        }
        return false;
    }
}
