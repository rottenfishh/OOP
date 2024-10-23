package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * incidence matrix implementation of graph.
 *
 * @param <T> type of object
 */
public class IncidenceMatrix<T> implements Graph<T> {
    List<List<Integer>> matrix;
    List<Vertex<T>> vertices;
    List<Edge<T>> edges;

    /**
     * class constructor.
     */
    public IncidenceMatrix() {
        matrix = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
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
        int idx = vertices.indexOf(vertex);
        if (idx == -1) {
            return;
        }
        matrix.remove(idx);
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<T> vertex = vertices.get(i);
            if (vertex.equals(edge.vertexFrom()) || vertex.equals(edge.vertexTo())) {
                matrix.get(i).add(edge.weight());
            } else {
                matrix.get(i).add(0);
            }
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        int idx = edges.indexOf(edge);
        for (int j = 0; j < vertices.size(); j++) {
            matrix.get(j).remove(idx);
        }
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        List<Vertex<T>> neighbors = new ArrayList<>();
        int idx = vertices.indexOf(vertex);

        for (int j = 0; j < edges.size(); j++) {
            if (matrix.get(idx).get(j) != 0) {
                Edge<T> edge = edges.get(j);
                if (!edge.vertexTo().equals(vertex)) {
                    neighbors.add(edge.vertexTo());
                }
            }
        }
        return neighbors;
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

    @Override
    public List<Vertex<T>> getVertices() {
        return vertices;
    }
}
