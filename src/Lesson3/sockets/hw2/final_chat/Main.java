package Lesson3.sockets.hw2.final_chat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] args) throws InterruptedException, ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
        String date = simpleDateFormat.format(new Date());
        System.out.println(date);
        Thread.sleep(2000);
        date = simpleDateFormat.format(new Date());
        System.out.println(date);
    }
}
