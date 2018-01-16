package Lesson1;

/**
 * Created by Ilyuza on 1/16/2018.
 */
public class ChickenVoice {
    static EggVoice eggVoice; // Побочный поток

    public static void main(String[] args) {
        eggVoice = new EggVoice();
        System.out.println("The battle is begun ---");
        eggVoice.start();

        for(int i = 0; i < 5; i++)
        {
            try{
                Thread.sleep(1000);		//Приостанавливает поток на 1 секунду
            } catch(InterruptedException e){}

            System.out.println("Chicken!");
        }
        // "Chicken!" is said 5 times

        if (eggVoice.isAlive()){// Если противник ещё не сказал последнее слово
            try {
                eggVoice.join();
            } catch (InterruptedException e){}

            System.out.println("Egg wins!");
        }
        else {
            System.out.println("Chicken wins!");
        }

        System.out.println("The battle is finished ---");
    }
}
