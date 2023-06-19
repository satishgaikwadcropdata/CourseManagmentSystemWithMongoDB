package com.cropdata.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cropdata.entity.Writer;
import com.cropdata.exception.WriterNotFoundException;
import com.cropdata.iservice.IWriterService;
import com.cropdata.mapper.WriterMapper;
import com.cropdata.repository.WriterRepository;

@Service
public class WriterServiceImpl implements IWriterService {

	@Autowired
	private WriterRepository writerRepository;

	@Autowired
	private WriterMapper writerMapper;

	@Override
	public Writer saveCourse(Writer writer) {
		Writer writer1 = null;
		try {
			writer1 = writerRepository.save(writer);

		} catch (Exception e) {
			e.printStackTrace();
			throw new WriterNotFoundException("Customer Present");
		}
		return writer1;
	}

	@Override
	public List<Writer> getAllCourse() {
		List<Writer> writersList = new ArrayList<>();
		try {
			writersList = writerRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new WriterNotFoundException();
		}
		return writersList;
	}

	@Override
	public Writer updateWriter(Writer writer) {
		Writer writer1 = writerRepository.findById(writer.getWId()).get();
		try {

			if (writer1.getWId() != null) {

				writer1 = writerRepository.save(writer);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WriterNotFoundException();
		}
		return writer1;
	}

	@Override
	public Writer deleteWriterById(Integer wId) {
		Writer writer = writerRepository.findById(wId).get();
		try {
			if (writer.getWId() != null) {
				writerRepository.delete(writer);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WriterNotFoundException();
		}
		return writer;
	}

	@Override
	public Writer findByWriterId(Integer wId) {
		Writer writer = new Writer();
		try {
			if (wId != null) {
				writer = writerRepository.findById(wId).get();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new WriterNotFoundException();
		}
		return writer;
	}

}
