package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

/**
 * testing equals methods with objects of different classes.
 */
public class EqualsTest {
    String pathGraph = "Graph2.txt";
    FileReader reader = new FileReader();

    @Test
    public void edgesTest() {
        Vertex<String> v1 = new Vertex<>("v1");
        Vertex<String> v2 = new Vertex<>("v2");
        Edge<String> edge1 = new Edge("e1", v1, v2, 5);
        Edge<String> edge2 = new Edge("e1", v1, v2, 5);
        Edge<String> edge3 = new Edge("e2", v2, v1, 10);
        assertEquals(edge1, edge2);
        assertNotEquals(edge1, edge3);
    }

    @Test
    public void adjListTest() throws URISyntaxException {
        AdjacencyList<String> list1 = new AdjacencyList<>();
        AdjacencyList<String> list2 = new AdjacencyList<>();
        reader.readFromFile(pathGraph, list1, s -> s);
        reader.readFromFile(pathGraph, list2, s -> s);
        assertEquals(list1, list2);
    }

    @Test
    public void adjMatrixTest() throws URISyntaxException {
        AdjMatrix<String> adjMatrix1 = new AdjMatrix<>();
        AdjMatrix<String> adjMatrix2 = new AdjMatrix<>();
        reader.readFromFile(pathGraph, adjMatrix1, s -> s);
        reader.readFromFile(pathGraph, adjMatrix2, s -> s);
        assertEquals(adjMatrix1, adjMatrix2);
    }

    @Test
    public void incidenceMatrixTest() throws URISyntaxException {
        IncidenceMatrix<String> incMatrix1 = new IncidenceMatrix<>();
        IncidenceMatrix<String> incMatrix2 = new IncidenceMatrix<>();
        reader.readFromFile(pathGraph, incMatrix1, s -> s);
        reader.readFromFile(pathGraph, incMatrix2, s -> s);
        assertEquals(incMatrix1, incMatrix2);
    }
}
