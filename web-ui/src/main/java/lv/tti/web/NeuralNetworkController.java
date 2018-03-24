package lv.tti.web;

import lv.tti.web.dto.EvaluateDto;
import lv.tti.web.dto.EvaluateResultDto;
import neuralnet.NNModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NeuralNetworkController {

    @Qualifier("skynet")
    @Autowired private NNModel model;

    @PostMapping("/evaluate")
    public EvaluateResultDto evaluate(@RequestBody EvaluateDto dto) {

        double[] prediction = model.predict(convert(dto.getImage()));

        double max = 0;
        Integer index = 0;
        for (int i = 0; i < prediction.length; i++) {
            if (prediction[i] > max) {
                max = prediction[i];
                index = i;
            }
        }

        return new EvaluateResultDto(index);
    }

    private double[] convert(List<Double> list) {
        double[] res = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

}
