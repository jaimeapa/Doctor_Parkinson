package ReceiveData;

import Pojos.*;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReceiveDataViaNetwork {

    public static String receiveString(DataInputStream dataInputStream) throws IOException {
        String information;
        information = dataInputStream.readUTF();
        return information;
    }

    public static Doctor receiveDoctor(DataInputStream dataInputStream){
        Doctor doctor = null;

        try {
            //Object tmp;
            int id = dataInputStream.readInt();
            String name = dataInputStream.readUTF();
            String surname = dataInputStream.readUTF();
            String date = dataInputStream.readUTF();
            String email = dataInputStream.readUTF();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(date, formatter);
            doctor = new Doctor(id,name,surname,dob,email);
        } catch (EOFException ex) {
            System.out.println("All data have been correctly read.");
        } catch (IOException  ex) {
            System.out.println("Unable to read from the client.");
            ex.printStackTrace();
            //Logger.getLogger(ReceiveClientViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }

        return doctor;
    }
    public static Patient recievePatient(DataInputStream dataInputStream){
        Patient patient = null;

        try {
            int id  = dataInputStream.readInt();
            String name = dataInputStream.readUTF();
            String surname = dataInputStream.readUTF();
            String date = dataInputStream.readUTF();
            String email = dataInputStream.readUTF();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dob = LocalDate.parse(date, formatter);
            patient = new Patient(id,name,surname,dob,email);
        } catch (EOFException ex) {
            System.out.println("All data have been correctly read.");
        } catch (IOException  ex) {
            System.out.println("Unable to read from the client.");
            ex.printStackTrace();
            //Logger.getLogger(ReceiveClientViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }

        return patient;
    }
    public static int receiveInt(DataInputStream dataInputStream) {
        int message=-1;
        try{
            message = dataInputStream.readInt();
        }catch (EOFException ex) {
            System.out.println("All data have been correctly read.");
        }catch (IOException  ex) {
            System.out.println("Unable to read from the client.");
            ex.printStackTrace();
        }
        return message;
    }

    public static Interpretation recieveInterpretation(DataInputStream dataInputStream){
        Interpretation interpretation = null;

        try {
            //Object tmp;
            String stringDate = dataInputStream.readUTF();
            int doctor_id = dataInputStream.readInt();
            String stringEMG = dataInputStream.readUTF();
            int patient_id = dataInputStream.readInt();
            String stringEDA = dataInputStream.readUTF();
            String observation = dataInputStream.readUTF();
            String interpretation1 = dataInputStream.readUTF();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(stringDate, formatter);
            Signal signalEMG = new Signal(Signal.SignalType.EMG);
            signalEMG.setValuesEMG(stringEMG);
            Signal signalEDA = new Signal(Signal.SignalType.EDA);
            signalEDA.setValuesEDA(stringEDA);
            interpretation = new Interpretation(date, interpretation1, signalEMG, signalEDA, patient_id, doctor_id, observation);
        } catch (EOFException ex) {
            System.out.println("All data have been correctly read.");
        } catch (IOException  ex) {
            System.out.println("Unable to read from the client.");
            ex.printStackTrace();
            //Logger.getLogger(ReceiveClientViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }

        return interpretation;
    }
}