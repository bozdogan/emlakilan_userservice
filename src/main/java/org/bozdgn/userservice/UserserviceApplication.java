package org.bozdgn.userservice;

import org.bozdgn.userservice.dto.UserInput;
import org.bozdgn.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class UserserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserserviceApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	// NOTE(bora): This causes problems on some tests :/
	@Bean
	CommandLineRunner setupTestUsers(UserService userService) {
		return (args -> {
			userService.deleteUserByUsername("user");
			userService.deleteUserByUsername("admin");
			userService.save(new UserInput(
					"user",
					"test",
					"user@example.com",
					false,
					null, null, null));
			userService.save(new UserInput(
					"admin",
					"test",
					"admin@example.com",
					true,
					null, null, null));

			userService.deleteUserByUsername("doctorx");
			userService.deleteUserByUsername("stinson");
			userService.deleteUserByUsername("lilypad");
			userService.save(new UserInput(
					"doctorx",
					"tracy",
					"tmosby@aol.com",
					false,
					"Ted",
					"Mosby",
					"10120220716"));
			userService.save(new UserInput(
					"stinson",
					"suitup",
					"legen@waitforit.dary",
					false,
					"Barney",
					"Stinson",
					"10220220716"));
			userService.save(new UserInput(
					"lilypad",
					"marvin",
					"lilyeriksen@aol.com",
					true,
					"Lily",
					"Aldrin",
					"99920220716"));
		});
	}
}
