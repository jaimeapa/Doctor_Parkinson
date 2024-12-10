package Pojos;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Represents a patient with personal information and a list of medical interpretations.
 */
public class Patient {
    /**
     * Unique identifier for the patient.
     */
    private int patient_id;

    /**
     * First name of the patient.
     */
    private String name;

    /**
     * Last name of the patient.
     */
    private String surname;

    /**
     * Date of birth of the patient.
     */
    private LocalDate dob;

    /**
     * Email address of the patient.
     */
    private String email;

    /**
     * Unique identifier for the doctor assigned to the patient.
     */
    private int doctor_id;

    /**
     * List of medical interpretations or analyses associated with the patient.
     */
    private LinkedList<Interpretation> interpretations;

    /**
     * Constructs a Patient with the specified details.
     *
     * @param patient_id the unique identifier of the patient.
     * @param name       the first name of the patient.
     * @param surname    the last name of the patient.
     * @param dob        the date of birth of the patient.
     * @param email      the email address of the patient.
     */
    public Patient(int patient_id, String name, String surname, LocalDate dob, String email) {
        this.patient_id = patient_id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.interpretations = new LinkedList<>();
    }

    /**
     * Gets the unique identifier of the patient.
     *
     * @return the patient ID.
     */
    public int getPatient_id() {
        return patient_id;
    }

    /**
     * Sets the unique identifier of the patient.
     *
     * @param patient_id the patient ID.
     */
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    /**
     * Gets the first name of the patient.
     *
     * @return the first name of the patient.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the patient.
     *
     * @param name the first name of the patient.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name of the patient.
     *
     * @return the last name of the patient.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the last name of the patient.
     *
     * @param surname the last name of the patient.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the date of birth of the patient.
     *
     * @return the date of birth of the patient.
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the patient.
     *
     * @param dob the date of birth of the patient.
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Gets the email address of the patient.
     *
     * @return the email address of the patient.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the patient.
     *
     * @param email the new email address of the patient.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     * Gets the doctor ID associated with the patient.
     *
     * @return the doctor ID.
     */
    public int getDoctor_id() {
        return doctor_id;
    }

    /**
     * Sets the doctor ID associated with the patient.
     *
     * @param doctor_id the doctor ID.
     */
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    /**
     * Returns a string representation of the patient.
     *
     * @return a string containing the patient's personal details.
     */
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
}
