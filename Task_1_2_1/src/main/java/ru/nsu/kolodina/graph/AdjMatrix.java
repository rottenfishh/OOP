package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * adjacency matrix implementation of graph.
 *
 * @param <T> type of object
 */
public class AdjMatrix<T> implements Graph<T> {
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
        int idx = vertices.indexOf(vertex);
        if (idx == -1) {
            return;
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
        int idxFrom = vertices.indexOf(edge.vertexFrom());
        int idxTo = vertices.indexOf(edge.vertexTo());
        matrix.get(idxFrom).set(idxTo, edge.weight());
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        int idxFrom = vertices.indexOf(edge.vertexFrom());
        int idxTo = vertices.indexOf(edge.vertexFrom());
        matrix.get(idxFrom).set(idxTo, 0);
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        List<Vertex<T>> neighbors = new ArrayList<>();
        int idx = vertices.indexOf(vertex);

        for (int i = 0; i < vertices.size(); i++) {
            if (matrix.get(idx).get(i) != 0) {
                neighbors.add(vertices.get(i));
            }
        }
        return neighbors;
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

    @Override
    public List<Vertex<T>> getVertices() {
        return vertices;
    }
}
