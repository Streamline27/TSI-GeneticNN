package genetic.steps;

import com.sun.tools.javah.Gen;
import genetic.utilities.FitnessEvaluator;
import genetic.Genome;
import genetic.utilities.Statistics;

import java.util.*;
import java.util.stream.Collectors;

import static genetic.Constants.ELITE_COUNT;
import static genetic.Constants.POPULATION_SIZE;
import static genetic.utilities.Utils.copyOf;
import static genetic.utilities.Utils.firstOf;

public class SelectionStep {
    private Statistics statistics;
    private FitnessEvaluator evaluator;

    public SelectionStep(FitnessEvaluator evaluator, Statistics statistics) {
        this.statistics = statistics;
        this.evaluator = evaluator;
    }

    public List<Genome> perform(List<Genome> population) {

        List<Genome> genomeBag = copyOf(population);

        List<Genome> elite = getElite(genomeBag);
        genomeBag = removeEliteFrom(genomeBag);

        Set<Genome> nextGenerationSet = new HashSet<>(elite);

        while (nextGenerationSet.size() < POPULATION_SIZE - ELITE_COUNT) {

            Roulette roulette = new Roulette(genomeBag);

            int geneIndex = roulette.selectIndex();
            Genome canditade = genomeBag.get(geneIndex);

            if (!nextGenerationSet.contains(canditade)) {
                nextGenerationSet.add(canditade);
                genomeBag.remove(geneIndex);
            }
        }

        List<Genome> nextGeneration = new ArrayList<>(nextGenerationSet);

        statistics.recordBestGenes(nextGeneration);
        statistics.recordBestScore(evaluator.computePrediction(firstOf(elite)));

        return nextGeneration;
    }

    private List<Genome> removeEliteFrom(List<Genome> genomeBag) {
        return genomeBag.stream().limit(genomeBag.size()-ELITE_COUNT).collect(Collectors.toList());
    }

    private List<Genome> getElite(List<Genome> population) {
        return population.stream()
                .sorted(Comparator.comparing(Genome::getFitness).reversed())
                .limit(ELITE_COUNT)
                .collect(Collectors.toList());
    }
}
