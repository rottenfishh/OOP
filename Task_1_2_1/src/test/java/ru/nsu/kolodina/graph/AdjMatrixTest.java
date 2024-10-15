package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdjMatrixTest {
    AdjMatrix<String> matrix;
    String pathGraph = "src/test/resources/Graph1.txt";
    @BeforeEach
    public void setUp() {
        matrix = new AdjMatrix<>();
        matrix.readFromFile(pathGraph);
    }

    @Test
    public void readFromFileTest() {
        assertEquals(6, matrix.vertices.size());
        assertEquals(7, matrix.edges.size());
    }

    @Test
    public void topoSortTest() {
        List<Vertex<String>> sortedList = matrix.topoSort();
        assertEquals("v6", sortedList.get(0).toString());
        assertEquals("v5", sortedList.get(1).toString());
        assertEquals("v1", sortedList.get(2).toString());
        assertEquals("v2", sortedList.get(3).toString());
        assertEquals("v3", sortedList.get(4).toString());
        assertEquals("v4", sortedList.get(5).toString());
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
