package com.cropdata.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cropdata.customeresponse.CustomeResponse;
import com.cropdata.dto.LanguageDTO;
import com.cropdata.entity.Language;
import com.cropdata.exception.LanguageException;
import com.cropdata.iservice.ILanguageService;
import com.cropdata.mapper.LanguageMapper;

@RestController
@RequestMapping("/language")
public class LanguageController {

	private String code;

	private Object data;

	@Autowired(required = true)
	private ILanguageService iLanguageService;

	@Autowired
	private LanguageMapper languageMapper;

	@SuppressWarnings("finally")
	@PostMapping()
	public ResponseEntity<?> createLanguage(@RequestBody LanguageDTO languageDTO) {
		Language language = new Language();
		try {
			language = iLanguageService.saveLanguage(languageMapper.toLanguage(languageDTO));
			data = language;
			code = "CREATED";
		} catch (LanguageException customerNotFoundException) {
			data = null;
			code = "DATA_NOT_CREATED";
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllLanguage() {

		List<Language> languages = iLanguageService.getAllLanguage();
		List<Language> languagesList = new ArrayList<>();
		try {

			for (Language language : languages) {
				languagesList.add(language);
			}
			data = languagesList;
			code = "SUCCESS";
		} catch (LanguageException customerNotFoundException) {
			code = "DATA_NOT_FOUND";
			data = null;
		} catch (RuntimeException runtimeException) {
			code = "RUNTIME_EXCEPTION";
			data = null;
		} catch (Exception exception) {
			code = "EXCEPTION";
			data = null;
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@DeleteMapping("{lId}")
	public ResponseEntity<?> deleteLanguageById(@PathVariable Integer lId) {
		Language id = null;
		try {
			id = iLanguageService.deleteLanguageById(lId);
			data = id;
			code = "SUCCESS";
		} catch (LanguageException customerNotFoundException) {
			data = null;
			code = "DELETE_FAILED" + id;
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@PutMapping("/update")
	public ResponseEntity<?> updateLanguage(@RequestBody LanguageDTO languageDTO) {

		Language language = null;
		try {
			language = iLanguageService.updateLanguage(languageMapper.toLanguage(languageDTO));
			data = language;
		} catch (LanguageException customerNotFoundException) {
			data = null;
			code = "UPDATE_FAILED";
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}
	}

	@SuppressWarnings("finally")
	@GetMapping("{cId}")
	public ResponseEntity<?> findByCourseId(@PathVariable Integer cId) {

		Language language = iLanguageService.findByCourseId(cId);
		LanguageDTO languageDTO = new LanguageDTO();
		try {

			languageDTO = languageMapper.toLanguageDTO(language);
			data = languageDTO;
			code = "SUCCESS";

		} catch (LanguageException customerNotFoundException) {
			data = null;
			code = "CUSTOMER_NOT_FOUND_ERROR";
		} catch (RuntimeException runtimeException) {
			data = null;
			code = "RUNTIME_EXCEPTION";
		} catch (Exception exception) {
			data = null;
			code = "EXCEPTION";
		} finally {
			return CustomeResponse.response(code, data);
		}

	}

}
