package ru.nsu.kolodina.hashTable;

import java.util.Iterator;

public class Main <K,V>{
    public static void main(String[] args) {
        HashTable<Integer, String> table = new HashTable();
        table.createHashTable();
        table.put(3, "three");
        table.put(4, "four");
        table.update(3, "три");
        table.delete(3, "три");
        table.put(5, "five");
        System.out.println(table.get(4));
        Iterator<Element<Integer, String>> iterator = table.iterator();
        System.out.println("Elements in the hash table:");
        while (iterator.hasNext()) {
            Element<Integer, String> element = iterator.next();
            System.out.println("Key: " + element.returnKey() + ", Value: " + element.returnValue());
        }
    }
}
