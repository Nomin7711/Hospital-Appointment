package edu.miu.nomin.jpa.hospitalappointment.controller;

import edu.miu.nomin.jpa.hospitalappointment.entity.Hospital;
import edu.miu.nomin.jpa.hospitalappointment.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HospitalsController {
    @Autowired
    HospitalRepository hospitalRepository;
    //Public request
    @GetMapping("/hospitals")
    public List<Hospital> getAllHospitals(){
        return hospitalRepository.findAll();
    }
}
