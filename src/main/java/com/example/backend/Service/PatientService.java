package com.example.backend.Service;

import com.example.backend.Repo.*;
import com.example.backend.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Method to save a patient
    public Patient savePatient(Patient patient) {

         return patientRepository.save(patient);
    }

    // Method to retrieve all patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Method to retrieve a patient by ID
    public Patient getPatientById(int id) {
        String str1 = Integer.toString(id);
        return patientRepository.findById(str1).orElse(null);
    }

    // Method to delete a patient by ID
    public void deletePatient(String id) {

//        String str1 = Integer.toString(id);
//        patientRepository.deleteById(str1);
    }
    public boolean isMobileNumberExists(String mobileNumber) {
        return patientRepository.existsByphoneNumber(mobileNumber);
    }
    public boolean isemailExists(String email) {
        return patientRepository.existsByemail(email);
    }
}


