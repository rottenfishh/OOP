package ru.nsu.kolodina.graph;

/**
 * class for vertex implementation.
 *
 * @param <T> type of object
 */
public class Vertex<T> {
    public final T name;

    public Vertex(T v) {
        this.name = v;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Vertex<T> vertex = (Vertex<T>) object;
        return this.name.equals(vertex.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
