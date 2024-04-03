package com.example.backend.Repo;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.backend.Entity.Patient;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    // Define custom repository methods if needed
    boolean existsByphoneNumber(String phoneNumber);
    boolean existsByemail(String email);
}