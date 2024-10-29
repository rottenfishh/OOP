package ru.nsu.kolodina.graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Function;

/**
 * class for reading graphs from files.
 */
public class FileReader {

    /**
     * input format: vertex count n.
     * n lines of vertex names
     * edge count m
     * m lines of: edge name vertexFrom name vertexTo name weight
     *
     * @param pathName path to the file to be read from
     * @param graph    graph to write into
     */
    public <T> void readFromFile(String pathName, Graph<T> graph, Function<String, T> parse)
            throws URISyntaxException, FileNotFoundException {
        String vertexName;
        String edgeString;
        Vertex<T> vertex;
        Edge<T> edge;
        Vertex<T> from;
        Vertex<T> to;
        int weight;
        String[] edgeList;
        URL resource = getClass().getClassLoader().getResource(pathName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found!");
        }

        File file = new File(resource.toURI());
        Scanner scanner = new Scanner(file);
        int n;
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++) {
            vertexName = scanner.nextLine();
            vertex = new Vertex(parse.apply(vertexName));
            graph.addVertex(vertex);
        }
        int m;
        m = scanner.nextInt();
        scanner.nextLine();
        for (int j = 0; j < m; j++) {
            edgeString = scanner.nextLine();
            edgeList = edgeString.split(" ");
            from = new Vertex(parse.apply(edgeList[1]));
            to = new Vertex(parse.apply(edgeList[2]));
            weight = Integer.parseInt(edgeList[3]);
            edge = new Edge<>(parse.apply(edgeList[0]), from, to, weight);
            graph.addEdge(edge);
        }
        scanner.close();
    }
}
