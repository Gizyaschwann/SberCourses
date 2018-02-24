package Lesson3.sockets.hw2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientInterface {
    public static void main(String[] args) {

        Client client = new Client();
        ClientReader reader = new ClientReader();
        ClientWriter writer = new ClientWriter();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        client.startClient("127.0.0.1", 8899);
        System.out.println("Connection to server is started..");

        reader.setInputCommunication(client.socket); // получает от сервера инфу о кол-ве комнат

        System.out.println("To create new room press key N: ");

        while (true){
            try {
                String mes;
                mes = bufferedReader.readLine();
                writer.setOutputCommunication(client.socket);

                if (!mes.isEmpty())
                    writer.write(mes);
                writer.write("MMMMMMMMMMMMm");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
