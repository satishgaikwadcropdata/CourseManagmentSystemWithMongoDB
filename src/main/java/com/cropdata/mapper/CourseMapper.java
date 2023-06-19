package com.cropdata.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cropdata.dto.CourseDTO;
import com.cropdata.entity.Course;

@Mapper(componentModel = "spring", uses = { LanguageMapper.class, WriterMapper.class })
public interface CourseMapper {

//	@Mapping(source = "writerDTOs", target = "writer")
//	@Mapping(source = "languageDTOs", target = "language")
	public Course toCourse(CourseDTO courseDTO);

	@Mapping(source = "writer", target = "writerDTOs")
	@Mapping(source = "language", target = "languageDTOs")
	public CourseDTO toCourseDTO(Course course);
}
