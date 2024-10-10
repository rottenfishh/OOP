package ru.nsu.kolodina.graph;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        AdjMatrix matrix = new AdjMatrix();
        IncidenceMatrix incMatrix = new IncidenceMatrix();
        AdjacencyList list = new AdjacencyList();

        Vertex<Integer> v1 = new Vertex("v1");
        matrix.addVertex(v1);
        Vertex<Integer> v2 = new Vertex(2);
        matrix.addVertex(v2);
        Edge<Integer> e1 = new Edge(0,v1, v2);
        matrix.addEdge(e1);
        List<Vertex<Integer>> neighb;

        incMatrix.addVertex(v1);
        incMatrix.addVertex(v2);
        incMatrix.addEdge(e1);


        list.addVertex(v1);
        list.addVertex(v2);
        list.addEdge(e1);

        List<Vertex<Integer>> listSosedi;
        AdjMatrix newMatrix = new AdjMatrix();
        newMatrix.readFromFile();
        System.out.println(newMatrix.vertices.get(0));
        neighb = newMatrix.getNeighbours(v1);
        for (int i = 0; i < neighb.size(); i++) {
            System.out.println("newMatrix " + neighb.get(i).name);
        }
    }
}
