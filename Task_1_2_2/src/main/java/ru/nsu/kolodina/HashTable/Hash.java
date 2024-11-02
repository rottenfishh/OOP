package ru.nsu.kolodina.HashTable;

public class Hash<K> {
    public int hashFunction(K key, int size) {
        return key.hashCode() % size;
    }
}
