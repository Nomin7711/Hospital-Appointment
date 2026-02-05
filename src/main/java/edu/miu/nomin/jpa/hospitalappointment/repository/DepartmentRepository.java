package edu.miu.nomin.jpa.hospitalappointment.repository;

import edu.miu.nomin.jpa.hospitalappointment.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
