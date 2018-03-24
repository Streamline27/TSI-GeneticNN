package genetic.utilities;

import genetic.Constants;
import genetic.Genome;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Statistics {
    private int currentIteration;
    private List<Record> iterationRecords;

    public Statistics() {
        currentIteration = 0;
        this.iterationRecords = new ArrayList<>();
        this.iterationRecords.add(new Record(0));
    }

    public void increaseIteration() {
        currentIteration++;
        this.iterationRecords.add(new Record(currentIteration));
    }

    public void recordBestGenes(List<Genome> genes){
        currentRecord().setBest(genes.stream()
                .sorted(Comparator.comparing(Genome::getFitness).reversed())
                .limit(Constants.NUM_BEST_GENES_TO_RECORD)
                .collect(Collectors.toList()));
    }

    public void recordBestScore(Double score) {
        currentRecord().setBestScore(score);
    }

    public Record getCurrentRecord() {
        return getRecordFor(currentIteration);
    }

    public Record getRecordFor(int iteration) {
        return this.iterationRecords.get(iteration);
    }

    public void log() {
        System.out.println(MessageFormat.format("Iteration: {0}, score: {1}, elite: [{2}, {3}, {4}]",
                currentRecord().iteration,
                currentRecord().bestScore,
                currentRecord().getBestGenomes().get(0).getNickname(),
                currentRecord().getBestGenomes().get(1).getNickname(),
                currentRecord().getBestGenomes().get(2).getNickname())
        );
    }


    public int getCurrentIteration() {
        return currentIteration;
    }

    private Record currentRecord() {
        return getRecordFor(this.currentIteration);
    }

    public class Record{
        private int iteration;
        private Double bestScore;
        private List<Genome> best;

        public Record() {
            this.iteration = 0;
            this.bestScore = 0.0;
            this.best = new ArrayList<>();
        }

        public Record(int iteration) {
            this.iteration = iteration;
            this.bestScore = 0.0;
            this.best = new ArrayList<>();
        }

        public int getIteration() {
            return iteration;
        }

        public void setIteration(int iteration) {
            this.iteration = iteration;
        }

        public Double getBestScore() {
            return bestScore;
        }

        public void setBestScore(Double bestScore) {
            this.bestScore = bestScore;
        }

        public List<Genome> getBestGenomes() {
            return best;
        }

        public void setBest(List<Genome> best) {
            this.best = best;
        }
    }

}
