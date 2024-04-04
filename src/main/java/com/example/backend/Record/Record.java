package com.example.backend.Record;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Document(collection = "Records")
public class Record {

    @Id
    private String recordId;

    private String patientId;

    private List<String> doctors;

    private String description;

    private byte[] fileData;

    // Constructors, Getters, and Setters

    public Record() {
    }

    public Record(String patientId, String description, byte[] fileData) {
        this.patientId = patientId;
        this.description = description;
        this.fileData = fileData;
    }


    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public List<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<String> doctors) {
        this.doctors = doctors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
