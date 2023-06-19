package com.cropdata.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

	private Integer cId;
	private String name;

	private String description;

	private List<WriterDTO> writerDTOs;

	private List<LanguageDTO> languageDTOs;

}
