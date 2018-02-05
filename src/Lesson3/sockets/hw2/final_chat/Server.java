package Lesson3.sockets.hw2.final_chat;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Server implements IPortAndAddress {

    private static int uniqueId;
    private static ArrayList<ClientThread> clients;
    private static HashMap<Integer, List<Integer>>rooms; // id's of Client threads
    private SimpleDateFormat simpleDateFormat;
    private AtomicBoolean isWorking;

    public Server() {

        simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        clients = new ArrayList<>();
        rooms = new HashMap<>();
        isWorking = new AtomicBoolean();
        uniqueId = 0;
    }

    public void start() {

        isWorking.set(true);
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (isWorking.get()) {
                display("Waiting for new clients.."); // delete or add logging

                Socket socket = serverSocket.accept();
                if (!isWorking.get())
                    break;

                uniqueId++;
                ClientThread t = new ClientThread(socket, uniqueId);
                clients.add(t);
                t.start();
            }

            try {
                serverSocket.close();
                for (ClientThread item : clients) {
                    try {
                        // close all data streams and socket
                        item.socket.close();
                        item.ois.close();
                        item.oos.close();
                    } catch (IOException ioE) {
                    }
                }
            } catch (IOException e) {
                display("Exception in closing server/clients: " + e);
            }

        } catch (IOException e) {
            String mes = simpleDateFormat.format(new Date()) + "ServerSocketException: " + e + "\n";
            //isWorking.set(false);
            display(mes);
        }
    }

    /**
     * Broadcast message into definite room
     * @param mes - message to send
     * @param room - room ro which users message is sent
     */
    private synchronized void broadcast(String mes, int room) {


        String time = simpleDateFormat.format(new Date());
        String broadcastMes = time + " " + mes + "\n";
        System.out.print(broadcastMes);

        int roomClientID = rooms.get(room).size();


        for(int i = roomClientID; --i >= 0;) {
            int id = rooms.get(room).get(i);
            ClientThread ct = clients.get(id-1);

            // Remove disconnected clients
            if(!ct.writeMes(broadcastMes)) {
                clients.remove(id-1);
                rooms.get(room).remove(i);
                display("Disconnected Client " + ct.username + " is removed");
            }
        }


        // Loop in reverse order in order to delete client which has disconnected
        for(int i = clients.size(); --i >= 0;) {
            ClientThread ct = clients.get(i);
            if(!ct.writeMes(broadcastMes)) {
                clients.remove(i);
                display("Disconnected Client " + ct.username + " is removed");
            }
        }

    }

    synchronized void logout(int id, int room) {

        String disconnectedClient = "";
        for (ClientThread item : clients) {
            if(item.id == id) {
                disconnectedClient = item.getUsername();
                clients.remove(item);
                break;
            }
        }
        broadcast(disconnectedClient + " has left the chat room.", room);
    }

    /**
     * Show message in server console
     * @param mes - message to be displayed
     */
    private void display(String mes) {
        String time = simpleDateFormat.format(new Date()) + " " + mes;
        System.out.println(time);
    }

    /**
     * Active client thread on server
     */
    class ClientThread extends Thread {

        int id;
        String username;
        Socket socket;
        ObjectInputStream ois;
        ObjectOutputStream oos;
        //SocketReaderWriter sockerReaderWriter;
        Message message;
        int chatRoom;

        ClientThread(Socket socket, int id) {
            //this.socket = socket;
            this.id = id;
            this.socket = socket;
            chatRoom = 0;
            System.out.println("Create stream");
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois  = new ObjectInputStream(socket.getInputStream());
                username = (String) ois.readObject();
                if (rooms.size() == 0) {
                    oos.writeObject("Number of available rooms for chatting: 0 \nType N to create new room");
                }else {
                    int temp = rooms.size();
                    oos.writeObject("Number of available rooms for chatting: " + temp +
                            "\n" + "- Type N to create new room\nType number from 1 to " + temp+" to join existing room");
                }
                display(username + " has loginned");

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }

        public String getUsername() {
            return username;
        }

        public void run() {

            boolean isRunning = true;
            while(isRunning) {

                try {
                    message = (Message) ois.readObject();
                } catch (IOException e) {
                    display("Connection is reset by client");
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                String mes = message.getMessage();

                switch(message.getType()) {

                    case Message.USERSLIST:
                        try {
                            oos.writeObject("List of the users connected at " + simpleDateFormat.format(new Date()) + "\n");
                            for(int i = 0; i < clients.size(); ++i) {
                                ClientThread ct = clients.get(i);
                                oos.writeObject((i+1) + ") " + ct.username);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;

                    case Message.NEWROOM:
                        chatRoom = rooms.size()+1;
                        rooms.put(rooms.size()+1, new ArrayList<>());
                        rooms.get(chatRoom).add(id);
                        writeMes("Welcome to the room number "+rooms.size());
                        display(username + " created a new room");
                        break;

                    case Message.ROOMS:
                        display(message.getMessage());
                        chatRoom = (int) message.getMessage().charAt(0);
                        rooms.get(chatRoom).add(id);
                        writeMes("Welcome to the room number "+chatRoom);
                        display(username + " joined room "+ chatRoom);
                        break;

                    case Message.MESSAGE:
                        broadcast(username + ": " + mes, chatRoom);
                        break;

                    case Message.QUIT:
                        display(username + " disconnected");
                        isRunning = false;
                        break;

                }
            }
            logout(id, chatRoom);
            close();
        }

        /**
         * Method for closing connection
         */
        private void close() {
            try {
                if (ois != null) ois.close();
                if (oos != null) oos.close();
                if (socket != null) socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
         * Method for sending message to client console
         * @param mes - message
         * @return
         */
        private boolean writeMes(String mes) {

            if(!socket.isConnected()) {
                close();
                return false;
            }

            try {
                oos.writeObject(mes);
            }
            catch(IOException e) {
                display("Error sending message to " + username);
            }
            return true;
        }
    }
}

