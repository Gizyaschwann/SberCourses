package HW1;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Ilyuza on 1/18/2018.
 */
public class Parser {

    public String[] parse(String line) throws IllegalArgumentException{
        String[] parts = line.split("\\s");
        Pattern regexp = Pattern.compile("[а-яА-ЯёЁ0-9.,()!?:;-]+$"); // допустимые символы - кириллица, цифры, знаки препинания
        try {

            for (String item : parts) {
                Matcher m = regexp.matcher(item);
                if (!m.matches()) {
                    throw new IllegalArgumentException();
                } else {
                    item = item.replaceAll("[.,()!?:;-]", "").toLowerCase();

                    System.out.println(item);
                }

            }
        }
        catch (IllegalArgumentException e){
            System.out.println("There's an illegal argument in string");
        }
        return parts;

    }
}
