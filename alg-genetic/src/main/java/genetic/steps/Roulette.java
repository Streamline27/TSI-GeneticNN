package genetic.steps;

import genetic.utilities.RandomGen;
import genetic.Genome;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static genetic.utilities.Utils.firstOf;

public class Roulette {

    private List<Genome.Score> sectors;

    public Roulette(List<Genome> population) {

        this.sectors = getSectors(getRelativeScores(population));
    }

    public Genome select() {

        Double chance = RandomGen.getRandonDouble();
        int i = 0;
        while (sectors.get(i).getValue() < chance) i++;
        return sectors.get(i).getGenome();
    }

    public int selectIndex() {

        Double chance = RandomGen.getRandonDouble();
        int i = 0;
        while (sectors.get(i).getValue() < chance) i++;
        return i;
    }


    private List<Genome.Score> getSectors(List<Genome.Score> scores) {

        List<Genome.Score> sectors = new ArrayList<>();

        sectors.add(firstOf(scores));
        for (int i = 1; i < scores.size(); i++) {
            sectors.add(scores.get(i).increasedBy(sectors.get(i - 1).getValue()));
        }

        return sectors;
    }

    private List<Genome.Score> getRelativeScores(List<Genome> population) {
        Double minFitness = getMin(population);
        Double sum = population.stream().mapToDouble(Genome::getFitness).sum();

        return population.stream()
                .map(genome -> genome.createScore((genome.getFitness() + Math.abs(minFitness))/sum))
                .collect(Collectors.toList());
    }

    private Double getMin(List<Genome> population) {
        Double min = population.stream().mapToDouble(Genome::getFitness).min().orElse(0);
        return min < 0 ? min : 0;
    }



}
