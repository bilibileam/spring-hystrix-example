package hello.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "read", url = "http://localhost:8090/", fallbackFactory = BookServiceFallBackFactory.class)
public interface FeignClientBookService {

	@PostMapping(value = "/recommended")
	String readingList();
	
}
