package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IncidenceMatrix<String> matrix = new IncidenceMatrix<>();
        IncidenceMatrix<String> matrix2 = new IncidenceMatrix<>();
        AdjMatrix<String> aMatrix = new AdjMatrix<>();
        String path = "src/main/resources/Graph1.txt";
        matrix.readFromFile(path);
        aMatrix.readFromFile(path);
        matrix2.readFromFile(path);
        List<Vertex<String>> sortedList = matrix.topoSort();
        if (sortedList != null) {
            for (Vertex<String> v : sortedList) {
                System.out.println(v.toString());
            }
        }
        System.out.println(aMatrix.toString());
        System.out.println(matrix.equals(matrix2));
    }
}
