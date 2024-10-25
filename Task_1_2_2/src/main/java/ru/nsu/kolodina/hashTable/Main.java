package ru.nsu.kolodina.hashTable;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer, String> table = new HashTable();
        table.createHashTable();
        table.put(3, "three");
        table.put(4, "four");
        table.update(3, "три");
        table.delete(3, "три");
        table.put(5, "five");
        while (table.forEach() != null) {
            System.out.println(table.forEach());
        }
    }
}
