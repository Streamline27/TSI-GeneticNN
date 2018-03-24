package genetic.steps;

import genetic.*;
import genetic.utilities.FitnessEvaluator;
import genetic.utilities.RandomGen;

import java.util.List;

import static genetic.Constants.GENOME_LENGTH;
import static genetic.utilities.Utils.copyOf;
import static genetic.utilities.Utils.emptyCode;
import static genetic.utilities.Utils.takeRandomFrom;

public class CrossoverStep {
    private FitnessEvaluator evaluator;

    public CrossoverStep(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Genome> perform(List<Genome> population) {

        Roulette roulette = new Roulette(population);

        List<Genome> nextPopulation = copyOf(population);

        for (int j = 0; j < Constants.POPULATION_INCREASE; j++) {


            Genome parent1;
            Genome parent2;
            if (Constants.ROULETTE_IN_CROSSOVER_ON) {
                parent1 = roulette.select();
                parent2 = roulette.select();
            }
            else {
                parent1 = takeRandomFrom(population);
                parent2 = takeRandomFrom(population);
            }

            int crossoverPoint1 = RandomGen.getCrossoverPoint();
            int crossoverPoint2 = RandomGen.getCrossoverPoint();

            if (crossoverPoint2 < crossoverPoint1) {
                int tmp = crossoverPoint1;
                crossoverPoint1 = crossoverPoint2;
                crossoverPoint2 = tmp;
            }

            String name = RandomGen.getRandomName();

            double[] code = emptyCode();

            for (int i = 0; i < crossoverPoint1; i++)               code[i] = parent1.getCode()[i];
            for (int i = crossoverPoint1; i < crossoverPoint2; i++) code[i] = parent2.getCode()[i];
            for (int i = crossoverPoint2; i < GENOME_LENGTH; i++)   code[i] = parent1.getCode()[i];

            Genome child = new Genome(code, name, evaluator);

            nextPopulation.add(child);
        }

        return nextPopulation;
    }



}
