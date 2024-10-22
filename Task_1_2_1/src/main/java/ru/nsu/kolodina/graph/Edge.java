package ru.nsu.kolodina.graph;

/**
 *
 * @param name
 * @param vertexFrom
 * @param vertexTo
 * @param weight
 * @param <T>
 */
public record Edge<T>(T name, Vertex<T> vertexFrom, Vertex<T> vertexTo, int weight) {}

