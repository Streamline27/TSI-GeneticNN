package genetic.v2;

import genetic.v2.steps.CrossoverStep;
import genetic.v2.steps.MutationStep;
import genetic.v2.steps.PopulationGenerator;
import genetic.v2.steps.SelectionStep;
import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.StatisticsCollector;

import java.util.List;

public class GeneticAlgorithm {

    private CrossoverStep crossoverStep;
    private MutationStep mutationStep;
    private SelectionStep selectionStep;

    private StatisticsCollector statistics;

    private List<Chromosome> population;

    public GeneticAlgorithm(FitnessEvaluator evaluator) {
        this.statistics = new StatisticsCollector();

        this.crossoverStep = new CrossoverStep(evaluator);
        this.mutationStep = new MutationStep(evaluator);
        this.selectionStep = new SelectionStep(evaluator, statistics);

        this.population = new PopulationGenerator(evaluator).generate();
    }

    public IterationResult performIteration() {

        statistics.nextIteration();

        population = crossoverStep.perform(population);
        population = mutationStep.perform(population);
        population = selectionStep.perform(population);

        statistics.log();

        return new IterationResult(
                statistics.currently().topScore(),
                statistics.currently().topFitness(),
                statistics.currently().leaderboard(),
                statistics.iteration());
    }


    public final class IterationResult {
        private Double bestScore;
        private Double bestFitness;
        private List<Chromosome> leaderboard;
        private Integer iteration;

        public IterationResult(Double bestScore, Double bestFitness, List<Chromosome> leaderboard, Integer iteration) {
            this.bestScore = bestScore;
            this.leaderboard = leaderboard;
            this.iteration = iteration;
            this.bestFitness = bestFitness;
        }

        public Double getBestScore() {
            return bestScore;
        }

        public void setBestScore(Double bestScore) {
            this.bestScore = bestScore;
        }

        public List<Chromosome> getLeaderboard() {
            return leaderboard;
        }

        public void setLeaderboard(List<Chromosome> leaderboard) {
            this.leaderboard = leaderboard;
        }

        public Integer getIteration() {
            return iteration;
        }

        public void setIteration(Integer iteration) {
            this.iteration = iteration;
        }

        public Double getBestFitness() {
            return bestFitness;
        }
    }
}
