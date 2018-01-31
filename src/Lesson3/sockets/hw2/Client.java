package Lesson3.sockets.hw2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {

    Socket socket;
    DataInputStream dataInputStream;

    public void startClient(String address, int port){
        try {
            socket = new Socket(address, port);
            dataInputStream = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
