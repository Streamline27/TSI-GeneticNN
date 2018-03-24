package genetic.cost;

import genetic.Constants;
import genetic.Genome;
import genetic.utilities.FitnessEvaluator;
import neuralnet.NNModel;

import static genetic.Constants.COLOR_BLACK;
import static genetic.Constants.COLOR_WHITE;
import static genetic.Constants.DIGIT_TO_DRAW;

public class CostFunctionNN implements FitnessEvaluator{

    private NNModel nnModel;

    public CostFunctionNN(NNModel nnModel) {
        this.nnModel = nnModel;
    }

    @Override
    public Double computeFitness(Genome genome) {

        double[] code = genome.getCode();
        double[] prediction = nnModel.predict(code);

        if (Constants.COMPOSITE_COST_FUNCTION_ON) {

            double colorRation = getColorRation(code);

            double sum = 0;
            for (double digitChance : prediction) {
                sum = digitChance;
            }


            return (sum + (prediction[DIGIT_TO_DRAW]*2)) * colorRation;
        }
        else
        {
            return prediction[DIGIT_TO_DRAW];
        }

    }

    private double getColorRation(double[] code) {
        double numBlack = count(COLOR_BLACK, code);
        double numWhite = count(COLOR_WHITE, code);

        double colorRation = 1;
        if (Constants.MINIMIZE_BLACK_MODE_ON) {
            colorRation = (numWhite / code.length);
        }
        return colorRation;
    }

    @Override
    public Double computePrediction(Genome genome) {
        return nnModel.predict(genome.getCode())[DIGIT_TO_DRAW];
    }


    private int count(Double color, double[] code) {
        int c = 1;
        for (int i = 0; i < code.length; i++) {
            if (code[i] == color) c++;
        }
        return c;
    }
}
