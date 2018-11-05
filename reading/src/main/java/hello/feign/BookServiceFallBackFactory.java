package hello.feign;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class BookServiceFallBackFactory implements FallbackFactory<FeignClientBookService>{

	@Override
	public FeignClientBookService create(Throwable throwable) {
		return new BookServiceFallBack(throwable);
	}

}
