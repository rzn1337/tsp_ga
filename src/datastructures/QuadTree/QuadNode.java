package datastructures.QuadTree;

import tsp.*;

import java.util.ArrayList;
import java.util.List;

import static datastructures.QuadTree.QuadTree.MAX_CAPACITY;


public class QuadNode {
    private final int xMin, yMin, xMax, yMax;
    private final List<TSPGene> points;
    private final QuadNode[] children;

    public QuadNode(int xMin, int yMin, int xMax, int yMax) {
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
        this.points = new ArrayList<>();
        this.children = new QuadNode[4]; // Four children for each quadrant
    }

    public void insert(TSPGene point) {
        if (children[0] != null) {
            int quadrant = getQuadrant(point);
            if (quadrant != -1) {
                children[quadrant].insert(point);
                return;
            }
        }

        points.add(point);
        if (points.size() > MAX_CAPACITY && children[0] == null) {
            subdivide();
            redistributePoints();
        }
    }

    private int getQuadrant(TSPGene point) {
        int xMid = (xMin + xMax) / 2;
        int yMid = (yMin + yMax) / 2;

        if (point.getX() >= xMin && point.getX() <= xMid) {
            if (point.getY() >= yMin && point.getY() <= yMid) {
                return 0; // Top left quadrant
            } else if (point.getY() > yMid && point.getY() <= yMax) {
                return 1; // Bottom left quadrant
            }
        } else if (point.getX() > xMid && point.getX() <= xMax) {
            if (point.getY() >= yMin && point.getY() <= yMid) {
                return 2; // Top right quadrant
            } else if (point.getY() > yMid && point.getY() <= yMax) {
                return 3; // Bottom right quadrant
            }
        }

        return -1; // Point doesn't fit into any quadrant
    }

    private void subdivide() {
        int xMid = (xMin + xMax) / 2;
        int yMid = (yMin + yMax) / 2;

        children[0] = new QuadNode(xMin, yMin, xMid, yMid);
        children[1] = new QuadNode(xMin, yMid, xMid, yMax);
        children[2] = new QuadNode(xMid, yMin, xMax, yMid);
        children[3] = new QuadNode(xMid, yMid, xMax, yMax);
    }

    private void redistributePoints() {
        for (int i = points.size() - 1; i >= 0; i--) {
            int quadrant = getQuadrant(points.get(i));
            if (quadrant != -1) {
                children[quadrant].insert(points.remove(i));
            }
        }
    }

    public void queryRange(int xMin, int yMin, int xMax, int yMax, List<TSPGene> pointsInRange) {
        if (xMax < this.xMin || xMin > this.xMax || yMax < this.yMin || yMin > this.yMax) {
            return; // No overlap, return empty list
        }

        for (TSPGene point : points) {
            if (point.getX() >= xMin && point.getX() <= xMax && point.getY() >= yMin && point.getY() <= yMax) {
                pointsInRange.add(point);
            }
        }

        if (children[0] != null) {
            for (QuadNode child : children) {
                child.queryRange(xMin, yMin, xMax, yMax, pointsInRange);
            }
        }
    }
}
