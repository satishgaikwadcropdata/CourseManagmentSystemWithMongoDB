package com.cropdata.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.cropdata.entity.Writer;
@Repository
public interface WriterRepository extends MongoRepository<Writer, Integer> {

}
 