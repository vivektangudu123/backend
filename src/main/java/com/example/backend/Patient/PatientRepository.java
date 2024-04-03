package com.example.backend.Patient;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    boolean existsByphoneNumber(String phoneNumber);
    boolean existsByemail(String email);

    Patient findByPhoneNumber(String phoneNumber);
    Patient findBypatientId(String patientId);
}