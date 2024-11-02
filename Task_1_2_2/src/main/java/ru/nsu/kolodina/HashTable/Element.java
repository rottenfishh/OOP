package ru.nsu.kolodina.HashTable;

/**
 * record for element of hashtable.
 *
 * @param key of element
 * @param value of element
 * @param <K> type of key
 * @param <V> type of element
 */
public record Element<K, V>(K key, V value) {

    /**
     * return key
     *
     * @return key
     */
    public K returnKey() {
        return key;
    }

    /**
     * return value
     *
     * @return value
     */
    public V returnValue() {
        return value;
    }
}
