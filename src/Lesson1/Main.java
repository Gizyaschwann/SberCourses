package Lesson1;

/**
 * Multithreading implementation
 */

public class Main {
    static MyRunnable myRunnable;
    static MyThreadInstance myThreadInstance;

    public static void main(String[] args) {

        // Method 1, way 1
        myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();

        // Method 1, way 2
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from one more thread:P");
            }
        });
        thread1.start();

        // Method 2
        myThreadInstance = new MyThreadInstance();
        myThreadInstance.start();

        // Finishing
        System.out.println("Main thread is finished...");
    }
}
