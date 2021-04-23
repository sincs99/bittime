package ch.bittime.bittime;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author Pascal
 */

@SpringBootApplication


public class BittimeApplication {

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}



	public static void main(String[] args) {
		SpringApplication.run(BittimeApplication.class, args);
	}

}