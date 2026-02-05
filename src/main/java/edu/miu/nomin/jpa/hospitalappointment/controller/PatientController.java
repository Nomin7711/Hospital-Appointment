package edu.miu.nomin.jpa.hospitalappointment.controller;

import edu.miu.nomin.jpa.hospitalappointment.entity.Patient;
import edu.miu.nomin.jpa.hospitalappointment.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public List<Patient> getPatient() {
        return patientService.findAll();
    }
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Long id) {
        return patientService.findById(id);
    }
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.save(patient);
    }
    @PutMapping
    public Patient updatePatient(@RequestBody Patient patient) {
        return patientService.save(patient);
    }
    @DeleteMapping("/{id}")
    public void deletePatientById(@PathVariable Long id) {
        patientService.deleteById(id);
    }
}
