package ru.nsu.kolodina.simple2;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    public static JSONArray read(String resourceName) {
        InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(resourceName);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resourceName);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        return object.getJSONArray("data");
    }
}
