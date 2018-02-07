package HW1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for words processing
 */

public class Parser implements IParse {

    Map<String, AtomicInteger> hashMap; // common data structure for storing words and their occurences
    Parser(){
        hashMap = new ConcurrentHashMap<>();
    }


    @Override
    public void parse(String line) throws IllegalArgumentException{

        String[] parts = line.split("\\s");

        for (String item : parts) {
            Matcher m = regexp.matcher(item);
            if (!m.matches()) {
                throw new IllegalArgumentException(item);
            } else {
                item = item.replaceAll("[0-9.,()!?:;-]|\\n|\\s|\\t|\\r", "").toLowerCase();

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
            }

        }
    }

}