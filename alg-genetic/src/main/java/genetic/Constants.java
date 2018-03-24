package genetic;

public class Constants {

    public static final String MODEL_NAME = "wallee.nn";
//    public static final String MODEL_NAME = "modelCool.nn";

    public static final boolean BLACK_AND_WHITE_MODE_ON = true;
    public static final boolean MINIMIZE_BLACK_MODE_ON = true;
    public static final boolean COMPOSITE_COST_FUNCTION_ON = false;
    public static final boolean ROULETTE_IN_CROSSOVER_ON = true;
    public static final boolean NEURAL_NETWORK_EVALUATION = false;

    public static final int DIGIT_TO_DRAW = 8;
    public static final int NUM_BEST_GENES_TO_RECORD = 24;

    public static final int NUM_ITERATION = 800;
    public static final int POPULATION_SIZE = 400;
    public static final int POPULATION_INCREASE = 400;

    public static final Double GENOME_MUTATION_CHANCE = 0.3;
    public static final Double GENE_MUTATION_CHANCE = 0.5;

    public static final int ELITE_COUNT = 16;

    public static final int GENE_NAME_LENGTH = 3;
    public static final int GENOME_LENGTH = 784;


    public static Double COLOR_BLACK = 255.0;
    public static Double COLOR_WHITE = 0.0;
}
