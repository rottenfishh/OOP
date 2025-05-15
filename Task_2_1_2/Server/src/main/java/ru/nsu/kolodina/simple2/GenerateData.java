package ru.nsu.kolodina.simple2;

import java.util.Random;
import java.util.stream.IntStream;
import org.json.JSONArray;

/**
 * generating data for tests.
 */
public class GenerateData {
    /**
     * generate prime numbers.
     *
     * @param start number
     * @param end   number
     * @return array
     */
    public static int[] primeNumbersTill(int start, int end) {
        return IntStream.rangeClosed(start, end)
                .filter(x -> isPrime(x)).boxed().mapToInt(x -> x).toArray();
    }

    private static boolean isPrime(int number) {
        return IntStream.rangeClosed(2, (int) (Math.sqrt(number)))
                .allMatch(n -> number % n != 0);
    }

    /**
     * generate data and write to file in json.
     */
    public void generateData() {
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            int[] arr0 = rand.ints(1000, 2, 1000000).toArray();
            JSONArray ja = JsonWriter.createJson(arr0);
            String name = "Array" + i + ".json";
            JsonWriter.writeToFile(ja, name);
        }
        int[] arr = primeNumbersTill(450000, 1000000);
        JSONArray ja = JsonWriter.createJson(arr);
        String name = "ArraySimpleBig.json";
        JsonWriter.writeToFile(ja, name);

    }
}
