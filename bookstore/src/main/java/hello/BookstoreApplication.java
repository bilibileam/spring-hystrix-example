package hello;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class BookstoreApplication {

	private static final Logger logger = LogManager.getLogger(BookstoreApplication.class);
	
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(15100)
				.setReadTimeout(15100).
				build();
	}
	
	@RequestMapping(value = "/recommended")
	public String readingList(){
		logger.info("Start Running slowly");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Finished");
		return "Spring in Action (Manning), Cloud Native Java (O'Reilly), Learning Spring Boot (Packt)";
	}

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
}
