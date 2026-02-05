package edu.miu.nomin.jpa.hospitalappointment.repository;

import edu.miu.nomin.jpa.hospitalappointment.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
}
