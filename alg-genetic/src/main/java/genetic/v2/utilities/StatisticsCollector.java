package genetic.v2.utilities;

import genetic.v2.Chromosome;
import genetic.v2.Const;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsCollector {

    private List<Record> iterationRecords;
    private int iteration = 0;

    public StatisticsCollector() {
        iterationRecords = new ArrayList<>();
        iterationRecords.add(new Record(0));
    }

    public void nextIteration() {
        iteration++;
        iterationRecords.add(new Record(iteration));
    }

    public void saveTopScore(Double topScore) {
        this.iterationRecords.get(iteration).topScore = topScore;
    }

    public void saveTopFitness(Double topFitness) {
        this.iterationRecords.get(iteration).topFitness = topFitness;
    }

    public void saveLeaders(List<Chromosome> population) {

        this.iterationRecords.get(iteration).leaderboard = population.stream()
                .sorted(Comparator.comparing(Chromosome::getScore).reversed())
                .limit(Const.RESULTS_TO_SHOW)
                .collect(Collectors.toList());
    }

    public void saveCodeLengthStatistics(List<Chromosome> chromosomes) {

        DoubleSummaryStatistics stats = chromosomes.stream()
                .mapToDouble(c -> c.getCode().size())
                .summaryStatistics();

        this.iterationRecords.get(iteration).avgChromosomeLength = stats.getAverage();
        this.iterationRecords.get(iteration).maxChromosomeLength = stats.getMax();
        this.iterationRecords.get(iteration).minChromosomeLength = stats.getMin();
    }

    public Record currently() {
        return iterationRecords.get(iteration);
    }



    public void log() {
        Record record = iterationRecords.get(iteration);
        System.out.println(MessageFormat.format("Itetation: {0}, best score: {1}, leaders: [{2}, {3}, {4}], length: [ AVG={5}, MAX={6}, MIN={7}, BEST_SIZE={8}]",
                record.iteration,
                record.topScore,
                record.leaderboard.get(0).getName(),
                record.leaderboard.get(1).getName(),
                record.leaderboard.get(2).getName(),
                record.avgChromosomeLength,
                record.maxChromosomeLength,
                record.minChromosomeLength,
                record.leaderboard.get(0).getCode().size()));
    }

    public Integer iteration() {
        return iteration;
    }

    public class Record {

        private int iteration;
        private double topScore;
        private double topFitness;
        private List<Chromosome> leaderboard;
        private double avgChromosomeLength;
        public double maxChromosomeLength;
        public double minChromosomeLength;

        public Record(int iteration) {
            this.iteration = iteration;
        }

        public double topScore() {
            return topScore;
        }

        public List<Chromosome> leaderboard() {
            return leaderboard;
        }

        public double avgChromosomeLength() {
            return avgChromosomeLength;
        }

        public double maxChromosomeLength() {
            return maxChromosomeLength;
        }

        public double minChromosomeLength() {
            return minChromosomeLength;
        }

        public double topFitness() {
            return topFitness;
        }
    }

}

