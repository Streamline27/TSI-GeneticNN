package genetic.v2.cost;

import genetic.v2.Chromosome;
import genetic.v2.utilities.CsvParser;
import genetic.v2.utilities.FitnessEvaluator;

import java.io.FileNotFoundException;

public class CostFunctionImageSquares implements FitnessEvaluator {

    private double[] targer;

    public CostFunctionImageSquares() {
        try {
            CsvParser parser = new CsvParser();
            targer = parser.parse("number3.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            targer = new double[1];
        }

    }

    @Override
    public Double computeFitness(Chromosome genome) {

        double[] code = genome.getImg();
        double sum = 0;
        for (int i = 0; i < code.length; i++) {
            sum += Math.abs((targer[i] - code[i])/255);
        }
        return (1 / sum);
    }

    @Override
    public Double computePrediction(Chromosome genome) {
        return computeFitness(genome);
    }

}
