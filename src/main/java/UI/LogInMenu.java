package UI;


import Encryption.EncryptPassword;
import Pojos.*;
import ReceiveData.ReceiveDataViaNetwork;
import ReceiveData.SendDataViaNetwork;
import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LogInMenu {


    public static void main(String[] args) throws IOException {
        while(true) {
            String ipAdress = Utilities.readString("Write the IP address of the server you want to connect to:\n");
            Socket socket = new Socket(ipAdress, 8000);
            SendDataViaNetwork sendDataViaNetwork = new SendDataViaNetwork(socket);
            ReceiveDataViaNetwork receiveDataViaNetwork = new ReceiveDataViaNetwork(socket);
            sendDataViaNetwork.sendInt(2);
            String message = receiveDataViaNetwork.receiveString();
            System.out.println(message);
            if(message.equals("DOCTOR")) {
                while (true) {
                    switch (printLogInMenu()) {
                        case 1: {
                            sendDataViaNetwork.sendInt(1);
                            registerDoctor(sendDataViaNetwork, receiveDataViaNetwork);
                            break;
                        }
                        case 2: {
                            sendDataViaNetwork.sendInt(2);
                            logInMenu(sendDataViaNetwork, receiveDataViaNetwork);
                            break;
                        }
                        case 3: {
                            System.out.println("Exiting...");
                            sendDataViaNetwork.sendInt(3);
                            releaseResources(socket, sendDataViaNetwork, receiveDataViaNetwork);
                            System.exit(0);
                        }
                        default: {
                            System.out.println("That number is not an option, try again");
                            break;
                        }
                    }
                }
            }else{
                System.out.println("Error in connection");
            }
        }
    }

    private static void logInMenu(SendDataViaNetwork sendDataViaNetwork, ReceiveDataViaNetwork receiveDataViaNetwork) throws IOException{
        String email = Utilities.readString("Email: ");
        String psw = Utilities.readString("Password: ");
        byte[] password;
        Role role = new Role("doctor");
        try {
            password = EncryptPassword.encryptPassword(psw);
        }catch(NoSuchAlgorithmException e){
            System.out.println("Error when encrypting the password");
            password = null;
        }
        if(password != null) {
            sendDataViaNetwork.sendStrings("OK");
            User u = new User(email, password, role);
            sendDataViaNetwork.sendUser(u);
            String message = receiveDataViaNetwork.receiveString();
            if (message.equals("OK")) {
                try {
                    Doctor doctor = receiveDataViaNetwork.receiveDoctor();
                    if (doctor != null) {
                        System.out.println("Log in successful");
                        System.out.println(doctor);
                        clientDoctorMenu(sendDataViaNetwork, receiveDataViaNetwork);
                    }
                } catch (IOException e) {
                    System.out.println("Log in problem");
                }
            } else if (message.equals("ERROR")) {
                System.out.println("User or password is incorrect");
            }
        }else{
            sendDataViaNetwork.sendStrings("ERROR");
        }
    }

    private static int printLogInMenu() {
        System.out.println("\n\n Doctor Menu:\n"
                + "\n1. Register"
                + "\n2. Log In"
                + "\n3. Exit"
        );
        return Utilities.readInteger("What would you want to do?\n");
    }

    public static void registerDoctor(SendDataViaNetwork sendDataViaNetwork, ReceiveDataViaNetwork receiveDataViaNetwork) throws IOException
    {
        Doctor doctor;
        User u;
        String name = Utilities.readString("Enter your name: ");
        String surname = Utilities.readString("Enter your last name: ");
        LocalDate dob = Utilities.readDate("Enter your date of birth: ");
        System.out.println(dob);
        String email = Utilities.readString("Enter your email: ");
        doctor = new Doctor(1,name,surname,dob,email);
        String psw = Utilities.readString("Password: ");
        byte[] password;
        Role role = new Role("doctor");
        try {
            password = EncryptPassword.encryptPassword(psw);
        }catch(NoSuchAlgorithmException e){
            System.out.println("Error when encrypting the password");
            password = null;
        }
        if(password != null) {
            sendDataViaNetwork.sendStrings("OK");
            u = new User(email, password, role);
            sendDataViaNetwork.sendDoctor(doctor);
            sendDataViaNetwork.sendUser(u);
            clientDoctorMenu(sendDataViaNetwork, receiveDataViaNetwork);
        }else{
            sendDataViaNetwork.sendStrings("ERROR");
        }
    }

    public static void clientDoctorMenu(SendDataViaNetwork sendDataViaNetwork, ReceiveDataViaNetwork receiveDataViaNetwork) throws IOException {

        boolean menu = true;
        while(menu){
            switch(printDoctorMenu()){
                case 1:{
                    sendDataViaNetwork.sendInt(1);
                    viewDetailsOfPatient(sendDataViaNetwork, receiveDataViaNetwork);
                    break;
                }
                case 2:{
                    sendDataViaNetwork.sendInt(2);
                    makeAnInterpretation(sendDataViaNetwork, receiveDataViaNetwork);
                    break;
                }
                case 3:{
                    menu = false;
                    sendDataViaNetwork.sendInt(3);
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

    private static void viewDetailsOfPatient(SendDataViaNetwork sendDataViaNetwork, ReceiveDataViaNetwork receiveDataViaNetwork){
        Patient patient;
        int size = receiveDataViaNetwork.receiveInt();
        System.out.println("Receiving " + size + " patients");
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                patient = receiveDataViaNetwork.recievePatient();
                System.out.println(i + 1 + ". " + patient.getSurname() + ", " + patient.getName());
            }
            boolean mandarID = true;
            int patientID;
            while (mandarID) {
                patientID = Utilities.readInteger("Select the id of the patient from which you want to see the information\n");
                if (patientID > 0 && patientID < size + 1) {
                    mandarID = false;
                    sendDataViaNetwork.sendInt(patientID - 1);
                } else {
                    System.out.println("There are no patients with that ID!");
                    System.out.println("Select the id of the patient from which you want to see the information\n");
                }
            }
            patient = receiveDataViaNetwork.recievePatient();
            System.out.println(patient.toString());
        }else{
            System.out.println("You have no patients assigned to you");
        }
    }

    private static void makeAnInterpretation(SendDataViaNetwork sendDataViaNetwork, ReceiveDataViaNetwork receiveDataViaNetwork) throws IOException {
        Interpretation interpretation;
        int size = receiveDataViaNetwork.receiveInt();
        if(size > 0) {
            for (int i = 0; i < size; i++) {
                Patient patient2 = receiveDataViaNetwork.recievePatient();
                System.out.println(i + 1 + ". " + patient2.getSurname() + ", " + patient2.getName());
            }
            boolean mandarID = true;
            int patientID;
            while (mandarID) {
                patientID = Utilities.readInteger("Select the id of the patient from which you want to see the information\n");
                if (patientID > 0 && patientID < size + 1) {
                    mandarID = false;
                    sendDataViaNetwork.sendInt(patientID - 1);
                } else {
                    System.out.println("There are no patients with that ID!");
                    System.out.println("Select the id of the patient from which you want to see the information\n");
                }
            }
            String message = receiveDataViaNetwork.receiveString();
            if (message.equals("ERROR")) {
                System.out.println("This patient doesn't have any reports");
            } else {
                int size2 = receiveDataViaNetwork.receiveInt();
                for (int i = 0; i < size2; i++) {
                    interpretation = receiveDataViaNetwork.recieveInterpretation();
                    System.out.println(i + 1 + ". " + interpretation.getDate());
                }
                boolean mandarID2 = true;
                int interpretationID;
                while (mandarID2) {
                    interpretationID = Utilities.readInteger("Select the id of the report of the patient from which you want to see the information and make an interpretation\n");
                    if (interpretationID > 0 && interpretationID < size2 + 1) {
                        mandarID2 = false;
                        sendDataViaNetwork.sendInt(interpretationID - 1);
                    } else {
                        System.out.println("There are no reports with that ID!");
                        System.out.println("Select the id of the report of the patient from which you want to see the information and make an interpretation\n");
                    }
                }
                interpretation = receiveDataViaNetwork.recieveInterpretation();
                int size3 = receiveDataViaNetwork.receiveInt();
                if(size3 > 0) {
                    for (int i = 0; i < size3; i++) {
                       String symptoms= receiveDataViaNetwork.receiveString();
                       System.out.println(symptoms);
                       interpretation.addSymptom(new Symptoms(symptoms));
                    }
                }
                System.out.println(interpretation.toString());
                System.out.println(interpretation.analyzeBitalinoData(interpretation.getSignalEDA().getValues(), Signal.SignalType.EDA));
                System.out.println(interpretation.analyzeBitalinoData(interpretation.getSignalEMG().getValues(), Signal.SignalType.EMG));
                String interpretation2 = Utilities.readString("Write here your interpretation: ");
                sendDataViaNetwork.sendStrings(interpretation2);
            }
        }else{
            System.out.println("You have no patients assigned to you");
        }
    }


    private static void releaseResources(Socket socket, SendDataViaNetwork sendDataViaNetwork, ReceiveDataViaNetwork receiveDataViaNetwork){
        sendDataViaNetwork.releaseResources();
        receiveDataViaNetwork.releaseResources();
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(LogInMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

