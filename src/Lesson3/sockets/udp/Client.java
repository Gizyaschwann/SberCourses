package Lesson3.sockets.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Client {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket = new DatagramSocket();

        DatagramPacket datagramPacket = new DatagramPacket("hello".getBytes(), 0, "hello".getBytes().length);

        datagramPacket.setAddress(InetAddress.getLocalHost());
        datagramPacket.setPort(8099);
        datagramSocket.send(datagramPacket);

        datagramSocket.close();

    }
}
