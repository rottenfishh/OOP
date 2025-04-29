package ru.nsu.kolodina.simple2;

import org.json.JSONArray;

import java.io.FileWriter;

public class JsonWriter {

    public static JSONArray createJson(int[] arr) {
        JSONArray ja = new JSONArray();
        for (int i = 0; i < arr.length; i++) {
            ja.put(arr[i]);
        }
        return ja;
    }
}
