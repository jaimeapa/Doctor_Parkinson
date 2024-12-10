package Pojos;

import java.time.LocalDate;
import java.util.LinkedList;

/**
 * Represents a doctor with personal information and a list of assigned patients.
 */
public class Doctor {
    /**
     * Unique identifier for the doctor.
     */
    private int doctor_id;

    /**
     * First name of the doctor.
     */
    private String name;

    /**
     * Last name of the doctor.
     */
    private String surname;

    /**
     * Date of birth of the doctor.
     */
    private LocalDate dob;

    /**
     * Email address of the doctor.
     */
    private String email;

    /**
     * Identifier for the associated user account of the doctor.
     * Can be null if not linked to a user account.
     */
    private Integer userId;

    /**
     * List of patients assigned to the doctor.
     */
    private LinkedList<Patient> patients;
    /**
     * Constructs a Doctor with the specified details.
     *
     * @param id      the unique identifier for the doctor.
     * @param name    the first name of the doctor.
     * @param surname the last name of the doctor.
     * @param dob     the date of birth of the doctor.
     * @param email   the email address of the doctor.
     */
    public Doctor(int id, String name, String surname, LocalDate dob, String email) {
        this.doctor_id = id;
        this.name = name;
        this.surname = surname;
        this.dob = dob;
        this.email = email;
        this.patients = new LinkedList<>();
    }

    /**
     * Gets the unique identifier of the doctor.
     *
     * @return the doctor ID.
     */
    public int getDoctor_id() {
        return doctor_id;
    }

    /**
     * Sets the unique identifier of the doctor.
     *
     * @param doctor_id the new doctor ID.
     */
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    /**
     * Gets the first name of the doctor.
     *
     * @return the doctor's first name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the first name of the doctor.
     *
     * @param name the new first name of the doctor.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the last name of the doctor.
     *
     * @return the doctor's last name.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the last name of the doctor.
     *
     * @param surname the new last name of the doctor.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets the date of birth of the doctor.
     *
     * @return the doctor's date of birth.
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Sets the date of birth of the doctor.
     *
     * @param dob the new date of birth of the doctor.
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Gets the email address of the doctor.
     *
     * @return the doctor's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the doctor.
     *
     * @param email the new email address of the doctor.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user ID associated with the doctor.
     *
     * @return the user ID of the doctor.
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the doctor.
     *
     * @param userId the new user ID of the doctor.
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * Gets the list of patients assigned to the doctor.
     *
     * @return a linked list of {@link Patient} objects.
     */
    public LinkedList<Patient> getPatients() {
        return patients;
    }

    /**
     * Sets the list of patients assigned to the doctor.
     *
     * @param patients a linked list of {@link Patient} objects.
     */
    public void setPatients(LinkedList<Patient> patients) {
        this.patients = patients;
    }

    /**
     * Returns a string representation of the doctor.
     *
     * @return a string containing the doctor's details.
     */
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

