package Pojos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import Pojos.Signal.SignalType;

/**
 * Represents an interpretation of medical data, including signals, symptoms, and observations.
 */
public class Interpretation {
    private int id;
    private LocalDate date;
    private int patient_id;
    private int doctor_id;
    private Signal signalEMG;
    private Signal signalEDA;
    private String interpretation;
    private List<Symptoms> symptoms;
    private String observation;

    /**
     * Constructs an Interpretation with the specified details.
     *
     * @param date           the date of the interpretation.
     * @param interpretation the doctor's interpretation notes.
     * @param signalEMG      the EMG signal data.
     * @param signalEDA      the EDA signal data.
     * @param patient_id     the ID of the patient.
     * @param doctor_id      the ID of the doctor.
     * @param observation    additional observations about the interpretation.
     */
    public Interpretation(LocalDate date, String interpretation, Signal signalEMG, Signal signalEDA, int patient_id, int doctor_id, String observation) {
        this.date = date;
        this.interpretation = interpretation;
        this.symptoms = new LinkedList<>();
        this.signalEMG = signalEMG;
        this.signalEDA = signalEDA;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.observation = observation;
    }

    /**
     * Gets the unique identifier of the interpretation.
     *
     * @return the ID of the interpretation.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the interpretation.
     *
     * @param id the ID of the interpretation.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the date of the interpretation.
     *
     * @return the date of the interpretation.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the date of the interpretation.
     *
     * @param date the date of the interpretation.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Gets the patient ID associated with the interpretation.
     *
     * @return the patient ID.
     */
    public int getPatient_id() {
        return patient_id;
    }

    /**
     * Sets the patient ID associated with the interpretation.
     *
     * @param patient_id the patient ID.
     */
    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    /**
     * Gets the doctor ID associated with the interpretation.
     *
     * @return the doctor ID.
     */
    public int getDoctor_id() {
        return doctor_id;
    }

    /**
     * Sets the doctor ID associated with the interpretation.
     *
     * @param doctor_id the doctor ID.
     */
    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    /**
     * Gets the doctor's interpretation notes.
     *
     * @return the interpretation notes.
     */
    public String getInterpretation() {
        return interpretation;
    }

    /**
     * Sets the doctor's interpretation notes.
     *
     * @param interpretation the interpretation notes.
     */
    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    /**
     * Gets the EMG signal data.
     *
     * @return the EMG signal.
     */
    public Signal getSignalEMG() {
        return signalEMG;
    }

    /**
     * Sets the EMG signal data.
     *
     * @param signalEMG the EMG signal.
     */
    public void setSignalEMG(Signal signalEMG) {
        this.signalEMG = signalEMG;
    }

    /**
     * Gets the EDA signal data.
     *
     * @return the EDA signal.
     */
    public Signal getSignalEDA() {
        return signalEDA;
    }

    /**
     * Sets the EDA signal data.
     *
     * @param signalEDA the EDA signal.
     */
    public void setSignalEDA(Signal signalEDA) {
        this.signalEDA = signalEDA;
    }

    /**
     * Gets the list of symptoms.
     *
     * @return the list of symptoms.
     */
    public List<Symptoms> getSymptoms() {
        return symptoms;
    }

    /**
     * Sets the list of symptoms.
     *
     * @param symptoms the list of symptoms.
     */
    public void setSymptoms(List<Symptoms> symptoms) {
        this.symptoms = symptoms;
    }

    /**
     * Adds a symptom to the list.
     *
     * @param symptom the symptom to add.
     */
    public void addSymptom(Symptoms symptom) {
        this.symptoms.add(symptom);
    }

    /**
     * Gets additional observations.
     *
     * @return the observations.
     */
    public String getObservation() {
        return observation;
    }

    /**
     * Sets additional observations.
     *
     * @param observation the observations.
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * Analyzes Bitalino data for a specific signal type (EMG or EDA).
     * Filters values within a range and calculates average and max values.
     *
     * @param values     the raw signal values to analyze.
     * @param signalType the type of signal (EMG or EDA).
     */
    public void analyzeBitalinoData(List<Integer> values, SignalType signalType) {
        List<Integer> filteredValues = new LinkedList<>();
        for (Integer value : values) {
            if (value >= 50 && value <= 900) {
                filteredValues.add(value);
            }
        }
        if (filteredValues.isEmpty()) {
            System.out.println("No valid data to analyze.");
        }
        if (signalType == SignalType.EMG) {
            double average = calculateAverage(filteredValues);
            int max = calculateMax(filteredValues);
            System.out.println("EMG average value= " + average);
            System.out.println("EMG max value= " + max);
        } else if (signalType == SignalType.EDA) {
            double average2 = calculateAverage(filteredValues);
            int max2 = calculateMax(filteredValues);
            System.out.println("EDA average value= " + average2);
            System.out.println("EDA max value= " + max2);
        } else {
            System.out.println("Unknown signal type.");
        }
    }

    /**
     * Calculates the average value from a list of integers.
     *
     * @param values the list of integer values.
     * @return the average value, or 0 if the list is empty or null.
     */
    private double calculateAverage(List<Integer> values) {
        double average;
        if (values == null || values.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (int value : values) {
            total += value;
        }
        average=total / values.size();
        return average;
    }

    /**
     * Calculates the maximum value from a list of integers.
     *
     * @param values the list of integer values.
     * @return the maximum value, or 0 if the list is empty or null.
     */
    private int calculateMax(List<Integer> values) {
        if (values == null || values.isEmpty()) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int value : values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    /**
     * Returns a string representation of the interpretation.
     *
     * @return a string containing the date, signals, symptoms, observations, and doctor's notes.
     */
    @Override
    public String toString() {
        return "Report " + date + ":" +
                "\n signalEMG=" + signalEMG +
                "\n signalEDA=" + signalEDA +
                "\n Your Symptoms: " + symptoms +
                "\n Your observations: '" + observation + '\'' +
                "\n Doctor´s notes: '" + interpretation + '\'';
    }
}

