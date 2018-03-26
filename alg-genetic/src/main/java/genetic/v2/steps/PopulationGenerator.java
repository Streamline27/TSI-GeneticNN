package genetic.v2.steps;

import genetic.v2.Chromosome;
import genetic.v2.Const;
import genetic.v2.Gene;
import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.RandomGen;

import java.util.List;
import java.util.Set;

import static genetic.v2.utilities.Utils.listOf;
import static genetic.v2.utilities.Utils.setOf;
import static genetic.v2.utilities.Utils.toList;

public class PopulationGenerator {

    private FitnessEvaluator evaluator;

    public PopulationGenerator(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Chromosome> generate() {

        Set<Chromosome> population = setOf(Chromosome.class);

        while (population.size() < Const.POPULATION_SIZE) {

            String name = RandomGen.getName();
            List<Gene> code = generateCode();

            population.add(new Chromosome(name, code, evaluator));
        }

        return toList(population);
    }


    private List<Gene> generateCode() {

        List<Gene> code = listOf(Gene.class);
        for (int i = 0; i < Const.INITIAL_CHROMOSOME_LENGTH; i++) {

            code.add(Gene.createRandom());
        }

        return code;
    }

}
