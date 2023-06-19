package com.cropdata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WriterDTO {

	private Integer wId;

	private String name;

//	private List<CourseDTO> coursesDTO;

	private Integer cId;
}
