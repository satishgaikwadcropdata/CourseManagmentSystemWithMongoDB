package com.cropdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cropdata.controller.CourseController;
import com.cropdata.entity.Course;
import com.cropdata.repository.CourseRepository;
import com.cropdata.serviceimpl.CourseIMPL;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { TestCourseService.class })
public class TestCourseService {

	@Mock
	public CourseRepository courseRepository;

	@InjectMocks
	public CourseIMPL CourseIMPL;

	@InjectMocks
	CourseController controller;

	List<Course> courses;

	@Test
	@Order(1)
	public void tst_getAllCourse() {
		List<Course> courses = new ArrayList<>();

		Course course = new Course();

		course.setCId(1);
		course.setDescription("one month course");
		course.setName("Core java");

		Course course1 = new Course();
		course1.setCId(2);
		course1.setDescription("one month course");
		course1.setName("Adv Java");

		courses.add(course);
		courses.add(course1);
		when(courseRepository.findAll()).thenReturn(courses);
		assertEquals(2, CourseIMPL.getAllCourse().size());

	}

	@Test
	@Order(2)
	public void test_getCourseById() {

		Course course1 = new Course();
		course1.setCId(2);
		course1.setDescription("one month course");
		course1.setName("Adv Java");

		when(courseRepository.findById(2)).thenReturn(Optional.of(course1));
		String name = "Adv Java";
		Course actualCourse = CourseIMPL.findByCourseId(2);
		assertEquals(2, actualCourse.getCId());
		assertEquals(name, actualCourse.getName());
	}

	@Test
	@Order(3)
	public void test_getCourseByName() {

		Course course1 = new Course();
		course1.setCId(2);
		course1.setDescription("one month course");
		course1.setName("Adv Java");

		when(courseRepository.findByname("Adv Java")).thenReturn(course1);
		String name = "Adv Java";
		Course actualCourse = CourseIMPL.findByCourseName(name);
		assertEquals(2, actualCourse.getCId());
		assertEquals(name, actualCourse.getName());
	}

	@Test
	@Order(4)
	public void testCreateLanguage() {

		Course course1 = new Course();
		course1.setCId(2);
		course1.setDescription("one month course");
		course1.setName("Adv Java");

		// Mock the languageRepository.save() method
		when(courseRepository.save(any(Course.class))).thenReturn(course1);
		// Call the createLanguage() method
		Course result = CourseIMPL.saveCourse(course1);
		// Assert the result
		assertNotNull(result);
		assertEquals("Adv Java", result.getName());
	}

	@Test
	@Order(5)
	public void test__UpdateLanguageDetail() {
		// Given
		Integer cId = 2;

		Course course1 = new Course();
		course1.setCId(2);
		course1.setDescription("one month course");
		course1.setName("Adv Java");

		Optional<Course> optionalCourse = Optional.of(course1);
		Course courseUpdated = new Course();

		Course updatedCourse = new Course();
		updatedCourse.setCId(2);
		updatedCourse.setDescription("one month course");
		updatedCourse.setName("Core Java");
		when(courseRepository.findById(cId)).thenReturn(optionalCourse);
		when(courseRepository.save(course1)).thenReturn(courseUpdated);

		// When
		Course result = CourseIMPL.updateCourse(course1);

		// Then
		assertEquals(courseUpdated, result);
		verify(courseRepository).save(course1);
	}
}
