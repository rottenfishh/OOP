package ru.nsu.kolodina.simple;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class SimpleNumbers {
    public boolean isSimple(int number) {
        if (abs(number) < 2) {
            return false;
        }
        for (int i = 2; i <= sqrt(number); i++) {
            if (number % i == 0 && number != i) {
                return false;
            }
        }
        return true;
    }

    public boolean hasNotSimpleOneThread(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (!isSimple(arr[i])) {
                return true;
            }
        }
        return false;
    }
}