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

import com.cropdata.controller.WriterController;
import com.cropdata.entity.Writer;
import com.cropdata.repository.WriterRepository;
import com.cropdata.serviceimpl.WriterServiceImpl;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = { TestWriterService.class })
public class TestWriterService {

	@Mock
	public WriterRepository writerRepository;

	@InjectMocks
	public WriterServiceImpl writerServiceImpl;

	@InjectMocks
	WriterController writerController;

	@Test
	@Order(1)
	public void tst_getAllCourse() {
		List<Writer> writers = new ArrayList<>();

		Writer writer = new Writer();

		writer.setWId(1);
		writer.setName("Shubham Tiwari");
		writer.setCId(1);

		Writer writer1 = new Writer();

		writer1.setWId(2);
		writer1.setName("Vikrant");
		writer1.setCId(2);

		writers.add(writer);
		writers.add(writer1);
		when(writerRepository.findAll()).thenReturn(writers);
		assertEquals(2, writerServiceImpl.getAllCourse().size());

	}

	@Test
	@Order(2)
	public void test_getCourseById() {

		Writer writer = new Writer();
		writer.setWId(2);
		writer.setName("Shubham Tiwari");
		writer.setCId(1);

		when(writerRepository.findById(2)).thenReturn(Optional.of(writer));
		String name = "Shubham Tiwari";
		Writer actualWriter = writerServiceImpl.findByWriterId(2);
		assertEquals(1, actualWriter.getCId());
		assertEquals(name, actualWriter.getName());
	}

	@Test
	@Order(3)
	public void testCreateLanguage() {
		// Create a mock Language object
		Writer writer = new Writer();

		writer.setWId(1);
		writer.setName("Shubham Tiwari");
		writer.setCId(1);
		// Mock the languageRepository.save() method
		when(writerRepository.save(any(Writer.class))).thenReturn(writer);
		// Call the createLanguage() method
		Writer result = writerServiceImpl.saveCourse(writer);
		// Assert the result
		assertNotNull(result);
		assertEquals("Shubham Tiwari", result.getName());
	}

	@Test
	@Order(4)
	public void test__UpdateLanguageDetail() {
		// Given
		Integer wId = 2;

		Writer writer = new Writer();

		writer.setWId(2);
		writer.setName("Shubham Tiwari");
		writer.setCId(1);

		Optional<Writer> optionalWriter = Optional.of(writer);
		Writer writerUpdated = new Writer();

		writerUpdated.setWId(2);
		writerUpdated.setName("Harsh");
		writerUpdated.setCId(1);
		when(writerRepository.findById(wId)).thenReturn(optionalWriter);
		when(writerRepository.save(writer)).thenReturn(writerUpdated);

		// When
		Writer result = writerServiceImpl.updateWriter(writer);

		// Then
		assertEquals(writerUpdated, result);
		verify(writerRepository).save(writer);
	}
}
