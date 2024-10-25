package ru.nsu.kolodina.hashTable;

public record Element<K,V>(K key, V value){
    public K returnKey() {
        return key;
    }
    public V returnValue() {
        return value;
    }
};
