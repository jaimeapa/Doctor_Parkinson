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
    private static ObjectOutputStream objectOutputStream;
    private static Socket socket;
    private static OutputStream outputStream;
    private static DataOutputStream dataOutputStream;
    private static PrintWriter printWriter;
    private static BufferedReader bufferedReader;
    private static DataInputStream dataInputStream;
    private static ObjectInputStream objectInputStream;
    private static Role role;
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        //Patient patient = null;
        socket = new Socket("localhost", 8000);

        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream = socket.getOutputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printWriter = new PrintWriter(socket.getOutputStream(), true);
        //InputStream inputStream = socket.getInputStream();
        dataInputStream = new DataInputStream(socket.getInputStream());
        objectInputStream = new ObjectInputStream(socket.getInputStream());

        SendDataViaNetwork.sendInt(2, dataOutputStream);
        role = new Role("doctor");
        //SendDataViaNetwork.sendInt(1,socket, dataOutputStream);
        while(true){
            switch (printLogInMenu()) {
                case 1 : {
                    SendDataViaNetwork.sendInt(1, dataOutputStream);
                    registerDoctor(socket);
                    break;
                }
                case 2 :{
                    SendDataViaNetwork.sendInt(2, dataOutputStream);
                    logInMenu(socket);
                    break;
                }
                case 3 :{
                    System.out.println("Exiting...");
                    SendDataViaNetwork.sendInt(3, dataOutputStream);
                    releaseResources(socket, dataOutputStream, outputStream, objectOutputStream, dataInputStream, objectInputStream, bufferedReader, printWriter);
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
        SendDataViaNetwork.sendUser(u, dataOutputStream);
        try {
            Doctor doctor = ReceiveDataViaNetwork.receiveDoctor(socket, dataInputStream);
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
        /*if (patient != null) {
            System.out.println("Login successful!");
            ClientMenu.clientMenu(patient);
            // Redirigir a la siguiente parte de la aplicación
        } else {
            System.out.println("Invalid email or password.");
        }*/
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
        Doctor doctor = null;
        User u = null;
        String name = Utilities.readString("Enter your name: ");
        String surname = Utilities.readString("Enter your last name: ");
        LocalDate dob = Utilities.readDate("Enter your date of birth: ");
        System.out.println(dob.toString());
        String email = Utilities.readString("Enter your email: ");
        doctor = new Doctor(name,surname,dob,email);
        String password = Utilities.readString("Enter your password: ");
        u = new User(email, password.getBytes(), role);
        //System.out.println(patient.toString());
        SendDataViaNetwork.sendDoctor(doctor, dataOutputStream);
        SendDataViaNetwork.sendUser(u, dataOutputStream);
        clientDoctorMenu(doctor);

    }

    public static void clientDoctorMenu(Doctor doctor_logedIn) throws IOException {
        Doctor doctor = doctor_logedIn;
        boolean menu = true;
        while(menu){
            switch(printDoctorMenu()){
                case 1:{

                    break;
                }
                case 2:{
                    break;
                }
                case 3:{
                    menu = false;
                    SendDataViaNetwork.sendInt(4, dataOutputStream);
                    System.out.println("Closing server");
                    break;
                }
            }
        }
    }

    private static int bitalinoMenu(){
        System.out.println("\n\nPossible measurements\n"
                + "\n1. EMG"
                + "\n2. EDA"
        );
        return Utilities.readInteger("What do you want to measure?\n");
    }

    private static int printDoctorMenu(){
        System.out.println("\n\nDiagnosis Menu:\n"
                + "\n1. View details of a specific patient"
                + "\n2. Make an interpretation of the results"
                + "\n3. Log out"
        );
        return Utilities.readInteger("What would you want to do?\n");
    }


    private static void releaseResources(Socket socket, DataOutputStream dataOutputStream, OutputStream outputStream, ObjectOutputStream objectOutputStream, DataInputStream dataInputStream, ObjectInputStream objectInputStream, BufferedReader bufferedReader, PrintWriter printWriter){
        try {
            objectOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            dataOutputStream.close();
        } catch (IOException ex) {
            //Logger.getLogger(SendBinaryDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        try {
            outputStream.close();
        } catch (IOException ex) {
            // Logger.getLogger(SendBinaryDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        try {
            objectInputStream.close();
        } catch (IOException ex) {
            //Logger.getLogger(ReceiveClientViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        try {
            dataInputStream.close();
        } catch (IOException ex) {
            //Logger.getLogger(ReceiveBinaryDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        try {
            bufferedReader.close();
        } catch (IOException ex) {
            //Logger.getLogger(ReceiveStringsViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
        printWriter.close();
        try {
            socket.close();
        } catch (IOException ex) {
            //Logger.getLogger(SendBinaryDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
}

