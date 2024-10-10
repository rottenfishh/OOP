package ru.nsu.kolodina.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IncidenceMatrix<T> implements Graph<T>{
    List<List<Boolean>> matrix;
    List<Vertex<T>> vertices;
    List<Edge> edges;

    public IncidenceMatrix() {
        matrix = new ArrayList<>();
        vertices = new ArrayList<>();
        edges = new ArrayList<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        vertices.add(vertex);
        List<Boolean> newArr= new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            newArr.add(false);
        }
        matrix.add(newArr);
    }

    @Override
    public void removeVertex(Vertex<T> vertex) {
        int idx = -1;
        for (int i = 0; i<vertices.size(); i++) {
            if (vertices.get(i).equals(vertex)) {
                idx = i;
                break;
            }
        }
        matrix.remove(idx);
    }

    @Override
    public void addEdge(Edge<T> edge) {
        edges.add(edge);
        for (int i = 0; i < vertices.size(); i++) {
            Vertex<T> vertex = vertices.get(i);
            if (vertex.equals(edge.vertexFrom) || vertex.equals(edge.vertexTo)) {
                matrix.get(i).add(true);
            } else {
                matrix.get(i).add(false);
            }
        }
    }

    @Override
    public void removeEdge(Edge<T> edge) {
        int idx = -1;
        for (int i = 0; i <edges.size(); i++) {
            if (edges.get(i).equals(edge)) {
                idx = i;
                break;
            }
        }
        for (int j = 0; j < vertices.size(); j++) {
            matrix.get(j).remove(idx);
        }
    }

    @Override
    public List<Vertex<T>> getNeighbours(Vertex<T> vertex) {
        int idx = -1;
        List<Vertex<T>> neighbors = new ArrayList<>();
        for (int i = 0; i<vertices.size(); i++) {
            if (vertices.get(i).equals(vertex)) {
                idx = i;
            }
        }
        for (int j = 0; j < edges.size(); j++) {
            if (matrix.get(idx).get(j) == true) {
                Edge edge = edges.get(j);
                if (!edge.vertexFrom.equals(vertex)) {
                    neighbors.add(edge.vertexFrom);
                }
                if (!edge.vertexTo.equals(vertex)) {
                    neighbors.add(edge.vertexTo);
                }
            }
        }
        return neighbors;
    }

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
