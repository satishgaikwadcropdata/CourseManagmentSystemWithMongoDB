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
import com.cropdata.dto.WriterDTO;
import com.cropdata.entity.Writer;
import com.cropdata.exception.WriterNotFoundException;
import com.cropdata.iservice.IWriterService;
import com.cropdata.mapper.WriterMapper;

@RestController
@RequestMapping("/writer")
public class WriterController {

	private String code;

	private Object data;

	@SuppressWarnings("unused")
	@Autowired
	private IWriterService iWriterService;

	@Autowired
	private WriterMapper writerMapper;

	@SuppressWarnings("finally")
	@PostMapping()
	public ResponseEntity<?> createCourse(@RequestBody WriterDTO writerDTO) {
		Writer writer = new Writer();
		try {
			writer = iWriterService.saveCourse(writerMapper.toWriter(writerDTO));
			data = writer;
			code = "CREATED";
		} catch (WriterNotFoundException writerNotFoundException) {
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
	public ResponseEntity<?> findAllCourse() {

		List<Writer> writers = iWriterService.getAllCourse();
		List<Writer> writersList = new ArrayList<>();
		try {

			for (Writer writer : writers) {
				writersList.add(writer);
			}
			data = writersList;
			code = "SUCCESS";
		} catch (WriterNotFoundException writerNotFoundException) {
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
	@PutMapping("/update")
	public ResponseEntity<?> updateWriter(@RequestBody WriterDTO writerDTO) {

		Writer writer = null;
		try {
			writer = iWriterService.updateWriter(writerMapper.toWriter(writerDTO));
			data = writer;
		} catch (WriterNotFoundException writerNotFoundException) {
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
	@DeleteMapping("{wId}")
	public ResponseEntity<?> deleteCourseById(@PathVariable Integer wId) {
		Writer id = null;
		try {
			id = iWriterService.deleteWriterById(wId);
			data = id;
			code = "SUCCESS";
		} catch (WriterNotFoundException writerNotFoundException) {
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
	@GetMapping("{wId}")
	public ResponseEntity<?> findByWriterId(@PathVariable Integer wId) {

		Writer writer = iWriterService.findByWriterId(wId);
		WriterDTO writerDTO = new WriterDTO();
		try {

			writerDTO = writerMapper.toWriterDTO(writer);
			data = writerDTO;
			code = "SUCCESS";

		} catch (WriterNotFoundException writerNotFoundException) {
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
