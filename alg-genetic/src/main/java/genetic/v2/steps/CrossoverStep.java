package genetic.v2.steps;

import genetic.v2.Chromosome;
import genetic.v2.Const;
import genetic.v2.Gene;
import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.RandomGen;

import java.util.List;
import java.util.Set;

import static genetic.v2.utilities.Utils.*;

public class CrossoverStep {

    private FitnessEvaluator evaluator;

    public CrossoverStep(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Chromosome> perform(List<Chromosome> population) {

        Set<Chromosome> nextPopulation = toSet(population);

        int i = 0;
        while (i < Const.POPULATION_INCREASE) {
            Chromosome parent1 = takeRandomFrom(population);
            Chromosome parent2 = takeRandomFrom(population);

            int crossoverPoint1 = randomPointAtCodeOf(parent1);
            int crossoverPoint2 = randomPointAtCodeOf(parent2);

            String name = RandomGen.getName();
            Set<Gene> code = setOf(Gene.class);

            List<Gene> codeFragment1 = fragmentOf(parent1, 0, crossoverPoint1);
            List<Gene> codeFragment2 = fragmentOf(parent2, crossoverPoint2, parent2.getCode().size());

            code.addAll(codeFragment1);
            code.addAll(codeFragment2);

            if (code.size() < Const.MAX_CHROMOSOME_LENGTH && code.size() > Const.MIN_CHROMOSOME_LENGTH) {

                nextPopulation.add(new Chromosome(name, toList(code), evaluator));
                i++;
            }
        }

        return toList(nextPopulation);
    }

    private List<Gene> fragmentOf(Chromosome source, int from, int to) {
        List<Gene> fragment = listOf(Gene.class);
        for (int i = from; i < to; i++) {
            fragment.add(source.getCode().get(i));
        }
        return fragment;
    }


    private Chromosome takeRandomFrom(List<Chromosome> population) {
        return population.get(RandomGen.getInt(population.size()));
    }

    private int randomPointAtCodeOf(Chromosome chromosome) {
        return RandomGen.getInt(chromosome.getCode().size());
    }
}
