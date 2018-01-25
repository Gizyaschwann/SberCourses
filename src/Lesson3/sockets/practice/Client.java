package Lesson3.sockets.practice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9099);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream= new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        String mes = "";
        String mes1;

        while (!"q".equals(mes)){

            if (scanner.hasNextLine()) {
                mes = scanner.nextLine();
                dataOutputStream.writeUTF(mes);
                dataOutputStream.flush();
            }

            mes1 = dataInputStream.readUTF();
            System.out.println(mes1);
            }

        dataOutputStream.close();

    }
}
