package edu.miu.nomin.jpa.hospitalappointment.service;

import edu.miu.nomin.jpa.hospitalappointment.entity.Patient;
import edu.miu.nomin.jpa.hospitalappointment.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
    public List<Patient> findByFirstName(String patientName) {
        return patientRepository.findByFirstName(patientName);
    }
    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

}
