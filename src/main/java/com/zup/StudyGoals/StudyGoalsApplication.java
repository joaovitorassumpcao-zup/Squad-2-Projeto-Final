package com.zup.StudyGoals;

import com.zup.StudyGoals.view.MenuPrincipal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableWebMvc
@SpringBootApplication
public class StudyGoalsApplication {

	public static void main(String[] args) {
		//SpringApplication.run(StudyGoalsApplication.class, args);

		ConfigurableApplicationContext context = new SpringApplicationBuilder(StudyGoalsApplication.class)
				.headless(false)
				.run(args);
		context.getBean(MenuPrincipal.class);
	}

}
