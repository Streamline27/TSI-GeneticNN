package genetic;

import neuralnet.NNModel;

public class Main {
    public static void main(String[] args) {
        GeneticAlgorithm algorithm = new GeneticAlgorithm(new NNModel().loadFromFile("wallee.nn"));

        for (int i = 0; i < Constants.NUM_ITERATION; i++) {
            algorithm.runIteration();
        }
    }
}
