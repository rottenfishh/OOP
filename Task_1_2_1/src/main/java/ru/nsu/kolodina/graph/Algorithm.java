package ru.nsu.kolodina.graph;

import java.util.List;

interface Algorithm<T> {
    void dfs(Vertex<T> v);

    List<Vertex<T>> topoSort();
}
