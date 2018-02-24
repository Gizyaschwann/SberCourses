package HW1;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class responsible for words processing
 */

public class Parser {

    Map<String, Integer> hashMap; // common data structure for storing words and their occurences
    Pattern regexp; // regular expression to process words

    Parser(){
        hashMap = new HashMap<>();
        regexp = Pattern.compile("[а-яА-ЯёЁ0-9.,()!?:;-]+$"); // allowed symbols - cyrillic, numbers, punctuation marks
    }


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

                                int temp = hashMap.get(item);
                                temp++;
                                hashMap.put(item, temp);
                                System.out.println("Word: " + item + " | кол-во вхождений: "
                                        + temp);
                            } else {
                                hashMap.put(item, 1);
                                System.out.println("Word: " + item + " | кол-во вхождений: 1");
                            }
                        }
                }

            }
    }

}
