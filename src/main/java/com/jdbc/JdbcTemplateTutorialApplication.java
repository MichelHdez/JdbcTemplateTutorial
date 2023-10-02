package com.jdbc;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jdbc.dao.DAO;
import com.jdbc.model.Course;

@SpringBootApplication
public class JdbcTemplateTutorialApplication {

	private static DAO<Course> dao;

	public JdbcTemplateTutorialApplication(DAO<Course> dao) {
		this.dao = dao;
	}

	public static void main(String[] args) {
		SpringApplication.run(JdbcTemplateTutorialApplication.class, args);

		System.out.println("\nOne Course -------------------------------------\n");
		Optional<Course> course = dao.get(1);
		System.out.println(course.get());

		System.out.println("\nCreate Course -------------------------------------\n");
		Course springVue = new Course(6, "Spring Boot + Vue", "New Course", "http://www.danvega.dev/courses");
		dao.create(springVue);

		springVue.setDescription("Learn to build Vue apps that talk to Spring");
		dao.update(springVue, 6);

		dao.delete(4);

		System.out.println("\nAll Courses -------------------------------------\n");
		List<Course> courses = dao.list();
		courses.forEach(System.out::println);
	}
}
