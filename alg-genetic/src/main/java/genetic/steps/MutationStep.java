package genetic.steps;

import genetic.Constants;
import genetic.utilities.FitnessEvaluator;
import genetic.utilities.Generator;
import genetic.Genome;

import java.util.List;

import static genetic.Constants.GENE_MUTATION_CHANCE;
import static genetic.Constants.GENOME_MUTATION_CHANCE;
import static genetic.utilities.Utils.copyOf;
import static genetic.utilities.Utils.emptyCode;

public class MutationStep {
    private FitnessEvaluator evaluator;

    public MutationStep(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Genome> perform(List<Genome> population) {

        List<Genome> nextPopulation = copyOf(population);

        for (Genome genome : population) {

            if (Generator.getRandonDouble() < GENOME_MUTATION_CHANCE) {

                double[] prototypeCode = genome.getCode();
                double[] mutatedCode = emptyCode();

                for (int j = 0; j < mutatedCode.length; j++) {

                    double gene = prototypeCode[j];
                    if (Generator.getRandonDouble() < GENE_MUTATION_CHANCE) gene = swap(gene);
                    mutatedCode[j] = gene;
                }

                String name = Generator.getRandomName();

                nextPopulation.add(new Genome(mutatedCode, name, evaluator));
            }
        }

        return nextPopulation;
    }

    public double swap(double gene) {
        if (gene == Constants.COLOR_BLACK) return Constants.COLOR_WHITE;
        return Constants.COLOR_BLACK;
    }
}
