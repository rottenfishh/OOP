package ru.nsu.kolodina.graph;

import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TopoSort<T> implements Algorithm<T> {
    private final Map<Vertex<T>, Integer> mark;
    private final List<Vertex<T>> topoSortList;
    boolean hasCycle;

    public TopoSort() {
        mark = new HashMap<>();
        hasCycle = false;
        topoSortList = new ArrayList<>();
    }

    public void dfs(Graph<T> graph, Vertex<T> v) {
        mark.put(v, 1);
        List<Vertex<T>> neighbors = graph.getNeighbours(v);
        for (Vertex<T> vertex : neighbors) {
            if (!mark.containsKey(vertex)) {
                if (hasCycle) {
                    return;
                }
                dfs(graph, vertex);
            }
            if (mark.get(vertex) == 1 && !hasCycle && !v.equals(vertex)) {
                hasCycle = true;
                return;
            }
        }
        mark.replace(v, 2);
        topoSortList.add(v);
    }

    @Override
    @Nullable
    public List<Vertex<T>> perform(Graph<T> graph) {
        List<Vertex<T>> vertices = graph.getVertices();
        Vertex<T> vertex;
        for (int i = 0; i < vertices.size(); i++) {
            vertex = vertices.get(i);
            if (!mark.containsKey(vertex)) {
                dfs(graph, vertex);
                if (hasCycle) {
                    System.out.println("Graph has cycle, no toposort is possible");
                    return null;
                }
            }
        }
        Collections.reverse(topoSortList);
        return topoSortList;
    }

}
