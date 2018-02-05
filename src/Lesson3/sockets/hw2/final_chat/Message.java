package Lesson3.sockets.hw2.final_chat;

import java.io.*;

public class Message implements Serializable {

    static final int USERSLIST = 0, NEWROOM = 1, ROOMS = 2, MESSAGE = 3, QUIT = 4;
    private int type;
    private String message;

    Message(int type, String message) {
        this.type = type;
        this.message = message;
    }

    int getType() {
        return type;
    }

    String getMessage() {
        return message;
    }
}