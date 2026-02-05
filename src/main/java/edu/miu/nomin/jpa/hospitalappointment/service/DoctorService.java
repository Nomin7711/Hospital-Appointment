package edu.miu.nomin.jpa.hospitalappointment.service;

import edu.miu.nomin.jpa.hospitalappointment.entity.Department;
import edu.miu.nomin.jpa.hospitalappointment.entity.Doctor;
import edu.miu.nomin.jpa.hospitalappointment.repository.DepartmentRepository;
import edu.miu.nomin.jpa.hospitalappointment.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public Doctor save(Doctor doctor) {
        if (doctor.getDepartment() != null && doctor.getDepartment().getId() != null) {
            Department department = departmentRepository.findById(doctor.getDepartment().getId()).orElse(null);
            if (department != null) {
                doctor.setDepartment(department);
            } else {
                throw new RuntimeException("Department not found");
            }
        }

        return doctorRepository.save(doctor);
    }
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
    public Doctor findById(Long id) {
        return doctorRepository.findById(id).orElse(null);
    }
    public void delete(Long id) {
        doctorRepository.deleteById(id);
    }
    public List<Doctor> findBySpecialization(String specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
    public List<Doctor> getDoctorsWithDepartmentAndHospital(String hospitalName) {
        return doctorRepository.findDoctorsWithDepartmentAndHospital(hospitalName);
    }
}
