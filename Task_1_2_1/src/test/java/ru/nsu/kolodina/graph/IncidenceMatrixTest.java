package ru.nsu.kolodina.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IncidenceMatrixTest {
    IncidenceMatrix<String> matrix;
    String pathGraph = "src/test/resources/Graph2.txt";
    fileReader reader;

    @BeforeEach
    public void setUp() {
        matrix = new IncidenceMatrix<>();
        reader = new fileReader();
        reader.readFromFile(pathGraph, matrix);
    }


    @Test
    public void removeEdgeTest() {
        Edge<String> edge = matrix.edges.get(0);
        matrix.removeEdge(edge);
        assertEquals(3, matrix.edges.size());
    }

    @Test
    public void removeVertexTest() {
        Vertex<String> vertex = matrix.vertices.get(0);
        matrix.removeVertex(vertex);
        assertEquals(3, matrix.vertices.size());
    }
}
