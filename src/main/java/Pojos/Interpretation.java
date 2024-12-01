package Pojos;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import Pojos.Signal.SignalType;

public class Interpretation {

    private LocalDate date;
    private Patient patient;
    private Doctor doctor;
    private String interpretation;

    public Interpretation(LocalDate date, Patient patient, Doctor doctor, String interpretation) {
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.interpretation = interpretation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getInterpretation() {
        return interpretation;
    }

    public void setInterpretation(String interpretation) {
        this.interpretation = interpretation;
    }

    public String analyzeBitalinoData(List<Integer> rawValues, SignalType signalType) {
        List<Integer> filteredValues = rawValues.stream()
                .filter(value -> value >= 50 && value <= 900)
                .collect(Collectors.toList());

        if (filteredValues.isEmpty()) {
            return "No valid data to analyze.";
        }

        if (signalType == SignalType.EMG) {
            return analyzeEMG(filteredValues);
        } else if (signalType == SignalType.EDA) {
            return analyzeEDA(filteredValues);
        } else {
            return "Unknown signal type.";
        }
    }

    private double convertRawValue(int rawValue) {
        return (rawValue * 3.3) / (Math.pow(2, 10) - 1);
    }

    private String analyzeEMG(List<Integer> emgValues) {
        double average = emgValues.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
        int max = emgValues.stream().mapToInt(Integer::intValue).max().orElse(0);

        StringBuilder analysis = new StringBuilder();
        analysis.append("EMG Analysis:\n");
        analysis.append("Average Amplitude: ").append(String.format("%.2f µV", average)).append("\n");
        analysis.append("Max Amplitude: ").append(max).append(" µV\n");

        if (max > 500) {
            analysis.append("Observation: High muscle activity, possible tremors detected.\n");
        } else if (average < 100) {
            analysis.append("Observation: Low muscle activity, possible rigidity or bradykinesia.\n");
        } else {
            analysis.append("Observation: Normal muscle activity.\n");
        }

        return analysis.toString();
    }

    private String analyzeEDA(List<Integer> edaValues) {
        double average = edaValues.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
        int max = edaValues.stream().mapToInt(Integer::intValue).max().orElse(0);

        StringBuilder analysis = new StringBuilder();
        analysis.append("EDA Analysis:\n");
        analysis.append("Average Conductance: ").append(String.format("%.2f µS", average)).append("\n");
        analysis.append("Max Conductance: ").append(max).append(" µS\n");

        if (average < 1.0) {
            analysis.append("Observation: Low autonomic response, indicative of reduced stress.\n");
        } else if (max > 15) {
            analysis.append("Observation: High autonomic response, possible stress detected.\n");
        } else {
            analysis.append("Observation: Normal autonomic activity.\n");
        }

        return analysis.toString();
    }

    @Override
    public String toString() {
        return "Interpretation{" +
                "date=" + date +
                ", patient=" + patient +
                ", doctor=" + doctor +
                ", interpretation='" + interpretation + '\'' +
                '}';
    }
}

