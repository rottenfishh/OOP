package ru.nsu.kolodina.simple2;

import org.json.JSONArray;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestServer {

    @Test
    public void testJson() {
        int[] arr = {1, 2, 3, 4, 5};
        JSONArray ja = JsonWriter.createJson(arr);
        String excepted = "[1,2,3,4,5]";
        assertEquals(excepted, ja.toString());
    }

    @Test
    public void testJson2() {
        String name = "test1.json";
        JSONArray ja = JsonReader.read(name);
        String excepted = "[1,2,3,4,5]";
        assertEquals(excepted, ja.toString());
    }

    @Test
    public void testWithoutClients() {
        String name = "test1.json";
        JSONArray ja = JsonReader.read(name);
        Server server = new Server(4444);
        boolean res = server.runServer(ja);
        assertFalse(res);
    }
}
