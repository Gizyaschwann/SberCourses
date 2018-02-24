package Lesson3.sockets.hw2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReader {
    DataInputStream dataInputStream;

    public void setInputCommunication(Socket socket){
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println(dataInputStream.readUTF().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void requestRoomsNumber(){

    }
}
