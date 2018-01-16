package Lesson1;

/**
 * Created by Ilyuza on 1/16/2018.
 */
public class EggVoice extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                sleep(1000); // приостанавливает поток на 1 секуну

            }catch (InterruptedException e){}

            System.out.println("Egg!");
        }
        // "Egg!" is said 5 times
    }
}
