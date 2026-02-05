package edu.miu.nomin.jpa.hospitalappointment.security;

import edu.miu.nomin.jpa.hospitalappointment.credentials.CredentialsModel;
import edu.miu.nomin.jpa.hospitalappointment.credentials.CredentialsService;
import edu.miu.nomin.jpa.hospitalappointment.entity.Department;
import edu.miu.nomin.jpa.hospitalappointment.entity.Doctor;
import edu.miu.nomin.jpa.hospitalappointment.entity.Hospital;
import edu.miu.nomin.jpa.hospitalappointment.repository.DepartmentRepository;
import edu.miu.nomin.jpa.hospitalappointment.repository.DoctorRepository;
import edu.miu.nomin.jpa.hospitalappointment.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class DatabaseInitializer implements CommandLineRunner {
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    CredentialsService credentialsService;
    @Override
    public void run(String... args) throws Exception {
//        CredentialsModel credentialsModel = new CredentialsModel("Jack", "123", "USER");
//        credentialsService.register(credentialsModel);
//        Hospital hospital1 = new Hospital("Miu Medical Center", "123 Main St, MIU", "123-456-7890");
//        Hospital hospital2 = new Hospital("City Hospital", "456 Elm St, City", "987-654-3210");
//        hospitalRepository.saveAll(Arrays.asList(hospital1, hospital2));
//
//        Department department1 = new Department("Cardiology", "Heart and cardiovascular care");
//        Department department2 = new Department("Neurology", "Brain and nervous system care");
//        Department department3 = new Department("Pediatrics", "Children's health services");
//        Department department4 = new Department("Orthopedics", "Bone and joint care");
//
//        hospital1.addDepartment(department1);
//        hospital1.addDepartment(department2);
//        hospital2.addDepartment(department3);
//        hospital2.addDepartment(department4);
//
//        Doctor doctor1 = new Doctor("John", "Doe", "johndoe@example.com", "123-555-6789", "Cardiologist", department1);
//        doctor1.setAvailableTimeSlots(Arrays.asList(1, 2, 3, 4, 6, 7, 8));
//        Doctor doctor2 = new Doctor("Jane", "Smith", "janesmith@example.com", "987-555-1234", "Neurologist", department2);
//        doctor2.setAvailableTimeSlots(Arrays.asList(1, 2, 4, 6, 7, 8, 9));
//        Doctor doctor3 = new Doctor("Alice", "Johnson", "alicej@example.com", "555-555-5555", "Pediatrician", department3);
//        doctor3.setAvailableTimeSlots(Arrays.asList(1, 2, 3, 4, 6, 8, 9));
//        Doctor doctor4 = new Doctor("Bob", "Brown", "bobb@example.com", "444-555-6666", "Orthopedic Surgeon", department4);
//        doctor4.setAvailableTimeSlots(Arrays.asList(1, 2, 3, 5, 6, 7));
//
//        doctorRepository.saveAll(Arrays.asList(doctor1, doctor2, doctor3, doctor4));
//        departmentRepository.saveAll(Arrays.asList(department1, department2, department3, department4));
//        hospitalRepository.saveAll(Arrays.asList(hospital1, hospital2));
    }
}
