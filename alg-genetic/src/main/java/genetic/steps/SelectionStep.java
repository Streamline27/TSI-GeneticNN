package genetic.steps;

import genetic.Constants;
import genetic.utilities.FitnessEvaluator;
import genetic.Genome;
import genetic.utilities.Statistics;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static genetic.utilities.Utils.firstOf;

public class SelectionStep {
    private Statistics statistics;
    private FitnessEvaluator evaluator;

    public SelectionStep(FitnessEvaluator evaluator, Statistics statistics) {
        this.statistics = statistics;
        this.evaluator = evaluator;
    }

    public List<Genome> perform(List<Genome> population) {

//        Roulette roulette = new Roulette(population);
//
//        List<Genome> elite = getElite(population);
//        List<Genome> nextGeneration = new ArrayList<>(elite);
//
//        for (int i = 0; i < POPULATION_SIZE - ELITE_COUNT; i++) {
//            nextGeneration.add(roulette.select());
//        }
//
//        return nextGeneration;

        List<Genome> nextGeneration = population.stream()
                .sorted(Comparator.comparing(Genome::getFitness).reversed())
                .limit(Constants.POPULATION_SIZE)
                .collect(Collectors.toList());

        statistics.recordBestGenes(nextGeneration.stream().limit(3).collect(Collectors.toList()));
        statistics.recordBestScore(evaluator.computePrediction(firstOf(nextGeneration)));

        return nextGeneration;
    }

//    private List<Genome> getElite(List<Genome> population) {
//        return population.stream()
//                .sorted(Comparator.comparing(Genome::getFitness).reversed())
//                .limit(Constants.ELITE_COUNT)
//                .collect(Collectors.toList());
//    }
}
