package ru.nsu.kolodina.hashTable;

import java.util.*;

/// GOVNOKOD
public class HashTable<K,V>{

    private int size = 0;
    private int capacity = 128;
    private final double threshold = 0.75;
    LinkedList<Element<K, V>>[] table;
    Hash<K> hash;
    TableIterator<K,V> iterator;
    void createHashTable() {
        table = (LinkedList<Element<K, V>>[]) new LinkedList<?>[capacity];
        hash = new Hash<K>();
        iterator = new TableIterator<>(table, capacity);
    }

    void put(K key, V value) {
        int hashValue = hash.hashFunction(key,capacity);
        Element<K,V> el = new Element<>(key, value);
        if (table[hashValue] == null) {
            table[hashValue] = new LinkedList<>();
        }
        if (size > capacity * threshold) {
            table = Arrays.copyOf(table, capacity * 2);
            capacity *= 2;
            reHash();
        }
        table[hashValue].add(el);
        size++;
    }

    void delete(K key, V value) {
        int hashValue = hash.hashFunction(key,capacity);
        Element<K, V> el = new Element<>(key, value);
        table[hashValue].remove(el);
    }

    //@Nullable
    V get(K key) {
        Element<K,V> el;
        int hashValue = hash.hashFunction(key,capacity);
        if (table[hashValue].isEmpty()) {
            return null;
        } else {
            for (Element<K, V> kvElement : table[hashValue]) {
                el = kvElement;
                if (el.returnKey().equals(key)) {
                    return el.returnValue();
                }
            }
        }
        return null;
    }

    boolean containsValue(K key) {
        Element<K,V> el;
        int hashValue = hash.hashFunction(key,capacity);
        if (table[hashValue].isEmpty() || table[hashValue] == null) {
            return false;
        } else {
            for (Element<K, V> kvElement : table[hashValue]) {
                el = kvElement;
                if (el.returnKey().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }
    void update(K key, V value) {
        Element<K,V> el;
        Element<K,V> newEl = new Element<>(key,value);
        int hashValue = hash.hashFunction(key,capacity);
        if (table[hashValue].isEmpty() || table[hashValue] == null) {
            return;
        } else {
            for (Element<K, V> kvElement : table[hashValue]) {
                el = kvElement;
                if (el.returnKey().equals(key)) {
                    table[hashValue].remove(el);
                    table[hashValue].add(newEl);
                    return;
                }
            }
        }
    }
    Element<K,V> forEach() {
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    boolean equals() {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    void reHash() {
        Element<K,V> el;
        int hashValue;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                ListIterator<Element<K, V>> listIter = table[i].listIterator(0);
                while (listIter.hasNext()) {
                    el = listIter.next();
                    hashValue = hash.hashFunction(el.returnKey(), capacity);
                    if (hashValue != i) {
                        table[i].remove(el);
                        table[hashValue].add(el);
                    }
                }
            }
        }
    }
}
