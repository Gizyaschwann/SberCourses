package Lesson3.sockets.hw2.final_chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketReaderWriter {

    Socket socket;
    ObjectInputStream ois;
    ObjectOutputStream oos;

    public SocketReaderWriter(Socket socket) {
        try {
            this.socket = socket;
            System.out.println(socket);
            ois = new ObjectInputStream(socket.getInputStream());
            oos = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("GGG");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object readMessage(){
        Object message = "G";
        try {
            message = ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean writeMessage(String message){
        if(!socket.isConnected()) {
            close();
            return false;
        }

        try {
            oos.writeObject(message);
        }
        catch(IOException e) {
            System.out.println("Error, cannot send message");
        }
        return true;
    }
    public void close(){
        try {
            if (socket!=null) socket.close();
            if(ois != null) ois.close();
            if(oos != null) oos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
