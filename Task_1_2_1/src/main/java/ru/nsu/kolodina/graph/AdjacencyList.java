package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * adjacency list implementation of graph.
 *
 * @param <T> type of object
 */
public class AdjacencyList<T> implements Graph<T>{
    List<List<Edge<T>>> list;
    public List<Vertex<T>> vertices;
    List<Edge> edges;

    /**
     * class constructor.
     */
    public AdjacencyList() {
        list = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        List<Edge<T>> newArr = new ArrayList<>();
        list.add(newArr);
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        Vertex<T> v = vertices.stream().filter(ver -> ver.equals(vertex)).findAny().orElse(null);
        int idx = vertices.indexOf(v);
        list.remove(idx);
        vertices.remove(vertex);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        Vertex<T> v = vertices.stream().filter(ver -> ver.equals(edge.vertexFrom())).findAny().orElse(null);
        int from= vertices.indexOf(v);
        list.get(from).add(edge);
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        Vertex<T> v = vertices.stream().filter(ver -> ver.equals(edge.vertexFrom())).findAny().orElse(null);
        int from= vertices.indexOf(v);
        list.get(from).remove(edge);
        edges.remove(edge);
    }

    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        List<Vertex<T>> neighbors = new ArrayList<>();
        Vertex<T> v = vertices.stream().filter(ver -> ver.equals(vertex)).findAny().orElse(null);
        int idx= vertices.indexOf(v);
        for (int j = 0; j < list.get(idx).size(); j++) {
            Vertex neighbor = list.get(idx).get(j).vertexTo();
            neighbors.add(neighbor);
        }
        return neighbors;
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

    @Override
    public List<Vertex<T>> getVertices() {
        return vertices;
    }
}
