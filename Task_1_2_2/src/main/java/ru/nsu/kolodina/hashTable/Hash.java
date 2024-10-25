package ru.nsu.kolodina.hashTable;

public class Hash<K> {
    public int hashFunction(K key, int size) {
        return key.hashCode() % size;
    }
}
