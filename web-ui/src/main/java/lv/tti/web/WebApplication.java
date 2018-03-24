package lv.tti.web;

import genetic.Constants;
import neuralnet.NNModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

	private final String AI_GENETIC = Constants.MODEL_NAME; //"wallee.nn"
	private final String AI_NEURALNET = "skynet1.nn";

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	@Qualifier("wallee")
	public NNModel nnModelWalle() {
		return new NNModel().loadFromFile(Constants.MODEL_NAME);
	}

	@Bean
	@Qualifier("skynet")
	public NNModel nnModelSkynet() {
		return new NNModel().loadFromFile(AI_NEURALNET);
	}
}
