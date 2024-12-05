package ReceiveData;

import Pojos.Doctor;
import Pojos.Patient;
import Pojos.User;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendDataViaNetwork {

    public static void sendStrings(String message, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeUTF(message);
    }
    public static void sendInt(Integer message,  DataOutputStream dataOutputStream) {
        try{
            dataOutputStream.writeInt(message);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public static void sendDoctor(Doctor doctor, DataOutputStream dataOutputStream)
    {
        try {
            dataOutputStream.writeInt(doctor.getDoctor_id());
            dataOutputStream.writeUTF(doctor.getName());
            dataOutputStream.writeUTF(doctor.getSurname());
            dataOutputStream.writeUTF(doctor.getDob().toString());
            dataOutputStream.writeUTF(doctor.getEmail());

        } catch (IOException ex) {
            System.out.println("Unable to write the objects on the server.");
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void sendUser(User u, DataOutputStream dataOutputStream) throws IOException
    {
        dataOutputStream.writeUTF(u.getEmail());
        dataOutputStream.writeUTF(new String(u.getPassword()));
        dataOutputStream.writeUTF(u.getRole().toString());
    }
}
