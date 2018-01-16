package HW1;

/**
 * Created by Ilyuza on 1/16/2018.
 */
public class Main {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from one more thread:P");
                // Начало чтения и вывод - получается несколько потоков должны иметь общий доступ к одному выводу
                // Обработка файла и изменение консольной информации
                // Просто динамически меняющийся поток слов с кол-вом его вхождений

            }
        });
        thread1.start();

        System.out.println("Halo from main");
    }
}
