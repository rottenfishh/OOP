package ru.nsu.kolodina.graph;

import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class AdjMatrix<T> implements Graph<T>{
    boolean hasCycle;
    Map<Vertex<T>, Integer> mark;
    List<Vertex<T>> topoSortList;
    List<List<Integer>> matrix;
    List<Vertex<T>> vertices;
    List<Edge> edges;
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
        List<Integer> newArr= new ArrayList<>();
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

    /**
     * input format: vertex count n.
     * n lines of vertex names
     * edge count m
     * m lines of: edge name vertexFrom name vertexTo name weight
     */
    @Override
    public void readFromFile(String pathName) {
        int n,m;
        String vertexName, edgeString;
        Vertex<T> vertex;
        Edge<T> edge;
        Vertex<T> from;
        Vertex<T> to;
        int weight;
        String edgeList[];
        try {
            File myObj = new File(pathName);
            Scanner scanner = new Scanner(myObj);
            n = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                vertexName = scanner.nextLine();
                vertex = new Vertex(vertexName);
                this.addVertex(vertex);
            }
            m = scanner.nextInt();
            scanner.nextLine();
            for (int j = 0; j < m; j++) {
                edgeString = scanner.nextLine();
                edgeList = edgeString.split(" ");
                from = new Vertex(edgeList[1]);
                to = new Vertex(edgeList[2]);
                weight = Integer.parseInt(edgeList[3]);
                edge = new Edge(edgeList[0], from, to, weight);
                this.addEdge(edge);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    void dfs(Vertex<T> v) {
        mark.put(v, 1);
        List<Vertex<T>> neighbors = this.getNeighbours(v);
        for (Vertex<T> vertex: neighbors) {
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
        return  Objects.equals(this.matrix, graph.matrix)
                && Objects.equals(this.vertices, graph.vertices)
                && Objects.equals(this.edges, graph.edges);
    }
}
