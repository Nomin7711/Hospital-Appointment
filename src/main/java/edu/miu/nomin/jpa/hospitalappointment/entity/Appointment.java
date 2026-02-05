package edu.miu.nomin.jpa.hospitalappointment.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Appointment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;  // Appointment status (PENDING, CONFIRMED, COMPLETED, CANCELLED)

    private Integer timeSlot;  // Store time slots as integer (1 to 9)

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    @JsonBackReference
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Appointment() {}

    public Appointment(LocalDate date, AppointmentStatus status, Integer timeSlot, Patient patient, Doctor doctor) {
        this.appointmentDate = date;
        this.status = status;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.doctor = doctor;
    }
    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }
    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
    public Integer getTimeSlot() {
        return timeSlot;
    }
    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

}

