package Lesson3.sockets.practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9099);

        Socket socket = serverSocket.accept();

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        String mes = "";
        String mes1;

        while (!"q".equals(mes)){

            mes1 = dataInputStream.readUTF();
            System.out.println(mes1);

            if (scanner.hasNextLine()){
                mes = scanner.nextLine();
                dataOutputStream.writeUTF(mes);
                dataOutputStream.flush();
            }


        }
        dataOutputStream.close();
    }
}
