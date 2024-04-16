package com.example.backend.Appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/create")
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable String patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctorId(@PathVariable String doctorId) {
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }

    @PutMapping("/{appointmentId}/setRecord")
    public Appointment setRecord(@PathVariable String appointmentId, @RequestParam String recordId) {
        return appointmentService.setRecord(appointmentId, recordId);
    }

    @PutMapping("/{appointmentId}/setFollowUp")
    public Appointment setFollowUp(@PathVariable String appointmentId, @RequestParam boolean followUp) {
        return appointmentService.setFollowUp(appointmentId, followUp);
    }
}
