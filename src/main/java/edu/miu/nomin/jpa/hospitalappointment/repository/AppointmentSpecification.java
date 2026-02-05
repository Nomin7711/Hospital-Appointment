package edu.miu.nomin.jpa.hospitalappointment.repository;

import edu.miu.nomin.jpa.hospitalappointment.entity.Appointment;
import edu.miu.nomin.jpa.hospitalappointment.entity.Department;
import edu.miu.nomin.jpa.hospitalappointment.entity.Doctor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class AppointmentSpecification {
    public static Specification<Appointment> hasDoctorInCardiology() {
        return (Root<Appointment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            Join<Appointment, Doctor> doctorJoin = root.join("doctor");
            Join<Doctor, Department> departmentJoin = doctorJoin.join("department");
            return criteriaBuilder.equal(departmentJoin.get("name"), "Cardiology");
        };
    }
}
