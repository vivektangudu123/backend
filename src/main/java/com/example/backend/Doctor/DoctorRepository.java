package com.example.backend.Doctor;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {
//    boolean existsByPhoneNumber(String phoneNumber);
//    boolean existsByEmail(String email);
//
//    Doctor findByPhoneNumber(String phoneNumber);
//    Doctor findByDoctorId(String doctorId);
}
