package datastructures.Graph;

import java.util.HashMap;
import java.util.Map;

public class Vertex {
    private final int id;
    private final int x;
    private final int y;
    private final Map<Vertex, Double> edges; // Map to store adjacent cities and distances

    public Vertex(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.edges = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void addEdge(Vertex city, double distance) {
        edges.put(city, distance);
    }

    public Map<Vertex, Double> getEdges() {
        return edges;
    }
}
