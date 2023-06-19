package com.cropdata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cropdata.controller.LanguageController;
import com.cropdata.entity.Language;
import com.cropdata.repository.LanguageRepository;
import com.cropdata.serviceimpl.LanguageServiceIMPL;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { TestLanguageService.class })
public class TestLanguageService {

	@Mock
	public LanguageRepository languageRepository;

	@InjectMocks
	public LanguageServiceIMPL languageServiceIMPL;

	@InjectMocks
	LanguageController languageController;

	@Test
	@Order(1)
	public void tst_getAllLanguage() {
		List<Language> languages = new ArrayList<>();

		Language language = new Language();

		language.setLId(1);
		language.setName("Shubham Tiwari");
		language.setCId(1);

		Language language2 = new Language();

		language.setLId(2);
		language2.setName("Vikrant");
		language2.setCId(2);

		languages.add(language);
		languages.add(language2);
		when(languageRepository.findAll()).thenReturn(languages);
		assertEquals(2, languageServiceIMPL.getAllLanguage().size());

	}

	@Test
	@Order(2)
	public void test_getLanguageById() {

		Language language = new Language();

		language.setLId(1);

		language.setName("Shubham Tiwari");
		language.setCId(1);

		when(languageRepository.findById(2)).thenReturn(Optional.of(language));
		String name = "Shubham Tiwari";
		Language actualLanguage = languageServiceIMPL.findByCourseId(2);
		assertEquals(1, actualLanguage.getCId());
		assertEquals(name, actualLanguage.getName());
	}

	@Test
	@Order(3)
	public void testCreateLanguage() {
		// Create a mock Language object
		Language language2 = new Language();

		language2.setLId(2);
		language2.setName("English");
		language2.setCId(2);

		// Mock the languageRepository.save() method
		when(languageRepository.save(any(Language.class))).thenReturn(language2);
		// Call the createLanguage() method
		Language result = languageServiceIMPL.saveLanguage(language2);
		// Assert the result
		assertNotNull(result);
		assertEquals("English", result.getName());
	}

	@Test
	@Order(4)
	public void test__UpdateLanguageDetail() {
		// Given
		Integer iId = 2;

		Language language2 = new Language();

		language2.setLId(2);
		language2.setName("English");
		language2.setCId(2);

		Optional<Language> optionalLanguage = Optional.of(language2);
		Language languageUpdated = new Language();

		languageUpdated.setLId(2);
		languageUpdated.setName("Phython");
		languageUpdated.setCId(2);
		when(languageRepository.findById(iId)).thenReturn(optionalLanguage);
		when(languageRepository.save(language2)).thenReturn(languageUpdated);

		// When
		Language result = languageServiceIMPL.updateLanguage(language2);

		// Then
		assertEquals(languageUpdated, result);
		verify(languageRepository).save(language2);
	}

}
