package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing toposort correctness with different implementation of graph.
 */
public class TopoSortTest {
    IncidenceMatrix<String> matrix1;
    IncidenceMatrix<String> matrix2;

    AdjMatrix<String> adjMatrix1;
    AdjMatrix<String> adjMatrix2;
    AdjacencyList<String> adjList1;
    AdjacencyList<String> adjList2;
    String pathGraph1 = "src/test/resources/Graph1.txt";
    String pathGraph2 = "src/test/resources/Graph2.txt";
    FileReader reader;

    @BeforeEach
    public void setUp() {
        matrix1 = new IncidenceMatrix<>();
        matrix2 = new IncidenceMatrix<>();
        adjMatrix1 = new AdjMatrix<>();
        adjMatrix2 = new AdjMatrix<>();
        adjList1 = new AdjacencyList<>();
        adjList2 = new AdjacencyList<>();
        reader = new FileReader();
        reader.readFromFile(pathGraph1, matrix1);
        reader.readFromFile(pathGraph2, matrix2);
        reader.readFromFile(pathGraph1, adjMatrix1);
        reader.readFromFile(pathGraph2, adjMatrix2);
        reader.readFromFile(pathGraph1, adjList1);
        reader.readFromFile(pathGraph2, adjList2);
    }

    @Test
    public void topoSortTest1() {
        List<Vertex<String>> sortedList = matrix1.topoSort();
        assertEquals("v6", sortedList.get(0).toString());
        assertEquals("v5", sortedList.get(1).toString());
        assertEquals("v1", sortedList.get(2).toString());
        assertEquals("v2", sortedList.get(3).toString());
        assertEquals("v3", sortedList.get(4).toString());
        assertEquals("v4", sortedList.get(5).toString());
    }

    @Test
    public void topoSortTestCycle() {
        List<Vertex<String>> sortedList = matrix2.topoSort();
        assertNull(sortedList);
    }

    @Test
    public void topoSortAdjTest1() {
        List<Vertex<String>> sortedList = adjMatrix1.topoSort();
        assertEquals("v6", sortedList.get(0).toString());
        assertEquals("v5", sortedList.get(1).toString());
        assertEquals("v1", sortedList.get(2).toString());
        assertEquals("v2", sortedList.get(3).toString());
        assertEquals("v3", sortedList.get(4).toString());
        assertEquals("v4", sortedList.get(5).toString());
    }

    @Test
    public void topoSortTestAdjCycle() {
        List<Vertex<String>> sortedList = adjMatrix2.topoSort();
        assertNull(sortedList);
    }

    @Test
    public void topoSortAdjListTest1() {
        List<Vertex<String>> sortedList = adjList1.topoSort();
        assertEquals("v6", sortedList.get(0).toString());
        assertEquals("v5", sortedList.get(1).toString());
        assertEquals("v1", sortedList.get(2).toString());
        assertEquals("v2", sortedList.get(3).toString());
        assertEquals("v3", sortedList.get(4).toString());
        assertEquals("v4", sortedList.get(5).toString());
    }

    @Test
    public void topoSortTestAdjListCycle() {
        List<Vertex<String>> sortedList = adjList2.topoSort();
        assertNull(sortedList);
    }
}
