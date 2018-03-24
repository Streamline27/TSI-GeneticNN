package genetic;

import neuralnet.NNModel;

public class Main {
    public static void main(String[] args) {
        NNModel model = new NNModel().loadFromFile("wallee.nn");
        GeneticAlgorithm algorithm = new GeneticAlgorithm(model);

        for (int i = 0; i < Constants.NUM_ITERATION; i++) {
            algorithm.runIteration();
        }

        System.out.println("Done");
    }
}
