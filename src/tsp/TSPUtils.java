package tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class TSPUtils {

    private final static Random R = new Random(10000);

    // Assuming Graph is used for cities
    static final Graph CITIES_GRAPH = generateData(60);

    private TSPUtils() {
        throw new RuntimeException("No!");
    }

    private static Graph generateData(final int numDataPoints) {
        final List<TSPGene> data = new ArrayList<>();
        for (int i = 0; i < numDataPoints; i++) {
            data.add(new TSPGene(randomIndex(World.WIDTH), randomIndex(World.HEIGHT)));
        }
        return new Graph(data);
    }

    static int randomIndex(final int limit) {
        return R.nextInt(limit);
    }

    static<T> List<T>[] split(final List<T> list) {
        final List<T> first = new ArrayList<>();
        final List<T> second = new ArrayList<>();
        final int size = list.size();
        final int partitionIndex = 1 + TSPUtils.randomIndex(list.size());
        IntStream.range(0, size).forEach(i -> {
            if (i < (size + 1) / partitionIndex) {
                first.add(list.get(i));
            } else {
                second.add(list.get(i));
            }
        });
        return (List<T>[]) new List[]{first, second};
    }
}

