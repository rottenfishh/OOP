package ru.nsu.kolodina.simple2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonWriter {
    public static JSONArray createJson(int[] arr) {
        JSONArray ja = new JSONArray();
        for (int i = 0; i < arr.length; i++) {
            ja.put(arr[i]);
        }
        return ja;
    }
    public static void writeToFile(JSONArray ja, String fileName) {
        JSONObject object = new JSONObject();
        object.put("data", ja);
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName);
            fw.write(object.toString(2));
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
