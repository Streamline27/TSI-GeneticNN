package lv.tti.web.dto;

import java.util.List;

public class GeneticResultDto {
    private Integer iterationNumber;
    private Double topScore;
    private Double topFitness;

    private List<ChromosomeDto> topChromosomes;

    public GeneticResultDto(Integer iterationNumber, Double topScore, Double topFitness, List<ChromosomeDto> topChromosomes) {
        this.iterationNumber = iterationNumber;
        this.topScore = topScore;
        this.topChromosomes = topChromosomes;
        this.topFitness = topFitness;
    }

    public Integer getIterationNumber() {
        return iterationNumber;
    }

    public void setIterationNumber(Integer iterationNumber) {
        this.iterationNumber = iterationNumber;
    }

    public Double getTopScore() {
        return topScore;
    }

    public void setTopScore(Double topScore) {
        this.topScore = topScore;
    }

    public List<ChromosomeDto> getTopChromosomes() {
        return topChromosomes;
    }

    public void setTopChromosomes(List<ChromosomeDto> topChromosomes) {
        this.topChromosomes = topChromosomes;
    }

    public Double getTopFitness() {
        return topFitness;
    }

    public void setTopFitness(Double topFitness) {
        this.topFitness = topFitness;
    }
}
