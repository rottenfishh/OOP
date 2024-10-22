package ru.nsu.kolodina.graph;

/**
 * record for edge implementation.
 *
 * @param name of edge
 * @param vertexFrom vertex edge comes from
 * @param vertexTo vertex edge comes to
 * @param weight of edge
 * @param <T> type of object
 */
public record Edge<T>(T name, Vertex<T> vertexFrom, Vertex<T> vertexTo, int weight) {
}

