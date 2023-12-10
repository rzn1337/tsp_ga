package tsp;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TSPPopulation {

    private List<TSPChromosome> population;
    private final int initialSize;
    private final Graph graph;  // Add a reference to the graph

    TSPPopulation(final Graph graph, final int initialSize) {
        this.population = init(graph, initialSize);
        this.initialSize = initialSize;
        this.graph = graph;
    }

    List<TSPChromosome> getPopulation() {
        return this.population;
    }

    TSPChromosome getAlpha() {
        return this.population.get(0);
    }

    private List<TSPChromosome> init(final Graph graph, final int initialSize) {
        final List<TSPChromosome> eden = new ArrayList<>();
        for (int i = 0; i < initialSize; i++) {
            final TSPChromosome chromosome = TSPChromosome.create(graph);
            eden.add(chromosome);
        }
        return eden;
    }

    void update() {
        doCrossOver();
        doMutation();
        doSpawn();
        doSelection();
    }

    private void doSelection() {
        this.population.sort(Comparator.comparingDouble(TSPChromosome::getDistance));
        this.population = this.population.stream().limit(this.initialSize).collect(Collectors.toList());
    }

    private void doSpawn() {
        IntStream.range(0, 1000).forEach(e -> this.population.add(TSPChromosome.create(graph)));
    }

    private void doMutation() {
        final List<TSPChromosome> newPopulation = new ArrayList<>();
        for (int i = 0; i < this.population.size() / 10; i++) {
            final TSPChromosome mutation = this.population.get(TSPUtils.randomIndex(this.population.size())).mutate();
            newPopulation.add(mutation);
        }
        this.population.addAll(newPopulation);
    }

    private void doCrossOver() {
        final List<TSPChromosome> newPopulation = new ArrayList<>();
        for (final TSPChromosome chromosome : this.population) {
            final TSPChromosome partner = getCrossOverPartner(chromosome);

            // Manual implementation to add elements to the new population
            for (TSPChromosome child : chromosome.crossOver(partner)) {
                newPopulation.add(child);
            }
        }
        this.population.addAll(newPopulation);
    }


    private TSPChromosome getCrossOverPartner(final TSPChromosome chromosome) {
        TSPChromosome partner = this.population.get(TSPUtils.randomIndex(this.population.size()));
        while (chromosome == partner) {
            partner = this.population.get(TSPUtils.randomIndex(this.population.size()));
        }
        return partner;
    }
}
