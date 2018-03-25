package genetic.v2.cost;

import genetic.v2.Chromosome;
import genetic.v2.utilities.FitnessEvaluator;
import neuralnet.NNModel;

import java.util.Arrays;


public class CostFunctionNN implements FitnessEvaluator {

    private NNModel nnModel;

    public CostFunctionNN(NNModel nnModel) {
        this.nnModel = nnModel;
    }

    @Override
    public Double computeFitness(Chromosome chromosome) {

        double[] img = chromosome.getImg();
        double[] prediction = nnModel.predict(img);

        double sum = Arrays.stream(prediction).sum();

        return prediction[3]*2 - sum;
    }

    @Override
    public Double computePrediction(Chromosome chromosome) {

        double[] img = chromosome.getImg();
        double[] prediction = nnModel.predict(img);

        return prediction[3];
    }

}
