package genetic.steps;

import genetic.Constants;
import genetic.utilities.FitnessEvaluator;
import genetic.utilities.RandomGen;
import genetic.Genome;

import java.util.List;

import static genetic.Constants.*;
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

            if (RandomGen.getRandonDouble() < GENOME_MUTATION_CHANCE) {

                double[] prototypeCode = genome.getCode();
                double[] mutatedCode = emptyCode();

                for (int j = 0; j < mutatedCode.length; j++) {

                    double gene = prototypeCode[j];
                    if (RandomGen.getRandonDouble() < GENE_MUTATION_CHANCE) gene = swap(gene);
                    mutatedCode[j] = gene;
                }

                String name = RandomGen.getRandomName();

                nextPopulation.add(new Genome(mutatedCode, name, evaluator));
            }
        }

        return nextPopulation;
    }

    public double swap(double gene) {
        if (Constants.BLACK_AND_WHITE_MODE_ON) {
            return RandomGen.getRandomGene();
        }
        else {
            return gene == COLOR_BLACK ? COLOR_WHITE : COLOR_BLACK;
        }
    }
}
