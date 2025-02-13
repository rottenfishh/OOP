package ru.nsu.kolodina.simple;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        SimpleNumbers test = new SimpleNumbers();
        Multithreading calculate = new Multithreading();
        Integer[] arr = {20319251, 6997901, 6997927, 6997937, 17858849, 6997967,
                6998009, 6998029, 6998039, 20165149, 6998051, 6998053};

        Integer[] arr2 = {6, 8, 7, 13, 5, 9, 4};
        List<Integer> list = new ArrayList<>(Arrays.asList(arr));
        boolean result = list.parallelStream().allMatch(test::isSimple);
        System.out.println(result);
    }
}