package hello;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class BookService {

	private final RestTemplate restTemplate;

	public BookService(RestTemplate rest) {
		this.restTemplate = rest;
	}

	@HystrixCommand(
		commandKey = "readingList",
		fallbackMethod = "reliable",
		commandProperties = {
			@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="4000")
		},
		threadPoolProperties = {
			@HystrixProperty(name="coreSize", value="1"),
			@HystrixProperty(name="maxQueueSize", value="-1")
		}
	)
	public String readingList() {
		URI uri = URI.create("http://localhost:8090/recommended");

		return this.restTemplate.getForObject(uri, String.class);
	}

	public String reliable() {
		return "Cloud Native Java (O'Reilly)";
	}

}
