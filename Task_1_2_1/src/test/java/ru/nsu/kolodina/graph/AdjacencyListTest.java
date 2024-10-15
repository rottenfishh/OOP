package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AdjacencyListTest {
    AdjacencyList<String> matrix;
    String pathGraph = "src/test/resources/Graph1.txt";
    fileReader<String> reader;
    @BeforeEach
    public void setUp() {
        matrix = new AdjacencyList<>();
        reader = new fileReader<>();
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

    @Test
    public void getNeighboursTest() {
        Vertex<String> v = matrix.vertices.get(0);
        List <Vertex<String>> neighbors = matrix.getNeighbours(v);
        assertEquals("v2", neighbors.get(0).name);
        assertEquals("v3", neighbors.get(1).name);
        assertEquals("v4", neighbors.get(2).name);
    }
}
