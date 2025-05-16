package ru.nsu.kolodina.simple2;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * class for the base method and simple one-way go.
 */
public class SimpleNumbers {
    /**
     * method that checks if num is simple.
     *
     * @param number to check
     * @return true or false
     */
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
}