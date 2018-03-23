package genetic.utilities;

import genetic.Genome;

public interface FitnessEvaluator {
    Double computeFitness(Genome genome);
    Double computePrediction(Genome genome);
}
