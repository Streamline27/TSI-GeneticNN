package genetic.steps;

import genetic.*;
import genetic.utilities.FitnessEvaluator;
import genetic.utilities.Generator;

import java.util.List;

import static genetic.Constants.GENOME_LENGTH;
import static genetic.utilities.Utils.copyOf;
import static genetic.utilities.Utils.emptyCode;

public class CrossoverStep {
    private FitnessEvaluator evaluator;

    public CrossoverStep(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Genome> perform(List<Genome> population) {

        Roulette roulette = new Roulette(population);

        List<Genome> nextPopulation = copyOf(population);

        for (int j = 0; j < Constants.POPULATION_INCREASE; j++) {

//            Genome parent1 = takeRandomFrom(population);
//            Genome parent2 = takeRandomFrom(population);

            Genome parent1 = roulette.select();
            Genome parent2 = roulette.select();

            int crossoverPoint = Generator.getCrossoverPoint();
            String name = Generator.getRandomName();

            double[] code = emptyCode();

            for (int i = 0; i < crossoverPoint; i++)             code[i] = parent1.getCode()[i];
            for (int i = crossoverPoint; i < GENOME_LENGTH; i++) code[i] = parent2.getCode()[i];

            Genome child = new Genome(code, name, evaluator);

            nextPopulation.add(child);
        }

        return nextPopulation;
    }

}
