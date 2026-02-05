package edu.miu.nomin.jpa.hospitalappointment.controller;

import edu.miu.nomin.jpa.hospitalappointment.entity.Doctor;
import edu.miu.nomin.jpa.hospitalappointment.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping(produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Doctor> getAllDoctors() {
        return doctorService.findAll();
    }
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable Long id) {
        return doctorService.findById(id);
    }
    @PostMapping(consumes = "application/json")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }
    @PutMapping
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return doctorService.save(doctor);
    }
    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.delete(id);
    }
    @GetMapping("/findBySpecialization/{specialization}")
    public List<Doctor> findBySpecialization(@PathVariable String specialization) {
        return doctorService.findBySpecialization(specialization);
    }
    //Named query
    @GetMapping("/hospital/{hospitalName}")
    public List<Doctor> getDoctorsWithDepartmentAndHospital(@PathVariable String hospitalName) {
        return doctorService.getDoctorsWithDepartmentAndHospital(hospitalName);
    }
}
