package lv.tti.web.dto;

public class EvaluateResultDto {
    private Integer number;

    public EvaluateResultDto(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
