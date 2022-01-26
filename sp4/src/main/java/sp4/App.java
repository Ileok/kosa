package sp4;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;

public class App {
	@RequestMapping("/")
	public String home() {
		return "hello world";
	}
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
