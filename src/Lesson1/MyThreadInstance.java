package Lesson1;

/**
 * Created by Ilyuza on 1/16/2018.
 */
public class MyThreadInstance extends Thread{
    @Override
    public void run() {
        System.out.println("Привет из экземпляра потока");
    }
}
