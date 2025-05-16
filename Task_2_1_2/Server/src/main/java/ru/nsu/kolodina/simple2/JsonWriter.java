package ru.nsu.kolodina.simple2;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * writing jsons.
 */
public class JsonWriter {

    /**
     * creating jsons from arrays.
     *
     * @param arr to convert
     * @return converted JSONarr
     */
    public static JSONArray createJson(int[] arr) {
        JSONArray ja = new JSONArray();
        for (int i = 0; i < arr.length; i++) {
            ja.put(arr[i]);
        }
        return ja;
    }

    /**
     * write json to file.
     *
     * @param ja       to write
     * @param fileName to save
     */
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
