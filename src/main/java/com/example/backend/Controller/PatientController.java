package com.example.backend.Controller;



import com.example.backend.Entity.Patient;
import com.example.backend.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // Endpoint to create a new patient
//    @PostMapping
//    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
//        Patient createdPatient = patientService.savePatient(patient);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
//    }
    @PostMapping("/create")
    public String create_patient(@RequestBody Patient npr) {
        System.out.println(npr.getPhoneNumber());
        if(patientService.isMobileNumberExists(npr.getPhoneNumber())){
            return "Mobile number already exsists";
        }
        if(patientService.isemailExists(npr.getEmail()))
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
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") int id) {
        Patient patient = patientService.getPatientById(id);
        if (patient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    // Endpoint to delete a patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") int id) {
//        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
