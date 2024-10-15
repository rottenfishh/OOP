package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Map;
import org.jetbrains.annotations.Nullable;

public class IncidenceMatrix<T> implements Graph<T>, Algorithm<T> {
    boolean hasCycle;
    Map<Vertex<T>, Integer> mark;
    List<Vertex<T>> topoSortList;
    List<List<Integer>> matrix;
    List<Vertex<T>> vertices;
    List<Edge> edges;

    public IncidenceMatrix() {
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
        for (int i = 0; i < edges.size(); i++) {
            newArr.add(0);
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
        matrix.remove(idx);
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<T> vertex = vertices.get(i);
            if (vertex.equals(edge.vertexFrom) || vertex.equals(edge.vertexTo)) {
                matrix.get(i).add(edge.weight);
            } else {
                matrix.get(i).add(0);
            }
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        int idx = -1;
        for (int i = 0; i < edges.size(); i++) {
            if (edges.get(i).equals(edge)) {
                idx = i;
                break;
            }
        }
        for (int j = 0; j < vertices.size(); j++) {
            matrix.get(j).remove(idx);
        }
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
        for (int j = 0; j < edges.size(); j++) {
            if (matrix.get(idx).get(j) != 0) {
                Edge edge = edges.get(j);
                if (!edge.vertexTo.equals(vertex)) {
                    neighbors.add(edge.vertexTo);
                }
            }
        }
        return neighbors;
    }

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
        hasCycle = false;
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
        sb.append("\nIncidence Matrix:\n");
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < matrix.get(i).size(); j++) {
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
        IncidenceMatrix<?> graph = (IncidenceMatrix<?>) obj;
        return Objects.equals(this.matrix, graph.matrix)
                && Objects.equals(this.vertices, graph.vertices)
                && Objects.equals(this.edges, graph.edges);
    }
}
