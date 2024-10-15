package ru.nsu.kolodina.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFromFileTest {
    IncidenceMatrix<String> matrix;
    String pathGraph = "src/test/resources/Graph2.txt";
    fileReader reader;

    @BeforeEach
    public void setUp() {
        matrix = new IncidenceMatrix<>();
        reader = new fileReader();
        reader.readFromFile(pathGraph, matrix);
    }

    @Test
    public void readFromFileTest() {
        assertEquals(4, matrix.vertices.size());
        assertEquals(4, matrix.edges.size());
    }
}
