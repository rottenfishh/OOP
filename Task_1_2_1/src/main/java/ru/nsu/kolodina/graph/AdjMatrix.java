package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdjMatrix<T> implements Graph<T>{

    List<List<Boolean>> matrix;
    List<Vertex<T>> vertices;
    List<Edge> edges;
    public AdjMatrix() {
        matrix = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }
    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        List<Boolean> newArr= new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++) {
            newArr.add(false);
        }
        int size = vertices.size() - 1;
        for (int i = 0; i < size; i++) {
            matrix.get(i).add(false);
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
        matrix.get(from).set(to, true);
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
        matrix.get(from).set(to, false);
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
            if (matrix.get(idx).get(i) == true) {
                neighbors.add(vertices.get(i));
            }
            if (matrix.get(i).get(idx) == true) {
                neighbors.add(vertices.get(i));
            }
        }
        return neighbors;
    }

    /**
     * input format: vertex count n.
     * n lines of vertex names
     * edge count m
     * m lines of edge name vertexFrom name vertexTo name
     */
    @Override
    public void readFromFile() {
        int n,m;
        String vertexName, edgeString;
        Vertex<T> vertex;
        Edge<T> edge;
        Vertex<T> from;
        Vertex<T> to;
        String edgeList[];
        try {
            File myObj = new File("src/main/resources/readGraph.txt");
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
                edge = new Edge(edgeList[0], from, to);
                this.addEdge(edge);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Override
    public void topoSort() {

    }
}
