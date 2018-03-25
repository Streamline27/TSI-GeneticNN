package genetic.v2;

import genetic.v2.utilities.FitnessEvaluator;
import genetic.v2.utilities.ImageGenerator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Chromosome {

    private final String name;
    private final Double score;
    private final List<Gene> code;
    private final double[] img;

    public Chromosome(String name, List<Gene> code, FitnessEvaluator evaluator) {
        this.name = name;
        this.code = sorted(code);
        this.img = ImageGenerator.fromCode(code);
        this.score = evaluator.computeFitness(this);
    }

    public Chromosome.Score createScore(Double value) {
        return new Chromosome.Score(this, value);
    }

    public String getName() {
        return name;
    }

    public Double getScore() {
        return score;
    }

    public List<Gene> getCode() {
        return code;
    }

    public double[] getImg() {
        return img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chromosome that = (Chromosome) o;
        return Arrays.equals(img, that.img);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(img);
    }

    public final class Score {
        private final Chromosome chromosome;
        private Double value;

        public Chromosome getChromosome() {
            return chromosome;
        }

        public Double getValue() {
            return value;
        }

        public Score(Chromosome genome, Double value) {
            this.chromosome = genome;
            this.value = value;
        }

        public Chromosome.Score increasedBy(Double value) {
            this.value += value;
            return this;
        }

        public Chromosome.Score dividedBy(Double value) {
            this.value /= value;
            return this;
        }

        public Chromosome.Score decreasedBy(Double value) {
            this.value -= value;
            return this;
        }
    }

    private List<Gene> sorted(List<Gene> code) {
        return code.stream().sorted(Comparator.comparing(Gene::sortKey)).collect(Collectors.toList());
    }
}
