package com.celso.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.celso.crudspring.entities.Course;
import com.celso.crudspring.entities.Lesson;
import com.celso.crudspring.enums.Category;
import com.celso.crudspring.repositories.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			String[] names = {"Java com Spring", "JS com Angular", "Python com FastAPI"};
			Category[] categories = {Category.BACKEND, Category.FRONTEND, Category.BACKEND};

			for(int i = 0; i<3; i++){
				Course c = new Course();
				c.setName(names[i]);
				c.setCategory(categories[i]);
				Lesson l = new Lesson();
				l.setName("Intro");
				l.setYtUrl("URL");
				l.setCourse(c);
				c.getLessons().add(l);
				courseRepository.save(c);
			}
		};
	}
}
