package com.example.backend.Record;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordRepo extends MongoRepository<Record, String> {
    List<Record> findByPatientId(String patientId);
}
