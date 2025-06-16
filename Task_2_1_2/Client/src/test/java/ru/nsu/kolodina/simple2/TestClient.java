package ru.nsu.kolodina.simple2;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

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
