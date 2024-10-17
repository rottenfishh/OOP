package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.jetbrains.annotations.Nullable;

/**
 * adjacency matrix implementation of graph.
 *
 * @param <T> type of object
 */
public class AdjMatrix<T> implements Graph<T>, Algorithm<T> {
    boolean hasCycle;
    Map<Vertex<T>, Integer> mark;
    List<Vertex<T>> topoSortList;
    List<List<Integer>> matrix;
    List<Vertex<T>> vertices;
    List<Edge> edges;

    /**
     * class constructor.
     */
    public AdjMatrix() {
        matrix = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
        topoSortList = new ArrayList<>();
        mark = new HashMap<>();
        hasCycle = false;
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        List<Integer> newArr = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            newArr.add(0);
        }
        int size = vertices.size() - 1;
        for (int i = 0; i < size; i++) {
            matrix.get(i).add(0);
        }
        matrix.add(newArr);
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
        int size = vertices.size() - 1;
        for (int i = 0; i < size; i++) {
            matrix.get(i).remove(idx);
        }
        matrix.remove(idx);
        vertices.remove(idx);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        int from = -1;
        int to = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(edge.vertexFrom)) {
                from = i;
            }
            if (vertices.get(i).equals(edge.vertexTo)) {
                to = i;
            }
            if (from != -1 && to != -1) {
                break;
            }
        }
        matrix.get(from).set(to, edge.weight);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        int from = -1;
        int to = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(edge.vertexFrom)) {
                from = i;
            }
            if (vertices.get(i).equals(edge.vertexTo)) {
                to = i;
            }
            if (from != -1 && to != -1) {
                break;
            }
        }
        matrix.get(from).set(to, 0);
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        List<Vertex<T>> neighbors = new ArrayList<>();
        int idx = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(vertex)) {
                idx = i;
                break;
            }
        }
        for (int i = 0; i < vertices.size(); i++) {
            if (matrix.get(idx).get(i) != 0) {
                neighbors.add(vertices.get(i));
            }
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
        sb.append("\nAdjacency Matrix:\n");
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                sb.append(matrix.get(i).get(j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(matrix, vertices, edges);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        AdjMatrix<?> graph = (AdjMatrix<?>) obj;
        return Objects.equals(this.matrix, graph.matrix)
                && Objects.equals(this.vertices, graph.vertices)
                && Objects.equals(this.edges, graph.edges);
    }
}
