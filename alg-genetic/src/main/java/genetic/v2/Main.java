package genetic.v2;

import genetic.v2.cost.CostFunctionImageSquares;
import genetic.v2.cost.CostFunctionNN;
import genetic.v2.utilities.FitnessEvaluator;
import neuralnet.NNModel;

public class Main {
    public static void main(String[] args) {

        FitnessEvaluator evaluator = new CostFunctionImageSquares();
        evaluator = new CostFunctionNN(new NNModel().loadFromFile("wallee.nn"));

        GeneticAlgorithm algorithm = new GeneticAlgorithm(evaluator);

        for (int i = 0; i < Const.NUMBER_OF_ITERATION; i++) {
            algorithm.performIteration();
        }
    }
}
