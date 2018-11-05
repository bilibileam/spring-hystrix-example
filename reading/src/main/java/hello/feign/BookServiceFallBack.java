package hello.feign;

public class BookServiceFallBack implements FeignClientBookService {
	
	private final Throwable cause;
	
	public BookServiceFallBack(Throwable throwable) {
		this.cause = throwable;
	}

	@Override
	public String readingList() {
		return "Cloud Native Java (O'Reilly)";
	}
}
