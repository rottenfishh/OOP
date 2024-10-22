package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.lang.Integer;

/**
 * testing reading from file.
 */
public class ReadFromFileTest {
    IncidenceMatrix<String> matrix;
    AdjacencyList<Integer> intList;
    String pathGraph = "src/test/resources/Graph2.txt";
    String pathGraph3 = "src/test/resources/Graph3.txt";
    FileReader reader;

    /**
     * setting up needed resources.
     */
    @BeforeEach
    public void setUp() {
        matrix = new IncidenceMatrix<>();
        intList = new AdjacencyList<>();
        reader = new FileReader();
        reader.readFromFile(pathGraph, matrix, s -> s);
        reader.readFromFile(pathGraph3, intList, Integer::parseInt );
    }

    @Test
    public void readFromFileTest1() {
        assertEquals(4, matrix.vertices.size());
        assertEquals(4, matrix.edges.size());
    }

    @Test
    public void readFromFileTest2() {
        assertEquals(5, intList.vertices.size());
        assertEquals(4, intList.edges.size());
    }
}
