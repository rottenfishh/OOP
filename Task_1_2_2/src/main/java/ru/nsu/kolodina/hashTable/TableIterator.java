package ru.nsu.kolodina.hashTable;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class TableIterator<K,V> implements Iterator<Element<K,V>> {
    int current;
    int next = -1;
    LinkedList<Element<K, V>>[] table;
    int capacity;
    public TableIterator(LinkedList<Element<K, V>>[] tablePassed, int cap) {
        table = tablePassed;
        capacity = cap;
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                current = i;
                break;
            }
        }
    }
    public void changeCap(int cap) {
        capacity = cap;
    }

    public boolean hasNext() {
        for (int i = current; i < capacity; i++) {
            if (table[i] != null) {
                next = i;
                return true;
            }
        }
        return false;
    }
    public Element<K,V> next() {
        Element<K,V> el;
        if (table[next] != null) {
            ListIterator<Element<K, V>> listIter = table[next].listIterator(0);
            if (listIter.hasNext()) {
                el = listIter.next();
                return el;
            }
        }
        next++;
        return null;
    }
}
