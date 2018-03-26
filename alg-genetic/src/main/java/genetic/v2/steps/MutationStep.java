package genetic.v2.steps;

import genetic.v2.Chromosome;
import genetic.v2.Const;
import genetic.v2.Gene;
import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.RandomGen;

import java.util.List;
import java.util.Set;

import static genetic.v2.utilities.Utils.*;

public class MutationStep {

    private FitnessEvaluator evaluator;

    public MutationStep(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Chromosome> perform(List<Chromosome> population) {

        if (Const.CHROMOSOME_MUTATION_CHANCE == 0.0) return population;

        Set<Chromosome> nextPopulation = toSet(population);

        for (Chromosome candidate : population) {

            if (shouldCreateMutant()) {

                String name = RandomGen.getName();

                List<Gene> code = inflate(degrade(candidate.getCode()));

                if (!code.isEmpty()) nextPopulation.add(new Chromosome(name, code, evaluator));
            }
        }

        return toList(nextPopulation);
    }

    private List<Gene> inflate(List<Gene> sourceCode) {

        int availableSpace = Const.MAX_CHROMOSOME_LENGTH - sourceCode.size();
        int chainSize = RandomGen.getInt(availableSpace);

        List<Gene> code = copyOf(sourceCode);

        int tumorGeneIndex = RandomGen.getInt(sourceCode.size());
        code.remove(tumorGeneIndex);
        for (int i = 0; i < chainSize; i++) {
            code.add(Gene.createRandom());
        }

        return code;
    }

    private List<Gene> degrade(List<Gene> sourceCode) {

        int point1 = RandomGen.getInt(sourceCode.size());
        int point2 = RandomGen.getInt(sourceCode.size());

        int from = Math.min(point1, point2);
        int to   = Math.max(point1, point2);

        List<Gene> code = listOf(Gene.class);
        for (int i = 0;  i < from; i++)              code.add(sourceCode.get(i));
        for (int i = to; i < sourceCode.size(); i++) code.add(sourceCode.get(i));

        return code;
    }

    private boolean shouldCreateMutant() {
        double chance = RandomGen.getRandonDouble();
        return Const.CHROMOSOME_MUTATION_CHANCE < chance;
    }
}
