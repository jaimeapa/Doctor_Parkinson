package UI;


import Pojos.*;
import ReceiveData.ReceiveDataViaNetwork;
import ReceiveData.SendDataViaNetwork;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LogInMenu {
    private static Socket socket;
    private static Role role;

    public static void main(String[] args) throws IOException {

        socket = new Socket("localhost", 8000);

        SendDataViaNetwork.sendInt(2, socket);
        role = new Role("doctor");
        while(true){
            switch (printLogInMenu()) {
                case 1 : {
                    SendDataViaNetwork.sendInt(1, socket);
                    registerDoctor(socket);
                    break;
                }
                case 2 :{
                    SendDataViaNetwork.sendInt(2, socket);
                    logInMenu(socket);
                    break;
                }
                case 3 :{
                    System.out.println("Exiting...");
                    SendDataViaNetwork.sendInt(3, socket);
                    releaseResources(socket);
                    System.exit(0);
                }
                default:{
                    System.out.println("That number is not an option, try again");
                    break;
                }
            }
        }
    }

    private static void logInMenu(Socket socket) throws IOException{
        String email = Utilities.readString("Email: ");
        String password = Utilities.readString("Password: ");
        User u = new User (email,password.getBytes(), role);
        SendDataViaNetwork.sendUser(u, socket);
        try {
            Doctor doctor = ReceiveDataViaNetwork.receiveDoctor(socket);
            if (doctor != null) {
                if(doctor.getName().equals("name")){
                    System.out.println("User or password is incorrect");
                }else {
                    System.out.println("Log in successful");
                    System.out.println(doctor.toString());
                    clientDoctorMenu(doctor);
                }
            }
        }catch(IOException e){
            System.out.println("Log in problem");
        }
    }

    private static int printLogInMenu() {
        System.out.println("\n\nDoctor Menu:\n"
                + "\n1. Register"
                + "\n2. Log In"
                + "\n3. Exit"
        );
        return Utilities.readInteger("What would you want to do?\n");
    }

    public static void registerDoctor(Socket socket) throws IOException
    {
        Doctor doctor;
        User u;
        String name = Utilities.readString("Enter your name: ");
        String surname = Utilities.readString("Enter your last name: ");
        LocalDate dob = Utilities.readDate("Enter your date of birth: ");
        System.out.println(dob.toString());
        String email = Utilities.readString("Enter your email: ");
        doctor = new Doctor(1,name,surname,dob,email);
        String password = Utilities.readString("Enter your password: ");
        u = new User(email, password.getBytes(), role);
        System.out.println(doctor.toString());
        System.out.println(u.toString());
        SendDataViaNetwork.sendDoctor(doctor,socket);
        SendDataViaNetwork.sendUser(u, socket);
        clientDoctorMenu(doctor);
    }

    public static void clientDoctorMenu(Doctor doctor_logedIn) throws IOException {
        Doctor doctor = doctor_logedIn;
        boolean menu = true;
        while(menu){
            switch(printDoctorMenu()){
                case 1:{
                    SendDataViaNetwork.sendInt(1, socket);
                    viewDetailsOfPatient();
                    break;
                }
                case 2:{
                    SendDataViaNetwork.sendInt(2, socket);
                    makeAnInterpretation();
                    break;
                }
                case 3:{
                    menu = false;
                    SendDataViaNetwork.sendInt(3, socket);
                    System.out.println("Closing server");
                    break;
                }
            }
        }
    }

    private static int printDoctorMenu(){
        System.out.println("\n\nDiagnosis Menu:\n"
                + "\n1. View details of a specific patient"
                + "\n2. Make an interpretation of the results"
                + "\n3. Log out"
        );
        return Utilities.readInteger("What would you want to do?\n");
    }

    private static void viewDetailsOfPatient() throws IOException {
        Patient patient;
        int size = ReceiveDataViaNetwork.receiveInt(socket);
        System.out.println("Receiving " + size + " patients");
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                patient = ReceiveDataViaNetwork.recievePatient(socket);
                System.out.println(i + 1 + ". " + patient.getSurname() + ", " + patient.getName());
            }
            boolean mandarID = true;
            int patientID;
            while (mandarID) {
                patientID = Utilities.readInteger("Select the id of the patient from which you want to see the information");
                if (patientID > 0 && patientID < size + 1) {
                    mandarID = false;
                    SendDataViaNetwork.sendInt(patientID - 1, socket);
                } else {
                    System.out.println("There are no patients with that ID!");
                    System.out.println("Select the id of the patient from which you want to see the information");
                }
            }
            patient = ReceiveDataViaNetwork.recievePatient(socket);
            System.out.println(patient.toString());
        }else{
            System.out.println("You have no patients assigned to you");
        }
    }

    private static void makeAnInterpretation() throws IOException {
        Interpretation interpretation;
        int size = ReceiveDataViaNetwork.receiveInt(socket);
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                Patient patient2 = ReceiveDataViaNetwork.recievePatient(socket);
                System.out.println(i + 1 + ". " + patient2.getSurname() + ", " + patient2.getName());
            }
            boolean mandarID = true;
            int patientID;
            while (mandarID) {
                patientID = Utilities.readInteger("Select the id of the patient from which you want to see the information\n");
                if (patientID > 0 && patientID < size + 1) {
                    mandarID = false;
                    SendDataViaNetwork.sendInt(patientID - 1, socket);
                } else {
                    System.out.println("There are no patients with that ID!");
                    System.out.println("Select the id of the patient from which you want to see the information");
                }
            }
            String message = ReceiveDataViaNetwork.receiveString(socket);
            if (message.equals("ERROR")) {
                System.out.println("This patient doesn't have any reports");
            } else {
                int size2 = ReceiveDataViaNetwork.receiveInt(socket);
                for (int i = 0; i < size2; i++) {
                    interpretation = ReceiveDataViaNetwork.recieveInterpretation(socket);
                    System.out.println(i + 1 + ". " + interpretation.getDate());
                }
                boolean mandarID2 = true;
                int interpretationID;
                while (mandarID2) {
                    interpretationID = Utilities.readInteger("Select the id of the report of the patient from which you want to see the information and make an interpretation");
                    if (interpretationID > 0 && interpretationID < size2 + 1) {
                        mandarID2 = false;
                        SendDataViaNetwork.sendInt(interpretationID - 1, socket);
                    } else {
                        System.out.println("There are no reports with that ID!");
                        System.out.println("Select the id of the report of the patient from which you want to see the information and make an interpretation");
                    }
                }
                interpretation = ReceiveDataViaNetwork.recieveInterpretation(socket);
                int size3 = ReceiveDataViaNetwork.receiveInt(socket);
                System.out.println(size3);
                if(size3 > 0) {
                    for (int i = 0; i < size3; i++) {
                       String symptoms= ReceiveDataViaNetwork.receiveString(socket);
                       interpretation.addSymptom(new Symptoms(symptoms));
                    }

                }
                System.out.println(interpretation.toString());
                String interpretation2 = Utilities.readString("Write here your interpretation: ");
                SendDataViaNetwork.sendStrings(interpretation2, socket);
            }
        }else{
            System.out.println("You have no patients assigned to you");
        }
    }


    private static void releaseResources(Socket socket){
        try {
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

