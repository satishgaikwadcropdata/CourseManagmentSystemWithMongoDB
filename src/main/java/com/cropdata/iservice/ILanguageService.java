package com.cropdata.iservice;

import java.util.List;

import com.cropdata.entity.Language;

public interface ILanguageService {

	public Language saveLanguage(Language language);

	public List<Language> getAllLanguage();

	public Language deleteLanguageById(Integer lId);

	public Language updateLanguage(Language language);

	public Language findByCourseId(Integer cId);

}
