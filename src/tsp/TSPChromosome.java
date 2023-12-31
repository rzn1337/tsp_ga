package tsp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class TSPChromosome {

    private final Graph graph;
    private final ArrayList<TSPGene> chromosome;
    private final double distance;

    public double getDistance() {
        return this.distance;
    }

    private TSPChromosome(final Graph graph, final ArrayList<TSPGene> chromosome) {
        this.graph = graph;
        this.chromosome = new ArrayList<>(chromosome);
        this.distance = calculateDistance();
    }
    static TSPChromosome create(final Graph graph) {
        ArrayList<TSPGene> nodes = new ArrayList<>(graph.getNodes());
        Collections.shuffle(nodes);
        return new TSPChromosome(graph, nodes);
    }
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        for (final TSPGene gene : this.chromosome) {
            builder.append(gene.toString()).append((" : "));
        }
        return builder.toString();
    }

    ArrayList<TSPGene> getChromosome() {
        return this.chromosome;
    }

    double calculateDistance() {
        double total = 0.0;
        for (int i = 0; i < this.chromosome.size() - 1; i++) {
            TSPGene currentGene = this.chromosome.get(i);
            TSPGene nextGene = this.chromosome.get(i + 1);
            total += this.graph.getDistance(currentGene, nextGene);
        }
        // Add distance from the last city back to the starting city
        total += this.graph.getDistance(this.chromosome.get(chromosome.size() - 1), this.chromosome.get(0));
        return total;
    }
    TSPChromosome[] crossOver(final TSPChromosome other) {
        Graph graph = this.graph;  // Assuming graph is a member variable in TSPChromosome
        ArrayList<TSPGene> myNodes = this.chromosome;
        ArrayList<TSPGene> otherNodes = other.getChromosome();

        ArrayList<TSPGene> firstCrossOver = new ArrayList<>();
        ArrayList<TSPGene> secondCrossOver = new ArrayList<>();

        // Choose a random index to split the chromosomes
        int splitIndex = new Random().nextInt(myNodes.size());

        // Add nodes up to the split index
        for (int i = 0; i < splitIndex; i++) {
            firstCrossOver.add(myNodes.get(i));
            secondCrossOver.add(otherNodes.get(i));
        }

        // Add remaining nodes in order of appearance in the other chromosome
        for (TSPGene gene : otherNodes) {
            if (!firstCrossOver.contains(gene)) {
                firstCrossOver.add(gene);
            }
        }

        for (TSPGene gene : myNodes) {
            if (!secondCrossOver.contains(gene)) {
                secondCrossOver.add(gene);
            }
        }

        if (firstCrossOver.size() != graph.getNodes().size() || secondCrossOver.size() != graph.getNodes().size()) {
            throw new RuntimeException("oops!");
        }

        return new TSPChromosome[]{
                new TSPChromosome(graph, firstCrossOver),
                new TSPChromosome(graph, secondCrossOver)
        };
    }


    TSPChromosome mutate() {
        Graph graph = this.graph;  // Assuming graph is a member variable in TSPChromosome
        ArrayList<TSPGene> nodes = new ArrayList<>(this.chromosome);

        int indexA = TSPUtils.randomIndex(nodes.size());
        int indexB = TSPUtils.randomIndex(nodes.size());

        // Ensure distinct indices
        while (indexA == indexB) {
            indexB = TSPUtils.randomIndex(nodes.size());
        }

        // Swap nodes in the chromosome
        Collections.swap(nodes, indexA, indexB);

        // Check if the mutation maintains a valid TSP route
        if (nodes.size() != graph.getNodes().size()) {
            throw new RuntimeException("oops!");
        }

        return new TSPChromosome(graph, nodes);
    }

}


