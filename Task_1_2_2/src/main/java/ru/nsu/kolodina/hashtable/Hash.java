package ru.nsu.kolodina.hashtable;

/**
 * class for hash function.
 *
 * @param <K> type of key
 */
public class Hash<K> {
    /**
     * hash function.
     *
     * @param key of element
     * @param size of hashtable
     * @return - hash
     */
    public int hashFunction(K key, int size) {
        return key.hashCode() % size;
    }
}
