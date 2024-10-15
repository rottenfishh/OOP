package ru.nsu.kolodina.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class fileReader<T> {

    /**
     * input format: vertex count n.
     * n lines of vertex names
     * edge count m
     * m lines of: edge name vertexFrom name vertexTo name weight
     */

    /**
     * input format: vertex count n.
     * n lines of vertex names
     * edge count m
     * m lines of: edge name vertexFrom name vertexTo name weight
     *
     * @param pathName
     * @param graph
     */
    public void readFromFile(String pathName, Graph<T> graph) {
        int n, m;
        String vertexName, edgeString;
        Vertex<T> vertex;
        Edge<T> edge;
        Vertex<T> from;
        Vertex<T> to;
        int weight;
        String[] edgeList;
        try {
            File myObj = new File(pathName);
            Scanner scanner = new Scanner(myObj);
            n = scanner.nextInt();
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                vertexName = scanner.nextLine();
                vertex = new Vertex(vertexName);
                graph.addVertex(vertex);
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
                graph.addEdge(edge);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
