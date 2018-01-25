package Lesson3.sockets.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(8089);

        byte[] bytes = new byte[1024];

        DatagramPacket datagramPacket = new DatagramPacket(bytes, 1024);

        String mes;
        while (true){
            ds.receive(datagramPacket);
            mes = String.valueOf(datagramPacket.getData());
            System.out.println(mes);
            if ("q".equals(mes)) // благодаря такому порядку, мы не словим NullPointerException
                return;
        }
    }
}
