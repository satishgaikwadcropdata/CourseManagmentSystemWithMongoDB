package com.cropdata.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Course {

	@org.springframework.data.annotation.Transient
	public static final String SEQUENCE_NAME = "course_sequence";

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cId;

	private String name;

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonManagedReference
//	@JoinColumn(name = "cId", referencedColumnName = "cId")

	@DBRef
	private List<Language> language;

	private String description;

//	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	@JsonManagedReference
//	@JoinColumn(name = "cId", referencedColumnName = "cId")
	@DBRef
	private List<Writer> writer;
}
