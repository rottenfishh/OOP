package ru.nsu.kolodina.hashtable;

/**
 * record for element of hashtable.
 *
 * @param key of element
 * @param value of element
 * @param <K> type of key
 * @param <V> type of element
 */
public record Element<K, V>(K key, V value) {
}
