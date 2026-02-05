package edu.miu.nomin.jpa.hospitalappointment.controller;

import edu.miu.nomin.jpa.hospitalappointment.entity.Appointment;
import edu.miu.nomin.jpa.hospitalappointment.entity.AppointmentStatus;
import edu.miu.nomin.jpa.hospitalappointment.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Appointment> getAllAppointments() {
        return appointmentService.findAll();
    }
    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable Long id) {
        return appointmentService.findById(id);
    }
    @PutMapping
    public Appointment updateAppointment(@RequestBody Appointment appointment) {
        return appointmentService.save(appointment);
    }
    @PutMapping("/{appointmentId}/status")
    public Appointment updateAppointmentStatus(@PathVariable Long appointmentId,
                                               @RequestParam AppointmentStatus status) {

        return appointmentService.updateStatus(appointmentId, status);
    }
    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteById(id);
    }
    @GetMapping("findByDoctorIdAndAppointmentDate/{id}/{date}")
    public List<Appointment> getAppointmentByDoctorIdAndAppointmentDate(@PathVariable Long id, @PathVariable String date) {
        LocalDate appointmentDate = LocalDate.parse(date);
        return appointmentService.findByDoctorIdAndAppointmentDate(id, appointmentDate);
    }
    //Find all appointments in Cardiology department Criteria query
    @GetMapping("/cardiology")
    public List<Appointment> getAppointmentsInCardiology() {
        return appointmentService.findAppointmentsInCardiology();
    }
    //Find appointments with given doctor and patient Dynamic query
    @GetMapping("/findByDoctorAndPatient/{doctorId}/{patientId}")
    public List<Appointment> getAppointmentsByDoctorAndPatient(
            @PathVariable Long doctorId, @PathVariable Long patientId) {
        return appointmentService.getAppointmentsByDoctorAndPatient(doctorId, patientId);
    }

}
