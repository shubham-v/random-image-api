package hackerearth.contest.randomimage.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RandomImageApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RandomImageApiApplication.class, args);
	}

}
