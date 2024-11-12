package ru.nsu.kolodina.strings;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BoyerMoore boyerMoore = new BoyerMoore();
        List<Integer> res = new ArrayList<>(boyerMoore.findInFile("file.txt", "бра"));
        System.out.println(res);
    }
}
