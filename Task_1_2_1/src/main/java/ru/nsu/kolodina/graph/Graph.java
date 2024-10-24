package ru.nsu.kolodina.graph;

import java.util.List;

/**
 * interface for graphs.
 *
 * @param <T> type of object
 */
public interface Graph<T> {

    void addVertex(Vertex<T> vertex);

    void removeVertex(Vertex<T> vertex);

    void addEdge(Edge<T> edge);

    void removeEdge(Edge<T> edge);

    List<Vertex<T>> getNeighbours(Vertex<T> vertex);

    List<Vertex<T>> getVertices();
}
