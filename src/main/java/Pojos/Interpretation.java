package Pojos;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
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

    public String analyzeBitalinoData(List<Integer> rawValues, SignalType signalType) {
        List<Integer> filteredValues = new LinkedList<>();
        for (Integer value : rawValues) {
            if (value >= 50 && value <= 900) {
                filteredValues.add(value);
            }
        }
        if (filteredValues.isEmpty()) {
            return "No valid data to analyze.";
        }
        if (signalType == SignalType.EMG) {
            return analyzeEMGForParkinson(filteredValues);
        } else if (signalType == SignalType.EDA) {
            return analyzeEDAForParkinson(filteredValues);
        } else {
            return "Unknown signal type.";
        }
    }



    private String analyzeEMGForParkinson(List<Integer> emgValues) {
        double total = 0;
        double max = 0;
        for (int value : emgValues) {
            total += value;
            if (value > max) {
                max = value;
            }
        }
        double average = 0;
        if (!emgValues.isEmpty()) {
            average = total / emgValues.size();
        }
        return "EMG Parkinson Analysis:\n" +
                "Average Amplitude: " + String.format("%.2f µV", average) + "\n" +
                "Max Amplitude: " + String.format("%.2f µV", max) + "\n";
        //return String.format("EMG Parkinson Analysis:\n Average: %.2f µV, Max: %.2f µV", average, max);
    }


    private String analyzeEDAForParkinson(List<Integer> edaValues) {
        double total = 0;
        double max = 0;
        for (int value : edaValues) {
            total += value;
            if (value > max) {
                max = value;
            }
        }
        double average = 0;
        if (!edaValues.isEmpty()) {
            average = total / edaValues.size();
        }
        String message = "EDA Parkinson Analysis:\n" +
                "Average Conductance: " + String.format("%.2f µS", average) + "\n" +
                "Max Conductance: " + String.format("%.2f µS", max) + "\n";
        return message;
        //return String.format("EDA Parkinson Analysis:\n Average: %.2f µV, Max: %.2f µV", average, max);
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

