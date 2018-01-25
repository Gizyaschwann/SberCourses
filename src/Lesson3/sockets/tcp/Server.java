package Lesson3.sockets.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket =
                new ServerSocket(9099);

        Socket socket = serverSocket.accept();

        DataInputStream dataInputStream =
                new DataInputStream(socket.getInputStream());

        String mes;
        while (true){
            mes = dataInputStream.readUTF();
            if("q".equals(mes))
                return;
            else {
                System.out.println(mes);
            }
        }
    }
}
