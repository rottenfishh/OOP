package ru.nsu.kolodina.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing reading from file.
 */
public class ReadFromFileTest {
    IncidenceMatrix<String> matrix;
    String pathGraph = "src/test/resources/Graph2.txt";
    FileReader reader;

    @BeforeEach
    public void setUp() {
        matrix = new IncidenceMatrix<>();
        reader = new FileReader();
        reader.readFromFile(pathGraph, matrix);
    }

    @Test
    public void readFromFileTest() {
        assertEquals(4, matrix.vertices.size());
        assertEquals(4, matrix.edges.size());
    }
}
