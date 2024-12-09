package ReceiveData;

import Pojos.Doctor;
import Pojos.User;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendDataViaNetwork {

    private  DataOutputStream dataOutputStream;

    public SendDataViaNetwork(Socket socket){
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        }catch (IOException ex){
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sendStrings(String message) throws IOException {
        dataOutputStream.writeUTF(message);


    }
    public void sendInt(Integer message){

        try{
            dataOutputStream.writeInt(message);
        }catch (IOException ex){
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendUser(User u) throws IOException
    {
        dataOutputStream.writeUTF(u.getEmail());
        byte[] password = u.getPassword();

        dataOutputStream.writeUTF(new String(password));
        dataOutputStream.writeUTF(u.getRole().toString());

    }
    public void sendDoctor(Doctor doctor){

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





    public void releaseResources(){
        try {
            dataOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
