package Lesson3.sockets.hw2.final_chat;

import java.util.Scanner;

public class ClientLaunch {

    public static void main(String[] args) {

        String userName = "NO_NAME";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the username: ");
        userName = sc.nextLine();
        Client client = new Client(userName);

        // try to establish communication with server - if there's no communication just stop client program(error is handled in Client class)
        if(!client.start())
            return;

        String mes;
        boolean correct = false;

        while (true){
            mes = sc.nextLine();
            if (mes.equals("N")){
                client.sendMessage(new Message(Message.NEWROOM, ""));
                break;
            }
            else if (mes.charAt(0) > 48 && mes.charAt(0) < 58){
                client.sendMessage(new Message(Message.ROOMS, mes));
                break;
            }
            else {
                System.out.println("Type correct symbol");
            }
        }


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("- Type 'L' to see list of current room users");
        System.out.println("- Type 'Q' to leave chat room");

        while(true) {

            mes = sc.nextLine();

            if (mes.equalsIgnoreCase("L")) {
                client.sendMessage(new Message(Message.USERSLIST, ""));
            }
            else if (mes.equalsIgnoreCase("Q")) {
                client.sendMessage(new Message(Message.QUIT, ""));
                break;
            }
            else {
                client.sendMessage(new Message(Message.MESSAGE, mes));
            }
        }

        sc.close();
        client.disconnect();
    }
}
