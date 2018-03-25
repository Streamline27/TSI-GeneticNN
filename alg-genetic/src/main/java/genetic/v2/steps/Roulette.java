package genetic.v2.steps;

import genetic.v2.Chromosome;
import genetic.v2.utilities.RandomGen;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import static genetic.v2.utilities.Utils.firstOf;

public class Roulette {

    private List<Chromosome.Score> sectors;

    public Roulette(List<Chromosome> population) {

        this.sectors = getSectors(getRelativeScores(population));
    }

    public Chromosome select() {

        Double chance = RandomGen.getRandonDouble();
        int i = 0;
        while (sectors.get(i).getValue() < chance) i++;
        return sectors.get(i).getChromosome();
    }


    private List<Chromosome.Score> getSectors(List<Chromosome.Score> scores) {

        List<Chromosome.Score> sectors = new ArrayList<>();

        sectors.add(firstOf(scores));
        for (int i = 1; i < scores.size(); i++) {
            sectors.add(scores.get(i).increasedBy(sectors.get(i - 1).getValue()));
        }

        return sectors;
    }

    private List<Chromosome.Score> getRelativeScores(List<Chromosome> population) {

        Double min = Math.abs(getMin(population));

        List<Chromosome.Score> correctedScores = population.stream()
                .map(chromosome -> chromosome.createScore(chromosome.getScore() + min))
                .collect(Collectors.toList());

        Double sum =  correctedScores.stream()
                .mapToDouble(Chromosome.Score::getValue)
                .sum();

        return correctedScores.stream()
                .map(score -> score.dividedBy(sum))
                .collect(Collectors.toList());
    }

    private Double getMin(List<Chromosome> population) {
        Double min = population.stream().mapToDouble(Chromosome::getScore).min().orElse(0);
        return min < 0 ? min : 0;
    }



}
