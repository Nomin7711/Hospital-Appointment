package edu.miu.nomin.jpa.hospitalappointment.service;

import edu.miu.nomin.jpa.hospitalappointment.entity.Appointment;
import edu.miu.nomin.jpa.hospitalappointment.entity.AppointmentStatus;
import edu.miu.nomin.jpa.hospitalappointment.entity.Doctor;
import edu.miu.nomin.jpa.hospitalappointment.entity.Patient;
import edu.miu.nomin.jpa.hospitalappointment.exception.AppointmentAlreadyBookedException;
import edu.miu.nomin.jpa.hospitalappointment.exception.ResourceNotFoundException;
import edu.miu.nomin.jpa.hospitalappointment.jms.Sender;
import edu.miu.nomin.jpa.hospitalappointment.repository.AppointmentRepository;
import edu.miu.nomin.jpa.hospitalappointment.repository.AppointmentSpecification;
import edu.miu.nomin.jpa.hospitalappointment.repository.DoctorRepository;
import edu.miu.nomin.jpa.hospitalappointment.repository.PatientRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private Sender sender;

    public Appointment save(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }
    public List<Appointment> findByDoctor(Doctor doctor) {
        return appointmentRepository.findByDoctorId(doctor.getId());
    }
    public List<Appointment> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
    public List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate) {
        return appointmentRepository.findByDoctorIdAndAppointmentDate(doctorId, appointmentDate);
    }
    public List<Appointment> findByAppointmentDate(LocalDate appointmentDate) {
        return appointmentRepository.findByAppointmentDate(appointmentDate);
    }
    public Appointment createAppointment(Appointment appointment) {
        Optional<Patient> patientOpt = patientRepository.findById(appointment.getPatient().getId());
        Optional<Doctor> doctorOpt = doctorRepository.findById(appointment.getDoctor().getId());

        if (patientOpt.isEmpty()) {
            throw new ResourceNotFoundException("Patient not found");
        }

        if (doctorOpt.isEmpty()) {
            throw new ResourceNotFoundException("Doctor not found");
        }
        Optional<Appointment> existingAppointment = appointmentRepository
                .findByPatientAndDoctorAndAppointmentDateAndTimeSlot(
                        appointment.getPatient(),
                        appointment.getDoctor(),
                        appointment.getAppointmentDate(),
                        appointment.getTimeSlot()
                );
        if(existingAppointment.isPresent()) {
            throw new AppointmentAlreadyBookedException("Appointment already booked");
        }

        Patient patient = patientOpt.get();
        Doctor doctor = doctorOpt.get();

        Appointment appointmentSave = new Appointment(appointment.getAppointmentDate(), appointment.getStatus(), appointment.getTimeSlot(), patient, doctor);
        Appointment appointmentSaved = appointmentRepository.save(appointmentSave);
        sender.sendAppointmentCreatedSms(
                appointmentSaved.getPatient().getFirstName(),
                appointmentSaved.getPatient().getPhoneNumber(),
                appointmentSaved.getId().toString(),
                appointmentSaved.getAppointmentDate().toString()
        );
        return appointmentSaved;
    }
    @Transactional
    public Appointment updateStatus(Long appointmentId, AppointmentStatus status) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found"));

        appointment.setStatus(status);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        sender.sendSmsMessage(savedAppointment.getPatient().getFirstName(), savedAppointment.getPatient().getPhoneNumber(), status.toString(), savedAppointment.getId().toString(), savedAppointment.getAppointmentDate().toString());
        return savedAppointment;
    }
    public List<Appointment> findAppointmentsInCardiology() {
        return appointmentRepository.findAll(AppointmentSpecification.hasDoctorInCardiology());
    }
    public List<Appointment> getAppointmentsByDoctorAndPatient(Long doctorId, Long patientId) {
        return appointmentRepository.findAppointmentsByDoctorAndPatient(doctorId, patientId);
    }
}
