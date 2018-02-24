package Lesson3.sockets.hw2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerCommunicator {

    DataInputStream dataInputStream;

    public void setInputCommunication(Socket socket){
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readClientMessage(){
        try {
            return dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
