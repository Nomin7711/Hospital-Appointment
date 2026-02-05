package edu.miu.nomin.jpa.hospitalappointment.repository;

import edu.miu.nomin.jpa.hospitalappointment.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> findByFirstName(String firstName);
}
