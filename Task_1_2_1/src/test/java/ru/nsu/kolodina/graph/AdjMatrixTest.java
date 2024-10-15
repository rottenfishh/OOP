package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdjMatrixTest {
    AdjMatrix<String> matrix;
    String pathGraph = "src/test/resources/Graph1.txt";
    fileReader reader;
    @BeforeEach
    public void setUp() {
        matrix = new AdjMatrix<>();
        reader = new fileReader();
        reader.readFromFile(pathGraph, matrix);
    }

    @Test
    public void removeEdgeTest() {
        Edge<String> edge = matrix.edges.get(0);
        matrix.removeEdge(edge);
        assertEquals(6, matrix.edges.size());
    }
    @Test
    public void removeVertexTest() {
        Vertex<String> vertex = matrix.vertices.get(0);
        matrix.removeVertex(vertex);
        assertEquals(5, matrix.vertices.size());
    }
}
