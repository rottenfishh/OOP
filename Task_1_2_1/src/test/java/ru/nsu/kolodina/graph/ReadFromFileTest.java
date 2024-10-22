package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.Integer;
import java.net.URISyntaxException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing reading from file.
 */
public class ReadFromFileTest {
    IncidenceMatrix<String> matrix;
    AdjacencyList<Integer> intList;
    String Graph1 = "Graph2.txt";
    String Graph3 = "Graph3.txt";
    FileReader reader;

    /**
     * setting up needed resources.
     */
    @BeforeEach
    public void setUp() throws URISyntaxException {
        matrix = new IncidenceMatrix<>();
        intList = new AdjacencyList<>();
        reader = new FileReader();
        reader.readFromFile(Graph1, matrix, s -> s);
        reader.readFromFile(Graph3, intList, Integer::parseInt);
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
