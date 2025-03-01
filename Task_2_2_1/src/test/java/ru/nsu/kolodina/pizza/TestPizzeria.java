package ru.nsu.kolodina.pizza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * test the work of Pizzeria.
 */
public class TestPizzeria {
    private ByteArrayOutputStream outputStream;
    Pizzeria dominos;
    @BeforeEach
    public void setUp() {
        dominos = new Pizzeria();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
    @Test
    public void Test() throws InterruptedException {
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
