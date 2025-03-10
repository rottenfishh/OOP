package ru.nsu.kolodina.hashtable;

import static java.lang.Math.abs;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * implementing hashtable.
 *
 * @param <K> type of key
 * @param <V> type of value
 */
public class HashTable<K, V> implements Iterable<Element<K, V>> {
    private static final double THRESHOLD = 0.75;
    private LinkedList<Element<K, V>>[] table;
    private int size = 0;
    private int capacity = 128;
    private int modCount = 0;

    /**
     * creating hashtable: initializing array and creating instances of hash and iterator.
     */
    void createHashTable() {
        table = (LinkedList<Element<K, V>>[]) new LinkedList<?>[capacity];
    }

    /**
     * function for producing hash.
     *
     * @param key of table entry
     * @param size of hashtable
     * @return hash
     */
    public int hashFunction(K key, int size) {
        return abs(key.hashCode()) % size;
    }
    
    /**
     * putting element in hashtable.
     *
     * @param key of element
     * @param value of element
     */
    void put(K key, V value) {
        int hashValue = hashFunction(key, capacity);
        Element<K, V> el = new Element<>(key, value);
        if (table[hashValue] == null) {
            table[hashValue] = new LinkedList<>();
        }
        if (size > capacity * THRESHOLD) {
            table = Arrays.copyOf(table, capacity * 2);
            capacity *= 2;
            reHash();
        }
        table[hashValue].add(el);
        size++;
        modCount++;
    }

    /**
     * deleting element from hashtable.
     *
     * @param key of element
     * @param value of element
     */
    void delete(K key, V value) {
        int hashValue = hashFunction(key, capacity);
        Element<K, V> el = new Element<>(key, value);
        if (table[hashValue] == null || table[hashValue].isEmpty()) {
            return;
        }
        table[hashValue].remove(el);
        modCount++;
    }

    /**
     * getting element from hashtable by key.
     *
     * @param key of element
     * @return value of element retrieved by key
     */
    @Nullable
    V get(K key) {
        Element<K, V> el;
        int hashValue = hashFunction(key, capacity);
        if (table[hashValue] == null || table[hashValue].isEmpty()) {
            return null;
        } else {
            for (Element<K, V> kvElement : table[hashValue]) {
                el = kvElement;
                if (el.key().equals(key)) {
                    return el.value();
                }
            }
        }
        return null;
    }

    /**
     * check if there hashtable contains value with given key.
     *
     * @param key of value we want to check
     * @return true or false
     */
    boolean containsValue(K key) {
        Element<K, V> el;
        int hashValue = hashFunction(key, capacity);
        if (table[hashValue] == null || table[hashValue].isEmpty()) {
            return false;
        } else {
            for (Element<K, V> kvElement : table[hashValue]) {
                el = kvElement;
                if (el.key().equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * updating value of element by key.
     *
     * @param key of element to update
     * @param value - new value of element
     */
    void update(K key, V value) {
        Element<K, V> el;
        Element<K, V> newEl = new Element<>(key, value);
        int hashValue = hashFunction(key, capacity);
        if (table[hashValue] == null || table[hashValue].isEmpty()) {
            return;
        } else {
            for (Element<K, V> kvElement : table[hashValue]) {
                el = kvElement;
                if (el.key().equals(key)) {
                    table[hashValue].remove(el);
                    table[hashValue].add(newEl);
                    return;
                }
            }
        }
        modCount++;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        HashTable<K, V> objTable = (HashTable<K, V>) obj;

        if (this.size != objTable.size) {
            return false;
        }

        for (LinkedList<Element<K, V>> list : this.table) {
            if (list != null) {
                for (Element<K, V> el : list) {
                    if (objTable.get(el.key()) == null
                            || objTable.get(el.key()) != el.value()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (LinkedList<Element<K, V>> list : this.table) {
            if (list != null) {
                for (Element<K, V> el : list) {
                    sb.append("key: ").append(el.key()).append(" ")
                            .append("value: ").append(el.value()).append("\n");
                }
            }
        }
        return sb.toString();
    }

    /**
     * function to rehash array if its resized.
     */
    void reHash() {
        Element<K, V> el;
        int hashValue;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                ListIterator<Element<K, V>> listIter = table[i].listIterator(0);
                while (listIter.hasNext()) {
                    el = listIter.next();
                    hashValue = hashFunction(el.key(), capacity);
                    if (hashValue != i) {
                        table[i].remove(el);
                        table[hashValue].add(el);
                    }
                }
            }
        }
    }

    @NotNull
    @Override
    public Iterator<Element<K, V>> iterator() {
        return new TableIterator();
    }

    class TableIterator implements Iterator<Element<K, V>> {
        private final int prevModCount;
        private int currentIndex;
        private Iterator<Element<K, V>> listIterator;

        public TableIterator() {
            this.currentIndex = -1;
            this.prevModCount = modCount;
            advanceToNextNonEmptyList();
        }

        @Override
        public boolean hasNext() {
            return listIterator != null && listIterator.hasNext();
        }

        @Override
        public Element<K, V> next() {
            checkForConcurrentModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Element<K, V> element = listIterator.next();
            if (!listIterator.hasNext()) {
                advanceToNextNonEmptyList();
            }
            return element;
        }

        private void advanceToNextNonEmptyList() {
            checkForConcurrentModification();
            listIterator = null;
            currentIndex++;
            while (currentIndex < table.length) {
                if (table[currentIndex] != null && !table[currentIndex].isEmpty()) {
                    listIterator = table[currentIndex].iterator();
                    break;
                }
                currentIndex++;
            }
        }

        private void checkForConcurrentModification() {
            if (prevModCount != modCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
