package com.cropdata.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cropdata.entity.Language;
import com.cropdata.exception.LanguageException;
import com.cropdata.iservice.ILanguageService;
import com.cropdata.mapper.LanguageMapper;
import com.cropdata.repository.LanguageRepository;

@Service
public class LanguageServiceIMPL implements ILanguageService {

	@Autowired
	private LanguageRepository languageRepository;

	@Autowired
	private LanguageMapper languageMapper;

	@Override
	public Language saveLanguage(Language language) {
		Language language1 = null;
		try {
			language1 = languageRepository.save(language);

		} catch (Exception e) {
			e.printStackTrace();
			throw new LanguageException("Customer Present");
		}
		return language1;
	}

	@Override
	public List<Language> getAllLanguage() {
		List<Language> languages = new ArrayList<>();
		try {
			languages = languageRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LanguageException();
		}
		return languages;
	}

	@Override
	public Language deleteLanguageById(Integer lId) {
		Language language = languageRepository.findById(lId).get();
		try {
			if (language.getLId() != null) {
				languageRepository.delete(language);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new LanguageException();
		}
		return language;
	}

	@Override
	public Language updateLanguage(Language language) {
		Language language1 = languageRepository.findById(language.getLId()).get();
		try {

			if (language1.getLId() != null) {

				language1 = languageRepository.save(language);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new LanguageException();
		}
		return language;
	}

	@Override
	public Language findByCourseId(Integer cId) {
		Language language = new Language();
		try {
			if (cId != null) {
				language = languageRepository.findById(cId).get();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new LanguageException();
		}
		return language;
	}

}
