package edu.miu.nomin.jpa.hospitalappointment.repository;

import edu.miu.nomin.jpa.hospitalappointment.entity.Appointment;
import edu.miu.nomin.jpa.hospitalappointment.entity.Doctor;
import edu.miu.nomin.jpa.hospitalappointment.entity.Patient;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>, JpaSpecificationExecutor<Appointment> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);
    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);
    Optional<Appointment> findByPatientAndDoctorAndAppointmentDateAndTimeSlot(
            Patient patient,
            Doctor doctor,
            LocalDate appointmentDate,
            int timeSlot
    );
    @Query("SELECT a FROM Appointment a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.patient.id = :patientId")
    List<Appointment> findAppointmentsByDoctorAndPatient(Long doctorId, Long patientId);

}
