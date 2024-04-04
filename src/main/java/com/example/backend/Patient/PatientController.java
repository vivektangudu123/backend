package com.example.backend.Patient;



import com.example.backend.Doctor.DoctorService;
import com.example.backend.authentication.UniqueIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    // Endpoint to create a new patient
//    @PostMapping
//    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
//        Patient createdPatient = patientService.savePatient(patient);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
//    }
    @PostMapping("/create")
    public String create_patient(@RequestBody Patient npr) {
        System.out.println(npr.getPhoneNumber());
        if(patientService.isMobileNumberExists(npr.getPhoneNumber()) || doctorService.isMobileNumberExists(npr.getPhoneNumber())){
            return "Mobile number already exsists";
        }
        if(patientService.isemailExists(npr.getEmail()) || doctorService.isEmailExists(npr.getEmail()))
        {
            return "email already exsists";
        }
        String uniqueId = UniqueIdGenerator.generateUniqueId(8);
        npr.setPatientId(uniqueId);
        this.patientService.savePatient(npr);
        return "Success";
    }

    // Endpoint to retrieve all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    // Endpoint to retrieve a patient by ID
    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("patientId") String id) {
        Patient patient = patientService.findBy_patientId(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Endpoint to delete a patient by ID
    @DeleteMapping("/{patientId}")
    public ResponseEntity<Void> deletePatient(@PathVariable("patientId") String id) {
//        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
