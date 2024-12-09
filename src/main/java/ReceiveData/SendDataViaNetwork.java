package ReceiveData;

import Pojos.Doctor;
import Pojos.User;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles sending data over a network connection using a socket.
 * Supports sending various types of data, including strings, integers, and objects like
 * {@link User} and {@link Doctor}.
 */
public class SendDataViaNetwork {

    private DataOutputStream dataOutputStream;

    /**
     * Constructs a new SendDataViaNetwork instance using the provided socket.
     * Initializes the data output stream for sending data.
     *
     * @param socket the socket used to establish the network connection.
     */
    public SendDataViaNetwork(Socket socket) {
        try {
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sends a string message over the network.
     *
     * @param message the string message to send.
     * @throws IOException if an error occurs while sending the data.
     */
    public void sendStrings(String message) throws IOException {
        dataOutputStream.writeUTF(message);
    }

    /**
     * Sends an integer over the network.
     *
     * @param message the integer to send.
     */
    public void sendInt(Integer message) {
        try {
            dataOutputStream.writeInt(message);
        } catch (IOException ex) {
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Sends a {@link User} object over the network.
     *
     * @param u the {@link User} object to send.
     * @throws IOException if an error occurs while sending the data.
     */
    public void sendUser(User u) throws IOException {
        dataOutputStream.writeUTF(u.getEmail());
        byte[] password = u.getPassword();
        dataOutputStream.writeUTF(new String(password));
        dataOutputStream.writeUTF(u.getRole().toString());
    }

    /**
     * Sends a {@link Doctor} object over the network.
     *
     * @param doctor the {@link Doctor} object to send.
     */
    public void sendDoctor(Doctor doctor) {
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

    /**
     * Releases resources by closing the data output stream.
     */
    public void releaseResources() {
        try {
            dataOutputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(SendDataViaNetwork.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

