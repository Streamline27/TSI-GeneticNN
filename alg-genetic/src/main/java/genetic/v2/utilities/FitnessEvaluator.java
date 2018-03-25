package genetic.v2.utilities;

import genetic.v2.Chromosome;

public interface FitnessEvaluator {
    Double computeFitness(Chromosome genome);
    Double computePrediction(Chromosome genome);
}
