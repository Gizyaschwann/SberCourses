package Lesson3.sockets.hw2;

public class ServerInterface {
    public static void main(String[] args) {

        Server server = new Server();
        server.startServer(8899);

        ServerCommunicator reader = new ServerCommunicator();
        reader.setInputCommunication(server.clientSocket);

        ServerOutputCommunicator printer = new ServerOutputCommunicator();
        printer.setOutputCommunication(server.clientSocket);

        printer.write("Number of available rooms: " + server.getRoomsNumber());

    }
}
