package datastructures.Graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Vertex> cities;

    public Graph() {
        cities = new ArrayList<>();
    }

    public void addCity(Vertex city) {
        cities.add(city);
    }

    public void addDistance(Vertex cityA, Vertex cityB) {
        double distance = calculateDistance(cityA, cityB);
        cityA.addEdge(cityB, distance);
        cityB.addEdge(cityA, distance);
    }

    public double getDistance(Vertex cityA, Vertex cityB) {
        return cityA.getEdges().get(cityB);
    }

    private double calculateDistance(Vertex cityA, Vertex cityB) {
        return Math.sqrt(Math.pow(cityA.getX() - cityB.getX(), 2) + Math.pow(cityA.getY() - cityB.getY(), 2));
    }
}
