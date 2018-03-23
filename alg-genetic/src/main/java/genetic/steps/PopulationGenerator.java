package genetic.steps;

import genetic.Constants;
import genetic.Genome;
import genetic.utilities.FitnessEvaluator;

import java.util.ArrayList;
import java.util.List;

public class PopulationGenerator {

    public FitnessEvaluator evaluator;

    public PopulationGenerator(FitnessEvaluator evaluator) {
        this.evaluator = evaluator;
    }

    public List<Genome> generate() {
        List<Genome> genes = new ArrayList<>();
        for (int i = 0; i < Constants.POPULATION_SIZE; i++) {
            genes.add(createGenome());
        }
        return genes;
    }

    private Genome createGenome() {
        return new Genome(evaluator);
    }
}
