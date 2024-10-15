package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

/**
 * adjacency list implementation of graph.
 *
 * @param <T> type of object
 */
public class AdjacencyList<T> implements Graph<T>, Algorithm<T> {
    boolean hasCycle;
    List<List<Edge<T>>> list;
    List<Vertex<T>> vertices;
    List<Edge> edges;
    private final Map<Vertex<T>, Integer> mark;
    private final List<Vertex<T>> topoSortList;

    /**
     * class constructor.
     */
    public AdjacencyList() {
        list = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        mark = new HashMap<>();
        hasCycle = false;
        topoSortList = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        List<Edge<T>> newArr = new ArrayList<>();
        list.add(newArr);
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        int idx = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(vertex)) {
                idx = i;
                break;
            }
        }
        list.remove(idx);
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        int from = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(edge.vertexFrom)) {
                from = i;
                break;
            }
        }
        list.get(from).add(edge);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        int from = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(edge.vertexFrom)) {
                from = i;
                break;
            }
        }
        list.get(from).remove(edge);
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        int idx = -1;
        List<Vertex<T>> neighbors = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(vertex)) {
                idx = i;
            }
        }
        for (int j = 0; j < list.get(idx).size(); j++) {
            Vertex neighbor = list.get(idx).get(j).vertexTo;
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    @Override
    public void dfs(Vertex<T> v) {
        mark.put(v, 1);
        List<Vertex<T>> neighbors = this.getNeighbours(v);
        for (Vertex<T> vertex : neighbors) {
            if (!mark.containsKey(vertex)) {
                if (hasCycle) {
                    return;
                }
                dfs(vertex);
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
    public List<Vertex<T>> topoSort() {
        Vertex<T> vertex;
        for (int i = 0; i < vertices.size(); i++) {
            vertex = vertices.get(i);
            if (!mark.containsKey(vertex)) {
                dfs(vertex);
                if (hasCycle) {
                    System.out.println("Graph has cycle, no toposort is possible");
                    return null;
                }
            }
        }
        Collections.reverse(topoSortList);
        return topoSortList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nAdjacency List:\n");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(list.get(i).toString()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(list, vertices, edges);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        AdjacencyList<?> graph = (AdjacencyList<?>) obj;
        return Objects.equals(this.list, graph.list)
                && Objects.equals(this.vertices, graph.vertices)
                && Objects.equals(this.edges, graph.edges);
    }
}
