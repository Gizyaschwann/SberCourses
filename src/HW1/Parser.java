package HW1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ilyuza on 1/18/2018.
 */
public class Parser {

    Map<String, AtomicInteger> hashMap;
    Pattern regexp; // допустимые символы - кириллица, цифры, знаки препинания
    ReentrantLock lock;

    Parser(){
        hashMap = new ConcurrentHashMap<>();
        regexp = Pattern.compile("[а-яА-ЯёЁ0-9.,()!?:;-]+$");
        lock = new ReentrantLock();
    }


    public synchronized void parse(String line) throws IllegalArgumentException{

        String[] parts = line.split("\\s");

        try {
            for (String item : parts) {
                Matcher m = regexp.matcher(item);
                if (!m.matches()) {
                    throw new IllegalArgumentException();
                } else {
                    item = item.replaceAll("[.,()!?:;-]", "").toLowerCase();

                    lock.lock();
                    try {
                        synchronized (hashMap) {

                            if (hashMap.containsKey(item)) {
                                AtomicInteger temp = hashMap.get(item);

//                                System.out.println(temp + "-----------");
                                temp.addAndGet(1);
                                hashMap.put(item, temp);
//                                System.out.println(temp + "-----------");
                            } else {
                                hashMap.putIfAbsent(item, new AtomicInteger(1));
                            }
                        }
                    } finally {
                        lock.unlock();
                    }
//                    System.out.println(item);
                }

            }

        }
        catch (IllegalArgumentException e){
            System.out.println("There's an illegal argument in string");

        }
        finally {
            for (Map.Entry entry : hashMap.entrySet()) {
                System.out.println("Key: " + entry.getKey() + " Value: "
                        + entry.getValue());
            }
        }

    }
    public void printMap(){

        System.out.println("START PRINTING -------");
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("Слово: " + entry.getKey() + ", кол-во вхождений: "
                    + entry.getValue());
        }
        System.out.println("Finish PRINTING -------");
    }
}
