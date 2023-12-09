package datastructures.Graph;

import java.util.ArrayList;
import java.util.List;

public class GraphAdjacencyList {
    private List<List<Double>> adjacencyList;
    private List<Vertex> vertices;

    public GraphAdjacencyList(int numVertices) {
        adjacencyList = new ArrayList<>(numVertices);
        vertices = new ArrayList<>(numVertices);

        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
            vertices.add(new Vertex(i)); // Creating vertices with unique IDs
        }
    }

    public void addEdge(Vertex vertexA, Vertex vertexB, double distance) {
        int idA = vertexA.getId();
        int idB = vertexB.getId();

        adjacencyList.get(idA).add(distance);
        adjacencyList.get(idB).add(distance); // Since it's an undirected graph
    }

    public List<Double> getNeighbors(Vertex vertex) {
        return adjacencyList.get(vertex.getId());
    }

    // Other necessary methods for the graph
}
