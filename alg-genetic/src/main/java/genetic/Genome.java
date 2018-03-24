package genetic;

import genetic.utilities.FitnessEvaluator;
import genetic.utilities.RandomGen;

import java.util.Arrays;

public class Genome {
    private double[] code;
    private String nickname;

    private Double fitness;

    public Genome(FitnessEvaluator evaluator) {
        this(RandomGen.getRandomCode(), RandomGen.getRandomName(), evaluator);
    }

    public Genome(double[] code, FitnessEvaluator evaluator) {
        this.code = code;
        this.fitness = evaluator.computeFitness(this);
    }

    public Genome(double[] code, String nickname, FitnessEvaluator evaluator) {
        this.code = code;
        this.nickname = nickname;
        this.fitness = evaluator.computeFitness(this);
    }

    public double[] getCode() {
        return code;
    }

    public void setCode(double[] code) {
        this.code = code;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public Double getFitness() {
        return fitness;
    }

    public void setFitness(Double fitness) {
        this.fitness = fitness;
    }

    public Score createScore(Double value) {
        return new Score(this, value);
    }

    public final class Score {
        private Genome genome;
        private Double value;

        public Score(Genome genome, Double value) {
            this.genome = genome;
            this.value = value;
        }

        public Score increasedBy(Double value) {
            this.value+=value;
            return this;
        }

        public Genome getGenome() {
            return genome;
        }

        public void setGenome(Genome genome) {
            this.genome = genome;
        }

        public Double getValue() {
            return value;
        }

        public void setValue(Double value) {
            this.value = value;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genome genome = (Genome) o;
        return Arrays.equals(code, genome.code);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(code);
    }
}
