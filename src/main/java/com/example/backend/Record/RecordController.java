package com.example.backend.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class RecordController {

    @Autowired
    private RecordService medicalRecordService;

    @PostMapping("/create")
    public ResponseEntity<String> createMedicalRecord(@RequestParam("patientId") String patientId,

                                                      @RequestParam("description") String description,
                                                      @RequestParam("file") MultipartFile file) {
        try {
            Record medicalRecord = medicalRecordService.createMedicalRecord(patientId, description, file);
            return new ResponseEntity<>("Medical Record created successfully with ID: " + medicalRecord.getRecordId(), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to create Medical Record: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Record>> getAllMedicalRecords() {
        List<Record> medicalRecords = medicalRecordService.getAllMedicalRecords();
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }

    @GetMapping("/by-patient/{patientId}")
    public ResponseEntity<List<Record>> getMedicalRecordsByPatientId(@PathVariable("patientId") String patientId) {
        List<Record> medicalRecords = medicalRecordService.getMedicalRecordsByPatientId(patientId);
        return new ResponseEntity<>(medicalRecords, HttpStatus.OK);
    }
}
