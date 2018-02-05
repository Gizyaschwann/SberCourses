package Lesson3.sockets.hw2.final_chat;


import java.net.*;
import java.io.*;


public class Client implements IPortAndAddress  {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private String username;

    public Client(String username){
        this.username = username;
    }

    /**
     * Start chart
     * @return false if there're some failures(e.g. couldn't connect to server)
     */
    public boolean start() {

        try {
            socket = new Socket("", PORT);
        } catch (IOException e) {
            System.out.println("Couldn't connect to the server, server is unavailable. \nPlease, repeat your attempt and launch program one more time.");
            return false;
        }

        try
        {
            ois  = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            System.out.println("Exception in creating input/output streams");
            e.printStackTrace();
        }

        new ServerListener().start();

        try
        {
            oos.writeObject(username);
        }
        catch (IOException e) {
            System.out.println("Exception while loginning");
            e.printStackTrace();
            disconnect();
            return false;
        }

        return true;
    }


    /**
     * Send message to server
     * @param msg
     */
    void sendMessage(Message msg) {
        try {
            oos.writeObject(msg);
        }
        catch(IOException e) {
            System.out.println("Couldn't send message to server. Please, relaunch the chat.");
        }
    }


    /**
     * Close all streams and socket
     */
    public void disconnect() {
        try {
            if (ois != null) ois.close();
            if (oos != null) oos.close();
            if (socket != null) socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    class ServerListener extends Thread {

        public void run() {
            while(true) {
                try {
                    String mes = (String) ois.readObject();
                    System.out.println(mes);
                    System.out.print("> ");
                }
                catch(IOException e) {
                    System.out.println("!!! Connection is closed by the server. Please, relaunch the chat !!!");
                    break;
                }
                catch(ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}