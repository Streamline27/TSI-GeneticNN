package lv.tti.web.dto;

public class ChromosomeDto {
    private String name;
    private Double score;
    private double[] code;

    public ChromosomeDto(String name, Double score, double[] code) {
        this.name = name;
        this.score = score;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public double[] getCode() {
        return code;
    }

    public void setCode(double[] code) {
        this.code = code;
    }
}
