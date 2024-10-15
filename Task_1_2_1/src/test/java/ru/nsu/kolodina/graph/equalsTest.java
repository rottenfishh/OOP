package ru.nsu.kolodina.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class equalsTest {
    String pathGraph = "src/test/resources/Graph2.txt";
    fileReader<String> reader = new fileReader();
    @Test
    public void edgesTest() {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        Edge<String> edge1 = new Edge("e1", v1, v2, 5);
        Edge<String> edge2 = new Edge("e1", v1, v2, 5);
        Edge<String> edge3 = new Edge("e2", v2, v1, 10);
        assertTrue(edge1.equals(edge2));
        assertFalse(edge1.equals(edge3));
    }
    @Test
    public void adjListTest() {
        AdjacencyList<String> list1 = new AdjacencyList<>();
        AdjacencyList<String> list2 = new AdjacencyList<>();
        reader.readFromFile(pathGraph, list1);
        reader.readFromFile(pathGraph, list2);
        assertTrue(list1.equals(list2));
    }
    @Test
    public void adjMatrixTest() {
        AdjMatrix<String> adjMatrix1 = new AdjMatrix<>();
        AdjMatrix<String> adjMatrix2 = new AdjMatrix<>();
        reader.readFromFile(pathGraph, adjMatrix1);
        reader.readFromFile(pathGraph, adjMatrix2);
        assertTrue(adjMatrix1.equals(adjMatrix2));
    }
    @Test
    public void incidenceMatrixTest() {
        IncidenceMatrix<String> incMatrix1 = new IncidenceMatrix<>();
        IncidenceMatrix<String> incMatrix2 = new IncidenceMatrix<>();
        reader.readFromFile(pathGraph, incMatrix1);
        reader.readFromFile(pathGraph, incMatrix2);
        assertTrue(incMatrix1.equals(incMatrix2));
    }
}
