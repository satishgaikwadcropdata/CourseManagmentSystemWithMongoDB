package com.cropdata.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Writer {

	@Id
	private Integer wId;

	private String name;

//	@OneToMany(mappedBy = "writer", cascade = CascadeType.ALL)
//	private List<Course> courses;

	private Course course;

	private Integer cId;
}
