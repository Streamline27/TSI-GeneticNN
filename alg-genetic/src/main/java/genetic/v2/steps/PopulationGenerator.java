package genetic.v2.steps;

import genetic.v2.Chromosome;
import genetic.v2.Const;
import genetic.v2.Gene;
import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.RandomGen;

import java.util.List;

import static genetic.v2.utilities.Utils.listOf;

public class PopulationGenerator {

    private FitnessEvaluator evaluator;

    public PopulationGenerator(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Chromosome> generate() {

        List<Chromosome> population = listOf(Chromosome.class);
        for (int i = 0; i < Const.POPULATION_SIZE; i++) {

            String name = RandomGen.getName();
            List<Gene> code = generateCode();

            population.add(new Chromosome(name, code, evaluator));
        }

        return population;
    }


    private List<Gene> generateCode() {

        List<Gene> code = listOf(Gene.class);
        for (int i = 0; i < Const.INITIAL_CHROMOSOME_LENGTH; i++) {

            code.add(Gene.createRandom());
        }

        return code;
    }

}
