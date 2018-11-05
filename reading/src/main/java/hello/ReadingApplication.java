package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hello.feign.FeignClientBookService;

@EnableCircuitBreaker
@EnableFeignClients
@RestController
@SpringBootApplication
public class ReadingApplication {

	@Autowired
	private BookService bookService;

	@Autowired
	private FeignClientBookService feignClientBookService;
	
	@Bean
	public RestTemplate rest(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(1200000)
				.setReadTimeout(700000).
				build();
	}

	@RequestMapping("/to-readf")
	public String toReadUsingFeign() {
		return feignClientBookService.readingList();
	}
	
	@RequestMapping("/to-read")
	public String toRead() {
		return bookService.readingList();
	}

	public static void main(String[] args) {
		SpringApplication.run(ReadingApplication.class, args);
	}
}
