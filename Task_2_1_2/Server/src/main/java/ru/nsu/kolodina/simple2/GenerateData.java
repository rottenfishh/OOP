package ru.nsu.kolodina.simple2;

import org.json.JSONArray;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GenerateData {
//12403 24500
    public static int[] primeNumbersTill(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .filter(x -> isPrime(x)).boxed().mapToInt(x -> x).toArray();
    }
    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }

    public static void main(String[] args) {
        Random rand = new Random();
        /*for (int i = 0; i < 10; i++) {
            int[] arr0 = rand.ints(1000, 2, 1000000).toArray();
            JSONArray ja = JsonWriter.createJson(arr0);
            String name = "Array" + i + ".json";
            JsonWriter.writeToFile(ja, name);
        }*/
        int[] arr = primeNumbersTill(450000, 1000000);
        JSONArray ja = JsonWriter.createJson(arr);
        String name = "ArraySimpleBig.json";
        JsonWriter.writeToFile(ja, name);

    }
}
