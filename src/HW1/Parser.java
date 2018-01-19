package HW1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for words processing
 */

public class Parser {

    Map<String, AtomicInteger> hashMap; // common data structure for storing words and their occurences
    Pattern regexp; // regular expression to process words
//    ReentrantLock lock;

    Parser(){
        hashMap = new ConcurrentHashMap<>();
        regexp = Pattern.compile("[а-яА-ЯёЁ0-9.,()!?:;-]+$"); // allowed symbols - cyrillic, numbers, punctuation marks
//        lock = new ReentrantLock();
    }


    public void parse(String line) throws IllegalArgumentException{

        String[] parts = line.split("\\s");

        try {
            for (String item : parts) {
                Matcher m = regexp.matcher(item);
                if (!m.matches()) {
                    throw new IllegalArgumentException(item);
                } else {
                    item = item.replaceAll("[0-9.,()!?:;-]|\\n|\\s|\\t|\\r", "").toLowerCase();

//                    lock.lock();
                    try {
                        synchronized (hashMap) {

                            if (hashMap.containsKey(item)) {
                                AtomicInteger temp = hashMap.get(item);
                                temp.addAndGet(1);
                                hashMap.put(item, temp);
                                System.out.println("Word: " + item + " | кол-во вхождений: "
                                        + temp);
                            } else {
                                hashMap.putIfAbsent(item, new AtomicInteger(1));
                                System.out.println("Word: " + item + " | кол-во вхождений: 1");
                            }
                        }
                    } finally {
//                        lock.unlock();
                    }
                }

            }

        }
        catch (IllegalArgumentException e){
            if (!e.getMessage().equals("")){ // avoid blanks in case they are not delimeters
                System.out.println("There's following illegal word in text: " + e.getMessage());
            }
        }

    }

//    public void printMap(){
//
//        System.out.println("START PRINTING -------");
//        for (Map.Entry entry : hashMap.entrySet()) {
//            System.out.println("Слово: " + entry.getKey() + ", кол-во вхождений: "
//                    + entry.getValue());
//        }
//        System.out.println("Finish PRINTING -------");
//    }
}
