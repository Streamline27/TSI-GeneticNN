package lv.tti.web;

import neuralnet.NNModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

	private final String AI_SKYNET = "models/skynet40k2k.nn";
	private final String AI_WINSTON = "models/winston.nn";
	private final String AI_WALLEE = "models/wallee.nn";

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	@Qualifier("wallee")
	public NNModel nnModelWalle() {
		return new NNModel().loadFromFile(AI_WALLEE);
	}

	@Bean
	@Qualifier("skynet")
	public NNModel nnModelSkynet() {
		return new NNModel().loadFromFile(AI_SKYNET);
	}

	@Bean
	@Qualifier("winston")
	public NNModel nnModelWinston() {
		return new NNModel().loadFromFile(AI_WINSTON);
	}

}
