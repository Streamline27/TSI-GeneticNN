package genetic.v2.steps;

import genetic.v2.Chromosome;
import genetic.v2.Const;
import genetic.v2.Gene;
import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.RandomGen;

import java.util.List;

import static genetic.v2.utilities.Utils.copyOf;
import static genetic.v2.utilities.Utils.listOf;

public class MutationStep {

    private FitnessEvaluator evaluator;

    public MutationStep(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Chromosome> perform(List<Chromosome> population) {

        List<Chromosome> nextPopulation = copyOf(population);

        for (Chromosome candidate : population) {

            if (shouldCreateMutant()) {

                String name = RandomGen.getName();

                List<Gene> code;

                if (shouldDegrade()) {
                    code = degrade(candidate.getCode());
                }
                else {
                    code = inflate(candidate.getCode());
                }

                nextPopulation.add(new Chromosome(name, code, evaluator));
            }
        }

        return population;
    }

    private List<Gene> inflate(List<Gene> sourceCode) {

        List<Gene> code = listOf(Gene.class);

        for (Gene gene : sourceCode) {
            if (shouldMutateGene()) {

                int maxGeneChain = Const.MAX_GENES_IN_MUTATED_CHAIN;
                int chainSize = RandomGen.getInt(maxGeneChain);

                for (int i = 0; i < chainSize; i++) {
                    code.add(Gene.createRandom());
                }
            }
            else {
                code.add(gene);
            }
        }
        return code;
    }

    private List<Gene> degrade(List<Gene> soureCode) {

        int point1 = RandomGen.getInt(soureCode.size());
        int point2 = RandomGen.getInt(soureCode.size());

        int from = Math.min(point1, point2);
        int to   = Math.max(point1, point2);

        List<Gene> code = listOf(Gene.class);
        for (int i = 0;  i < from; i++)             code.add(soureCode.get(i));
        for (int i = to; i < soureCode.size(); i++) code.add(soureCode.get(i));

        return code;
    }

    private boolean shouldDegrade() {
        double chance = RandomGen.getRandonDouble();
        return Const.DEGRADATION_CHANCE < chance;
    }

    private boolean shouldCreateMutant() {
        double chance = RandomGen.getRandonDouble();
        return Const.CHROMOSOME_MUTATION_CHANCE < chance;
    }

    private boolean shouldMutateGene() {
        double chance = RandomGen.getRandonDouble();
        return Const.GENE_MUTATION_CHANCE < chance;
    }
}
