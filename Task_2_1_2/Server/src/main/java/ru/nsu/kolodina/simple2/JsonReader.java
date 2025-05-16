package ru.nsu.kolodina.simple2;

import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * reading jsons.
 */
public class JsonReader {
    /**
     * read json from file in resources.
     *
     * @param resourceName file name
     * @return JSONarr
     */
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
