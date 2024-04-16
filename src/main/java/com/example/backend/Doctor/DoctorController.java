package com.example.backend.Doctor;

import com.example.backend.Patient.PatientService;
import com.example.backend.authentication.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientService patientService;

    @PostMapping("/create")
    public String createDoctor(@RequestBody Doctor npr) {
        if(patientService.isMobileNumberExists(npr.getPhoneNumber()) || doctorService.isMobileNumberExists(npr.getPhoneNumber())){
            return "Mobile number already exsists";
        }
        if(patientService.isemailExists(npr.getEmail()) || doctorService.isEmailExists(npr.getEmail()))
        {
            return "email already exsists";
        }
        String uniqueId = UniqueIdGenerator.generateUniqueId(8);
        npr.setDoctorId(uniqueId);
        this.doctorService.saveDoctor(npr);
        return "Success";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable("doctorId") String id) {
        Doctor doctor = doctorService.findByDoctorId(id);
        if (doctor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable("doctorId") String id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
