package com.example.backend.Appointment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(collection = "appointments")
public class Appointment {

    @Id
    private String appointmentId;

    private String patientId;

    private String doctorId;

    private String startTime;

    private int durationMinutes;

    private boolean followUp;

    private String recordId;

    public Appointment(String appointmentId, String patientId, String doctorId, String startTime, int durationMinutes, boolean followUp, String recordId) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.startTime = startTime;
        this.durationMinutes = durationMinutes;
        this.followUp = followUp;
        this.recordId = recordId;
    }
    // Setters and Getters
    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public boolean isFollowUp() {
        return followUp;
    }

    public void setFollowUp(boolean followUp) {
        this.followUp = followUp;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
}
