package com.cropdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cropdata.customeresponse.CustomeResponse;
import com.cropdata.dto.CourseDTO;
import com.cropdata.entity.Course;
import com.cropdata.exception.CourseNotFoundException;
import com.cropdata.iservice.ICourseService;
import com.cropdata.mapper.CourseMapper;

@RestController
@RequestMapping("/course")
public class CourseController {

	private String code;

	private Object data;

	@Autowired(required = true)
	private ICourseService iCourseService;

	@Autowired
	private CourseMapper courseMapper;

	@SuppressWarnings("finally")
	@PostMapping()
	public ResponseEntity<?> createCourse(@RequestBody CourseDTO courseDTO) {
		Course course = new Course();
		try {
			course = iCourseService.saveCourse(courseMapper.toCourse(courseDTO));
			data = course;
			code = "CREATED";
		} catch (CourseNotFoundException courseNotFoundException) {
			data = null;
			code = "DATA_NOT_CREATED";
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllCourse() {

		List<Course> coursesList = iCourseService.getAllCourse();
		List<Course> courses = new ArrayList<>();
		try {

			for (Course course : coursesList) {
				courses.add(course);
			}
			data = courses;
			code = "SUCCESS";
		} catch (CourseNotFoundException courseNotFoundException) {
			code = "DATA_NOT_FOUND";
			data = null;
		} catch (RuntimeException runtimeException) {
			code = "RUNTIME_EXCEPTION";
			data = null;
		} catch (Exception exception) {
			code = "EXCEPTION";
			data = null;
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@PutMapping("/update")
	public ResponseEntity<?> updateCourse(@RequestBody CourseDTO courseDTO) {

		Course course = null;
		try {
			course = iCourseService.updateCourse(courseMapper.toCourse(courseDTO));
			data = course;
		} catch (CourseNotFoundException courseNotFoundException) {
			data = null;
			code = "UPDATE_FAILED";
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@DeleteMapping("{cId}")
	public ResponseEntity<?> deleteCourseById(@PathVariable Integer cId) {
		Course id = null;
		try {
			id = iCourseService.deleteCourseById(cId);
			data = id;
			code = "SUCCESS";
		} catch (CourseNotFoundException courseNotFoundException) {
			data = null;
			code = "DELETE_FAILED" + id;
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@GetMapping("{cId}")
	public ResponseEntity<?> findByCourseId(@PathVariable Integer cId) {

		Course course = iCourseService.findByCourseId(cId);
		CourseDTO courseDTO = new CourseDTO();
		try {

			courseDTO = courseMapper.toCourseDTO(course);
			data = courseDTO;
			code = "SUCCESS";

		} catch (CourseNotFoundException courseNotFoundException) {
			data = null;
			code = "CUSTOMER_NOT_FOUND_ERROR";
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@GetMapping("/findbyname/{CourseName}")
	public ResponseEntity<?> getByName(@PathVariable String CourseName) {
		Course course = iCourseService.findByCourseName(CourseName);
		CourseDTO courseDTO = new CourseDTO();
		try {
			courseDTO = courseMapper.toCourseDTO(course);
			data = courseDTO;
			code = "SUCCESS";
		} catch (CourseNotFoundException customerNotFoundException) {
			data = null;
			code = "CUSTOMER_NOT_FOUND_ERROR";
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}
}
