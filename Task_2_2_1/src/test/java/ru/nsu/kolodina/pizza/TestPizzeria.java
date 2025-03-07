package ru.nsu.kolodina.pizza;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * test the work of Pizzeria.
 */
public class TestPizzeria {
    private ByteArrayOutputStream outputStream;
    Pizzeria dominos;

    /**
     * set up Pizzeria and outputStream.
     */
    @BeforeEach
    public void setUp() {
        dominos = new Pizzeria();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void test() throws InterruptedException {
        dominos.startWork();
        dominos.endWork();
        String output = outputStream.toString();
        assertTrue(output.contains("ORDERED"));
        assertTrue(output.contains("TAKEN"));
        assertTrue(output.contains("BAKING"));
        assertTrue(output.contains("DELIVERING"));
        assertTrue(output.contains("DELIVERED"));
    }

    @Test
    public void testStorage() {
        Storage storage = new Storage(100);
        Order order = new Order(10);
        storage.putInStorage(order);
        Order orderOut = storage.getFromStorage(1).getLast();
        assertEquals(order.orderId, orderOut.orderId);
    }
}
