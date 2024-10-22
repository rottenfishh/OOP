package ru.nsu.kolodina.graph;

import java.util.List;

interface Algorithm<T> {
    List<Vertex<T>> perform(Graph<T> graph);
}
