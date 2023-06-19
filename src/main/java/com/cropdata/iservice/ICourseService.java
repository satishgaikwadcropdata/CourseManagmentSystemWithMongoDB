package com.cropdata.iservice;

import java.util.List;

import com.cropdata.entity.Course;

public interface ICourseService {

	public Course saveCourse(Course course);

	public List<Course> getAllCourse();

	public Course updateCourse(Course course);

	public Course deleteCourseById(Integer cId);

	public Course findByCourseId(Integer cId);

	public Course findByCourseName(String courseName);

}
