package genetic;

import genetic.steps.CrossoverStep;
import genetic.steps.MutationStep;
import genetic.steps.PopulationGenerator;
import genetic.steps.SelectionStep;
import genetic.utilities.FitnessEvaluator;
import genetic.utilities.Statistics;
import neuralnet.NNModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GeneticAlgorithm implements FitnessEvaluator {
    private NNModel nnModel;

    private List<Genome> population;

    private CrossoverStep crossoverStep;
    private MutationStep mutationStep;
    private SelectionStep selectionStep;

    private Statistics statistics;

    public GeneticAlgorithm(NNModel nnModel) {
        this.nnModel = nnModel;
        this.statistics = new Statistics();

        population = new PopulationGenerator(this).generate();

        crossoverStep = new CrossoverStep(this);
        mutationStep = new MutationStep(this);
        selectionStep = new SelectionStep(this, statistics);
    }

    @Override
    public Double computeFitness(Genome genome) {

        double[] prediction = nnModel.predict(genome.getCode());

        double sum = 0;
        for (double digitChance : prediction) {
            sum = digitChance;
        }

        return sum + (prediction[2]*2);
    }

    @Override
    public Double computePrediction(Genome genome) {
        return nnModel.predict(genome.getCode())[2];
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
