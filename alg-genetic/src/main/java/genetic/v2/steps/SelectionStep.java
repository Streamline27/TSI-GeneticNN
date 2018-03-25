package genetic.v2.steps;

import genetic.v2.Chromosome;
import genetic.v2.Const;
import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.StatisticsCollector;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static genetic.v2.utilities.Utils.listOf;

public class SelectionStep {

    private FitnessEvaluator evaluator;
    private StatisticsCollector statistics;

    public SelectionStep(FitnessEvaluator evaluator, StatisticsCollector statistics) {
        this.evaluator = evaluator;
        this.statistics = statistics;
    }

    public List<Chromosome> perform(List<Chromosome> population) {

        List<Chromosome> nextPopulation = listOf(Chromosome.class);

        List<Chromosome> populationBag = sortedCopyOf(population);

        for (int i = 0; i < Const.ELITE_SIZE; i++) {
            nextPopulation.add(populationBag.get(i));
            populationBag.remove(i);
        }

        for (int i = 0; i < Const.POPULATION_SIZE- Const.ELITE_SIZE; i++) {

            Roulette roulette = new Roulette(populationBag);

            Chromosome survivor = roulette.select();
            nextPopulation.add(survivor);
            populationBag.remove(survivor);
        }

        statistics.saveTopFitness(nextPopulation.get(0).getScore());
        statistics.saveTopScore(evaluator.computePrediction(nextPopulation.get(0)));
        statistics.saveLeaders(nextPopulation);
        statistics.saveCodeLengthStatistics(nextPopulation);

        return nextPopulation;
    }

    private List<Chromosome> sortedCopyOf(List<Chromosome> population) {
        return population.stream()
                .sorted(Comparator.comparing(Chromosome::getScore).reversed())
                .collect(Collectors.toList());
    }


}
