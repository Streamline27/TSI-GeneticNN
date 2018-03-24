package genetic.cost;

import genetic.Constants;
import genetic.Genome;
import genetic.utilities.CsvParser;
import genetic.utilities.FitnessEvaluator;

import java.io.FileNotFoundException;

import static genetic.Constants.COLOR_WHITE;

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
    public Double computeFitness(Genome genome) {

        double[] code = genome.getCode();
        double sum = 0;
        for (int i = 0; i < code.length; i++) {
            sum += Math.pow((targer[i] - code[i]), 2);
        }
        return (1 / sum) * getColorRation(code);
    }

    @Override
    public Double computePrediction(Genome genome) {
        double[] code = genome.getCode();
        double sum = 0;
        for (int i = 0; i < code.length; i++) {
            sum += Math.pow((targer[i] - code[i]), 2);
        }
        return sum / code.length;
    }


    private double getColorRation(double[] code) {
        if (Constants.MINIMIZE_BLACK_MODE_ON) {
            double numWhite = count(COLOR_WHITE, code);
            return (numWhite / code.length);
        }
        else {
            return 1;
        }

    }

    private int count(Double color, double[] code) {
        int c = 1;
        for (int i = 0; i < code.length; i++) {
            if (code[i] == color) c++;
        }
        return c;
    }
}
