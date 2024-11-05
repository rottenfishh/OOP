package ru.nsu.kolodina.HashTable;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * class for tests.
 */
public class TestHashTable {
    HashTable<String, String> table;
    HashTable<Double, Integer> table2;
    @BeforeEach
    void setup() {
        table = new HashTable<>();
        table.createHashTable();
        table2 = new HashTable<>();
        table2.createHashTable();
    }

    @Test
    void testPutAndGet() {
        table.put("three", "три");
        table.put("six", "шесть");
        assertEquals("три", table.get("three"));
        assertEquals("шесть", table.get("six"));
    }

    @Test
    void testContains() {
        table.put("four", "четыре");
        assertTrue(table.containsValue("four"));
        table.delete("four", "четыре");
        assertFalse(table.containsValue("four"));
    }

    @Test
    void updateTest() {
        table.put("seven", "семь");
        assertEquals("семь", table.get("seven"));
        table.update("seven", "восемь");
        assertEquals("восемь", table.get("seven"));
    }

    @Test
    void equalsTest() {
        HashTable<String, String> tableTest = new HashTable<>();
        tableTest.createHashTable();
        table.put("eight", "восемь");
        table.put("three", "три");
        table.put("nine", "девять");
        tableTest.put("nine", "девять");
        tableTest.put("three", "три");
        tableTest.put("eight", "восемь");
        assertEquals(table, tableTest);
    }

    @Test
    void iteratorTest() {
        table.put("eight", "восемь");
        table.put("three", "три");
        table.put("nine", "девять");
        List<Element<String, String>> list = new ArrayList<>();
        Iterator<Element<String, String>> iterator = table.iterator();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        Element<String, String> el1 = new Element<>("three", "три");
        Element<String, String> el2 = new Element<>("nine", "девять");
        Element<String, String> el3 = new Element<>("eight", "восемь");
        assertTrue(list.contains(el1));
        assertTrue(list.contains(el2));
        assertTrue(list.contains(el3));
    }

    @Test
    void toStringTest() {
        table.put("eight", "восемь");
        table.put("three", "три");
        table.put("nine", "девять");
        String excepted = "key: eight value: восемь\n"
                + "key: three value: три\n"
                + "key: nine value: девять\n";
        assertEquals(excepted, table.toString());
    }

    @Test
    void collisionTest() {
        table.put("Aa", "first");
        table.put("BB", "second");
        assertEquals("Aa".hashCode(), "BB".hashCode());
        assertEquals("first", table.get("Aa"));
        assertEquals("second", table.get("BB"));
    }

    @Test
    void concurrentModificationTest() {
        table.put("One", "один");
        table.put("Two", "два");
        table.put("Three", "три");
        Iterator<Element<String, String>> iterator = table.iterator();
        assertThrows(ConcurrentModificationException.class, () -> {
            while (iterator.hasNext()) {
                iterator.next();
                table.put("THROW", "EXCEPTION");
            }
        });
    }

    @Test
    void testSecondTable() {
        table2.put(3.424, 3);
        table2.put(2.651, 2);
        assertEquals(3, table2.get(3.424));
        assertEquals(2, table2.get(2.651));
        table2.update(3.424, 4);
        assertEquals(4, table2.get(3.424));
        assertTrue(table2.containsValue(2.651));
    }
    @Test
    void testEquals2() {
        HashTable<Double, Integer> tableTest = new HashTable<>();
        tableTest.createHashTable();
        table2.put(2.333, 2);
        table2.put(100.24, 100);
        tableTest.put(2.333, 2);
        tableTest.put(100.24, 100);
        assertTrue(table2.equals(tableTest));
    }
}