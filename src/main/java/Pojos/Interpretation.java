package Pojos;


import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import Pojos.Signal.SignalType;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public List<Symptoms> getSymptoms() {
        return symptoms;
    }

    public String getObservation() {
        return observation;
    }

    public void setSymptoms(List<Symptoms> symptoms) {
        this.symptoms = symptoms;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public Signal getSignalEMG() {
        return signalEMG;
    }

    public void setSignalEMG(Signal signalEMG) {
        this.signalEMG = signalEMG;
    }

    public Signal getSignalEDA() {
        return signalEDA;
    }

    public void addSymptom(Symptoms symptom){
        this.symptoms.add(symptom);
    }

    public void setSignalEDA(Signal signalEDA) {
        this.signalEDA = signalEDA;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

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
            double average= calculateAverage(filteredValues);
            int max= calculateMax(filteredValues);
            System.out.println("EMG average value= " + average);
            System.out.println("EMG max value= " + max);
        } else if (signalType == SignalType.EDA) {
            double average2= calculateAverage(filteredValues);
            int max2= calculateMax(filteredValues);
            System.out.println("EDA average value= " + average2);
            System.out.println("EDA max value= " + max2);
        } else {
            System.out.println("Unknown signal type.");
        }
    }


    private double calculateAverage(List<Integer> Values) {
        double average;
        if (Values == null || Values.isEmpty()) {
            return 0.0;
        }
        double total = 0;
        for (int value : Values) {
            total += value;
        }
        average= total/Values.size();
        return average;
    }

    private int calculateMax(List<Integer> Values) {
        if (Values == null || Values.isEmpty()) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        for (int value : Values) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    @Override
    public String toString() {
        return "Report " + date + ":"+
                "\n signalEMG=" + signalEMG +
                "\n signalEDA=" + signalEDA +
                "\n Your Symptoms: " + symptoms +
                "\n Your observations: '" + observation + '\'' +
                "\n Doctor´s notes: '" + interpretation + '\'';
    }

}

