package Lesson3.sockets.hw2;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server{

    ServerSocket serverSocket;
    Socket clientSocket;
    SocketAddress socketAddress;
    int roomsNumber;
    Map<Integer, ArrayList<Client>> hashmap;
    ArrayList<Client> clients;



    public Server() {
        this.roomsNumber = 0;
        this.hashmap = new ConcurrentHashMap<>();
        this.clients = new ArrayList<>();
    }

    public int getRoomsNumber() {
        return roomsNumber;
    }

    public void startServer(int port){
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            System.out.println("Connection is established");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
