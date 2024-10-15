package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IncidenceMatrix<String> matrix = new IncidenceMatrix<>();
        String path = "src/main/resources/Graph2.txt";
        matrix.readFromFile(path);
        List<Vertex<String>> sortedList = matrix.topoSort();
        if (sortedList != null) {
            for (Vertex<String> v : sortedList) {
                System.out.println(v.toString());
            }
        }
    }
}
