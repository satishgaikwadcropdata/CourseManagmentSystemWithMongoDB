package com.cropdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDTO {

	private Integer lId;

	private String name;

	private Integer cId;

//	private List<CourseDTO> coursesDTO;
}
