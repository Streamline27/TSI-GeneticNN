package lv.tti.web;

import genetic.Constants;
import neuralnet.NNModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class WebApplication {

	private final String AI_GENETIC = Constants.MODEL_NAME; //"wallee.nn"
	private final String AI_NEURALNET = "skynet40k2k.nn";

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}

	@Bean
	@Qualifier("wallee")
	public NNModel nnModelWalle() {
		return new NNModel().loadFromFile(AI_GENETIC);
	}

	@Bean
	@Qualifier("skynet")
	public NNModel nnModelSkynet() {
		return new NNModel().loadFromFile(AI_NEURALNET);
	}

}
