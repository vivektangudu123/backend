package com.example.backend.Record;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping("/add")
    public ResponseEntity<String> addRecord(@RequestParam("file") MultipartFile file,
                                            @RequestParam("patientId") String patientId,
                                            @RequestParam("description") String description) throws IOException {
        recordService.addRecord(file, patientId, description);
        return ResponseEntity.status(HttpStatus.CREATED).body("Record added successfully");
    }

    @GetMapping("/view/{recordId}")
    public ResponseEntity<Resource> viewRecord(@PathVariable("recordId") String recordId) {
        Record record = recordService.getRecordById(recordId);
        ByteArrayResource resource = new ByteArrayResource(record.getFileData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + recordId + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @GetMapping("/download/{recordId}")
    public ResponseEntity<Resource> downloadRecord(@PathVariable("recordId") String recordId) {
        Record record = recordService.getRecordById(recordId);
        ByteArrayResource resource = new ByteArrayResource(record.getFileData());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recordId + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }

    @PutMapping("/{recordId}/add-doctor")
    public ResponseEntity<String> addDoctorToRecord(@PathVariable("recordId") String recordId,
                                                    @RequestParam("doctorId") String doctorId) {
        recordService.addDoctorToRecord(recordId, doctorId);
        return ResponseEntity.ok().body("Doctor added to the record successfully");
    }

    @GetMapping("/all/{patientId}")
    public ResponseEntity<List<Record>> getAllRecordsByPatientId(@PathVariable("patientId") String patientId) {
        List<Record> records = recordService.getAllRecordsByPatientId(patientId);
        return ResponseEntity.ok().body(records);
    }
}
