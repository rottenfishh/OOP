package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

/**
 * class implementing toposort.
 *
 * @param <T> type of object
 */
public class TopoSort<T> implements Algorithm<T> {
    private final Map<Vertex<T>, Integer> mark;
    private final List<Vertex<T>> topoSortList;
    boolean hasCycle;

    /**
     * constructor for class.
     */
    public TopoSort() {
        mark = new HashMap<>();
        hasCycle = false;
        topoSortList = new ArrayList<>();
    }

    /**
     * dfs implemetation to use in toposort.
     *
     * @param graph we use dfs on
     * @param v vertex we start dfs from
     */
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
