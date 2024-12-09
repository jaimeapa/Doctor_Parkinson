package ReceiveData;

import Pojos.Doctor;
import Pojos.Interpretation;
import Pojos.Patient;
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
        }catch (IOException e){
            e.printStackTrace();
    }
    }
    public void sendStrings(String message) throws IOException {
        dataOutputStream.writeUTF(message);


    }
    public void sendInt(Integer message) throws IOException{

        try{
            dataOutputStream.writeInt(message);
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }


    public void sendPatient(Patient patient)
    {
        try {

            dataOutputStream.writeInt(patient.getPatient_id());
            dataOutputStream.writeUTF(patient.getName());
            dataOutputStream.writeUTF(patient.getSurname());
            dataOutputStream.writeUTF(patient.getDob().toString());
            dataOutputStream.writeUTF(patient.getEmail());

        } catch (IOException ex) {
            System.out.println("Unable to write the objects on the server.");
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendInterpretation(Interpretation interpretation) throws IOException{

        dataOutputStream.writeUTF(interpretation.getDate().toString());
        dataOutputStream.writeInt(interpretation.getDoctor_id());
        dataOutputStream.writeUTF(interpretation.getSignalEMG().valuesToString());
        dataOutputStream.writeInt(interpretation.getPatient_id());
        dataOutputStream.writeUTF(interpretation.getSignalEDA().valuesToString());
        dataOutputStream.writeUTF(interpretation.getObservation());
        dataOutputStream.writeUTF(interpretation.getInterpretation());
    }
    public void sendUser(User u) throws IOException
    {
        dataOutputStream.writeUTF(u.getEmail());
        byte[] password = u.getPassword();

        dataOutputStream.writeUTF(new String(password));
        dataOutputStream.writeUTF(u.getRole().toString());

    }
    public void sendDoctor(Doctor doctor) throws IOException {

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
            ex.printStackTrace();
        }
    }

}
