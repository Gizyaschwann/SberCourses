package Lesson3.sockets.hw2;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientWriter implements IWrite {
    DataOutputStream dataOutputStream;

    public void setOutputCommunication(Socket socket){
        try {
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeOutputCommunication(Socket socket){
        try {
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String message) {
        try {
            dataOutputStream.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
