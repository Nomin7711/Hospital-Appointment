package edu.miu.nomin.jpa.hospitalappointment.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "Doctor.findDoctorsWithDepartmentAndHospital",
                query = "SELECT d FROM Doctor d " +
                        "JOIN d.department dep " +
                        "JOIN Hospital h ON dep MEMBER OF h.departments " +
                        "WHERE h.name = :hospitalName"
        )
})
public class Doctor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String specialization;

    @ElementCollection
    @CollectionTable(name = "doctor_time_slots", joinColumns = @JoinColumn(name = "doctor_id"))
    @Column(name = "time_slot")
    private List<Integer> availableTimeSlots = new ArrayList<>();  // Time slots (1 - 9, where 5 is lunch break)


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;

    public Doctor() {}

    public Doctor(String firstName, String lastName, String email, String phoneNumber, String specialization, Department department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.specialization = specialization;
        this.department = department;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setAvailableTimeSlots(List<Integer> availableTimeSlots) {
        this.availableTimeSlots = availableTimeSlots;
    }

    public List<Integer> getAvailableTimeSlots() {
        return availableTimeSlots;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
}


