package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.net.URISyntaxException;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing toposort correctness with different implementation of graph.
 */
public class TopoSortTest {
    IncidenceMatrix<String> matrix1;
    IncidenceMatrix<String> matrix2;
    TopoSort<String> topoSort;

    AdjMatrix<String> adjMatrix1;
    AdjMatrix<String> adjMatrix2;
    AdjacencyList<String> adjList1;
    AdjacencyList<String> adjList2;
    String pathGraph1 = "Graph1.txt";
    String pathGraph2 = "Graph2.txt";
    FileReader reader;

    /**
     * setting up needed resources.
     */
    @BeforeEach
    public void setUp() throws URISyntaxException {
        matrix1 = new IncidenceMatrix<>();
        matrix2 = new IncidenceMatrix<>();
        adjMatrix1 = new AdjMatrix<>();
        adjMatrix2 = new AdjMatrix<>();
        adjList1 = new AdjacencyList<>();
        adjList2 = new AdjacencyList<>();
        reader = new FileReader();
        topoSort = new TopoSort<>();
        reader.readFromFile(pathGraph1, matrix1, s -> s);
        reader.readFromFile(pathGraph2, matrix2, s -> s);
        reader.readFromFile(pathGraph1, adjMatrix1, s -> s);
        reader.readFromFile(pathGraph2, adjMatrix2, s -> s);
        reader.readFromFile(pathGraph1, adjList1, s -> s);
        reader.readFromFile(pathGraph2, adjList2, s -> s);
    }

    @Test
    public void topoSortTest1() {
        List<Vertex<String>> sortedList = topoSort.perform(matrix1);
        assertEquals("v6", sortedList.get(0).name());
        assertEquals("v5", sortedList.get(1).name());
        assertEquals("v1", sortedList.get(2).name());
        assertEquals("v2", sortedList.get(3).name());
        assertEquals("v3", sortedList.get(4).name());
        assertEquals("v4", sortedList.get(5).name());
    }

    @Test
    public void topoSortTestCycle() {
        List<Vertex<String>> sortedList = topoSort.perform(matrix2);
        assertNull(sortedList);
    }

    @Test
    public void topoSortAdjTest1() {
        List<Vertex<String>> sortedList = topoSort.perform(adjMatrix1);
        assertEquals("v6", sortedList.get(0).name());
        assertEquals("v5", sortedList.get(1).name());
        assertEquals("v1", sortedList.get(2).name());
        assertEquals("v2", sortedList.get(3).name());
        assertEquals("v3", sortedList.get(4).name());
        assertEquals("v4", sortedList.get(5).name());
    }

    @Test
    public void topoSortTestAdjCycle() {
        List<Vertex<String>> sortedList = topoSort.perform(adjMatrix2);
        assertNull(sortedList);
    }

    @Test
    public void topoSortAdjListTest1() {
        List<Vertex<String>> sortedList = topoSort.perform(adjList1);
        assertEquals("v6", sortedList.get(0).name());
        assertEquals("v5", sortedList.get(1).name());
        assertEquals("v1", sortedList.get(2).name());
        assertEquals("v2", sortedList.get(3).name());
        assertEquals("v3", sortedList.get(4).name());
        assertEquals("v4", sortedList.get(5).name());
    }

    @Test
    public void topoSortTestAdjListCycle() {
        List<Vertex<String>> sortedList = topoSort.perform(adjList2);
        assertNull(sortedList);
    }
}
