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
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.websocket.server.PathParam;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class GeneticController {

    @Qualifier("skynet")
    @Autowired private NNModel nnModel;

    @RequestMapping(value = "/genalg", produces = MediaType.TEXT_EVENT_STREAM_VALUE, method = RequestMethod.GET)
    public Flux<GeneticResultDto> genAlg()  {

        GeneticAlgorithm algorithm = new GeneticAlgorithm(new CostFunctionNN(nnModel));

        return Mono.just(new Object())
                .repeat(Const.NUMBER_OF_ITERATION)
                .map(next -> convert(algorithm.performIteration()));
    }

    private GeneticResultDto convert(GeneticAlgorithm.IterationResult iterationResult) {

        return new GeneticResultDto(
                iterationResult.getIteration(),
                iterationResult.getBestScore(),
                iterationResult.getBestFitness(),
                iterationResult.getLeaderboard().stream()
                        .map(chrom -> new ChromosomeDto(chrom.getName(), chrom.getScore(), chrom.getImg()))
                        .collect(Collectors.toList())
        );

    }
}

