package com.cropdata.mapper;

import org.mapstruct.Mapper;

import com.cropdata.dto.LanguageDTO;
import com.cropdata.entity.Language;

@Mapper(componentModel = "spring")
public interface LanguageMapper {

	public Language toLanguage(LanguageDTO languageDTO);

//	@Mapping(source = "LId", target = "LId")
//	@Mapping(source = "courses", target = "coursesDTO")
	public LanguageDTO toLanguageDTO(Language language);
}
