package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class IncidenceMatrixTest {
    IncidenceMatrix<String> matrix;
    String pathGraph = "src/test/resources/Graph2.txt";
    @BeforeEach
    public void setUp() {
        matrix = new IncidenceMatrix<>();
        matrix.readFromFile(pathGraph);
    }
    @Test
    public void readFromFileTest() {
        assertEquals(4, matrix.vertices.size());
        assertEquals(4, matrix.edges.size());
    }

    @Test
    public void topoSortTest() {
        List<Vertex<String>> sortedList = matrix.topoSort();
        assertEquals(null, sortedList);
    }
}
