package ru.nsu.kolodina.hashTable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TableIterator<K, V> implements Iterator<Element<K, V>> {
    private final LinkedList<Element<K, V>>[] table;
    private int currentIndex;
    private Iterator<Element<K, V>> listIterator;

    public TableIterator(LinkedList<Element<K, V>>[] table, int capacity) {
        this.table = table;
        this.currentIndex = -1;
        advanceToNextNonEmptyList();
    }

    @Override
    public boolean hasNext() {
        return listIterator != null && listIterator.hasNext();
    }

    @Override
    public Element<K, V> next() {
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
}