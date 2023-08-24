package com.celso.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.celso.crudspring.entity.Course;
import com.celso.crudspring.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			String[] names = {"Java com Spring", "JS com Angular", "Python com Pandas"};
			String[] categories = {"Back End", "Front End", "Data Science"};

			for(int i = 0; i<3; i++){
				Course c = new Course();
				c.setName(names[i]);
				c.setCategory(categories[i]);

				courseRepository.save(c);
			}
		};
	}
}
