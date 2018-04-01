package lv.tti.web;

import genetic.v2.Const;
import genetic.v2.GeneticAlgorithm;
import genetic.v2.cost.CostFunctionImageSquares;
import genetic.v2.cost.CostFunctionNN;
import lv.tti.web.dto.ChromosomeDto;
import lv.tti.web.dto.GeneticResultDto;
import neuralnet.NNModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class GeneticController {

    @Qualifier("wallee")
    @Autowired private NNModel nnModel;

    @RequestMapping(value = "/genalg", produces = MediaType.TEXT_EVENT_STREAM_VALUE, method = RequestMethod.GET)
    public Flux<GeneticResultDto> genAlg()  {

        final int numberOfIterations = Const.NUMBER_OF_ITERATION;
//        final GeneticAlgorithm algorithm = new GeneticAlgorithm(new CostFunctionImageSquares());
        final GeneticAlgorithm algorithm = new GeneticAlgorithm(new CostFunctionNN(nnModel));

        return Flux.just(new Object())
                .repeat(numberOfIterations)
                .map(next -> convert(algorithm.performIteration()));
    }

    private GeneticResultDto convert(GeneticAlgorithm.IterationResult iterationResult) {

        return new GeneticResultDto(
                iterationResult.getIteration(),
                iterationResult.getBestScore(),
                iterationResult.getBestFitness(),
                iterationResult.getLeaderboard().stream()
                        .map(chr -> new ChromosomeDto(chr.getName(), chr.getScore(), chr.getImg()))
                        .collect(Collectors.toList())
        );

    }
}

