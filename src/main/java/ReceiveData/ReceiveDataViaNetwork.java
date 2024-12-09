package ReceiveData;

import Pojos.*;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class ReceiveDataViaNetwork {

    private DataInputStream dataInputStream;

    public ReceiveDataViaNetwork(Socket socket){
        try {
            this.dataInputStream = new DataInputStream(socket.getInputStream());
        }catch (IOException e){

        }
    }

    public String receiveString() throws IOException {
           String information ;
           information = dataInputStream.readUTF();

           return information;
       }

       public Doctor receiveDoctor(){

           Doctor doctor = null;

           try {
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
           }

           return doctor;
       }

       public Patient recievePatient(){
           Patient patient = null;

           try {
               int id = dataInputStream.readInt();
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
           }

           return patient;
       }
       public Interpretation recieveInterpretation(){
           Interpretation interpretation = null;

           try {

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

           }

           return interpretation;
       }

       public int receiveInt(){
           int message = 10;
           try{
               message = dataInputStream.readInt();
           }catch(IOException ex){
               ex.printStackTrace();
           }

           return message;
       }

       public User recieveUser(Socket socket)
       {
           User u = null;
           try{
               String email = dataInputStream.readUTF();
               byte[] psw = dataInputStream.readUTF().getBytes();
               String role = dataInputStream.readUTF();
               Role r = new Role(role);
               u = new User(email,psw,r);

           }catch (IOException e){
               e.printStackTrace();
           }
           return u;
       }

       public void releaseResources(){
           try {
               dataInputStream.close();
           } catch (IOException ex) {
               ex.printStackTrace();
           }
       }

   }
