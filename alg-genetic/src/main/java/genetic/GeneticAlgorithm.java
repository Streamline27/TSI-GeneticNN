package genetic;

import genetic.cost.CostFunctionImageSquares;
import genetic.cost.CostFunctionNN;
import genetic.steps.*;
import genetic.utilities.FitnessEvaluator;
import genetic.utilities.Statistics;
import neuralnet.NNModel;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GeneticAlgorithm {

    private List<Genome> population;

    private CrossoverStep crossoverStep;
    private MutationStep mutationStep;
    private SelectionStep selectionStep;

    private Statistics statistics;

    public GeneticAlgorithm(NNModel nnModel) {
        this.statistics = new Statistics();

        FitnessEvaluator costFunction;

        if (Constants.NEURAL_NETWORK_EVALUATION) {
            costFunction = new CostFunctionNN(nnModel);
        }
        else {
            costFunction = new CostFunctionImageSquares();
        }

        population = new PopulationGenerator(costFunction).generate();

        crossoverStep = new CrossoverStep(costFunction);
        mutationStep = new MutationStep(costFunction);
        selectionStep = new SelectionStep(costFunction, statistics);
    }

    public IterationResult runIteration() {

        statistics.increaseIteration();

        population = crossoverStep.perform(population);
        population = mutationStep.perform(population);
        population = selectionStep.perform(population);

        statistics.log();

        Statistics.Record record = statistics.getCurrentRecord();

        return new IterationResult(
                record.getIteration(),
                record.getBestGenomes(),
                record.getBestScore());
    }

    public class IterationResult{
        public Integer iterationNumber;
        public List<Genome> topGenomes;
        public Double topScore;

        public IterationResult(Integer iterationNumber, List<Genome> topGenomes, Double topScore) {
            this.iterationNumber = iterationNumber;
            this.topScore = topScore;
            this.topGenomes = topGenomes.stream()
                    .sorted(Comparator.comparing(Genome::getFitness).reversed())
                    .collect(Collectors.toList());
        }

        public Integer getIterationNumber() {
            return iterationNumber;
        }

        public List<Genome> getTopGenomes() {
            return topGenomes;
        }


        public Double getTopScore() {
            return topScore;
        }

    }
}
