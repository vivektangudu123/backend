package com.example.backend.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    public void addRecord(MultipartFile file, String patientId, String description) throws IOException {
        byte[] fileData = file.getBytes();
        Record record = new Record(patientId, description, fileData);
        recordRepository.save(record);
    }

    public Record getRecordById(String recordId) {
        return recordRepository.findById(recordId).orElse(null);
    }

    public void addDoctorToRecord(String recordId, String doctorId) {
        Record record = recordRepository.findById(recordId).orElse(null);
        if (record != null) {
            List<String> doctors = record.getDoctors();
            doctors.add(doctorId);
            record.setDoctors(doctors);
            recordRepository.save(record);
        }
    }

    public List<Record> getAllRecordsByPatientId(String patientId) {
        return recordRepository.findByPatientId(patientId);
    }
}
