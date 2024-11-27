package Pojos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Doctor implements Serializable {
    private static final long serialVersionUID = 4092297860583387711L;
    private int doctor_id;
    private String name;
    private String surname;
    private LocalDate dob;
    private String email;
    private Integer userId;
    private LinkedList<Patient> patients;

    public Doctor(int doctor_id, String name, String surname, LocalDate dob, String email, Integer userId) {
        this.doctor_id = doctor_id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.userId = userId;
        patients= new LinkedList<>();
    }

    public Doctor(String name, String surname, LocalDate dob, String email) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        patients= new LinkedList<>();
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public LinkedList<Patient> getPatients() {
        return patients;
    }
     public void setPatients(LinkedList<Patient> patients) {
            this.patients = patients;
     }
    @Override
    public String toString() {
        return "Doctor{" +
                "doctor_id=" + doctor_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}
