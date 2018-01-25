package Lesson3.sockets.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9099);

        DataOutputStream dataOutputStream =
                new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);
        String mes = "";

        while (!"q".equals(mes)){
            mes = scanner.nextLine();
            dataOutputStream.writeUTF(mes);
            dataOutputStream.flush();
        }

        dataOutputStream.close();
    }
}
