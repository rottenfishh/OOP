package ru.nsu.kolodina.HashTable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestHashTable {
    HashTable<String, String> table;

    @BeforeEach
    void setup() {
        table = new HashTable<>();
        table.createHashTable();
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
        HashTable<String, String> table2 = new HashTable<>();
        table2.createHashTable();
        table.put("eight", "восемь");
        table.put("three", "три");
        table.put("nine", "девять");
        table2.put("nine", "девять");
        table2.put("three", "три");
        table2.put("eight", "восемь");
        assertEquals(table, table2);
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
        String excepted = "key: eight value: восемь\n" +
                "key: three value: три\n" +
                "key: nine value: девять\n";
        assertEquals(excepted, table.toString());
    }
}
