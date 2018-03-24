package lv.tti.web;

import genetic.Constants;
import genetic.GeneticAlgorithm;
import genetic.utilities.Statistics;
import neuralnet.NNModel;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;
import java.util.logging.Logger;

@RestController
public class GeneticController {

    @Qualifier("wallee")
    @Autowired private NNModel nnModel;

    @RequestMapping(value = "/genalg", produces = MediaType.TEXT_EVENT_STREAM_VALUE, method = RequestMethod.GET)
    public Flux<GeneticAlgorithm.IterationResult> genAlg() {

        GeneticAlgorithm algorithm = new GeneticAlgorithm(nnModel);

        return Flux.range(0, Constants.NUM_ITERATION)
                .map(next -> algorithm.runIteration());

    }

    @RequestMapping("/hello")
    public String hello() {
        return "Hello";
    }
}

