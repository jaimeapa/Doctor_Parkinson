package Pojos;


import java.rmi.NotBoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient  {
    private int patient_id;
    private String name;
    private String surname;
    private LocalDate dob;
    private String email;
    private int doctor_id;
    private LinkedList<Interpretation> interpretations;


    public Patient(int patient_id, String name, String surname, LocalDate dob, String email,LinkedList<Symptoms>  symptoms, int doctor_id) {
        this.patient_id = patient_id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.doctor_id = doctor_id;
        this.interpretations= new LinkedList<>();
    }

    public Patient(String name, String surname, LocalDate dob, String email) {
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.interpretations= new LinkedList<>();
    }
    public Patient(int patient_id, String name, String surname, LocalDate dob, String email) {
        this.patient_id = patient_id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.interpretations= new LinkedList<>();
    }

    public int getPatient_id() {
        return patient_id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }


    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setEmail(String email) throws NotBoundException {
        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            this.email = email;
        } else {
            throw new NotBoundException("Not valid email");
        }
    }


    public void setDoctor_id(int doctor_id) { this.doctor_id = doctor_id; }

    public int getDoctor_id() { return doctor_id; }

    @Override
    public String toString() {
        return "Patient{" +
                "patient_id=" + patient_id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }

    public String toString2(){
        return "Patient{" +
                "patient_id=" + patient_id +
                ", name='" + name + '\'' +
                ", surname='" + surname +
                '}';
    }
}
