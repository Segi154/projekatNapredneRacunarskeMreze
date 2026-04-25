package rs.ac.bg.fon.projekat_nrt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ProjekatNrtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjekatNrtApplication.class, args);
	}

}
