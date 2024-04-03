package com.example.backend.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepo RecordRepository;

    public Record createMedicalRecord(String patientId,  String description, MultipartFile file) {
        // Your implementation to save the medical record
        // For example:
        // MedicalRecord medicalRecord = new MedicalRecord(patientId, doctorIds, description, file);
        // return medicalRecordRepository.save(medicalRecord);
        return null;
    }

    public List<Record> getAllMedicalRecords() {
        return RecordRepository.findAll();
    }

    public List<Record> getMedicalRecordsByPatientId(String patientId) {
        return RecordRepository.findByPatientId(patientId);
    }
}
