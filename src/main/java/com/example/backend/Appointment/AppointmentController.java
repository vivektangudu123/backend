package com.example.backend.Appointment;

import com.example.backend.authentication.AuthenticationController;
import com.example.backend.authentication.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private AuthenticationController authenticationController;

    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@RequestParam("doctorId") String doctorId,@RequestParam("date") String date,@RequestParam("time") String time,
                                               @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.replace("Bearer ", "");
        String patientId = authenticationController.get_username_using_jwt(jwtToken);
        System.out.println(patientId);

        if (patientId.equals("1")) {
            System.out.println("111");
            return ResponseEntity.ok().body("1");
        }

        // Create Appointment object
        System.out.println(date);
        String dateTimeString = date + " " + time;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
        String formattedDateTimeString = dateTime.format(formatter);
        String appointmentId = UniqueIdGenerator.generateUniqueId(8);
        Appointment appointment = new Appointment(appointmentId,patientId,doctorId,formattedDateTimeString,45,false,"");
        appointmentService.createAppointment(appointment);
        System.out.println(appointment.getStartTime());
        return ResponseEntity.ok().body("Success");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAppointmentsByPatientId(@RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.replace("Bearer ", "");
        String patientId = authenticationController.get_username_using_jwt(jwtToken);
        System.out.println(patientId);

        if (patientId.equals("1")) {
            return ResponseEntity.ok().body("1");
        }
        return ResponseEntity.ok().body(appointmentService.getAppointmentsByPatientId(patientId));
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
