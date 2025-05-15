package ru.nsu.kolodina.simple2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * not much to test for client without server.
 */
public class TestClient {

    @Test
    public void testClient() {
        SimpleNumbers test = new SimpleNumbers();
        assertTrue(test.isSimple(5));
    }
}
