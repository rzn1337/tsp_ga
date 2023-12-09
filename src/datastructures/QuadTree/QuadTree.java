package datastructures.QuadTree;

import tsp.*;

import java.util.ArrayList;
import java.util.List;

public class QuadTree {
    public static final int MAX_CAPACITY = 4; // Maximum number of points in a node
    private final QuadNode root;

    public QuadTree(int width, int height) {
        root = new QuadNode(0, 0, width, height);
    }

    public void insert(TSPGene point) {
        root.insert(point);
    }

    public List<TSPGene> queryRange(int xMin, int yMin, int xMax, int yMax) {
        List<TSPGene> pointsInRange = new ArrayList<>();
        root.queryRange(xMin, yMin, xMax, yMax, pointsInRange);
        return pointsInRange;
    }


}

