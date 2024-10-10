package ru.nsu.kolodina.graph;

public class Edge<T> {
    public final T name;
    public final Vertex<T> vertexFrom;
    public final Vertex<T> vertexTo;

    public Edge(T name, Vertex<T> from, Vertex<T> to) {
        this.vertexFrom = from;
        this.vertexTo = to;
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }

        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }
        Edge<T> edge = (Edge<T>) object;
        return (edge.vertexFrom.name.equals(this.vertexFrom.name)
            && edge.vertexTo.name.equals(this.vertexTo.name)
            && edge.name.equals(this.name));
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
