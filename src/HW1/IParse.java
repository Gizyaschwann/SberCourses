package HW1;

import java.util.regex.Pattern;

public interface IParse {

    Pattern regexp = Pattern.compile("[а-яА-ЯёЁ0-9.,()!?:;-]+$"); // regular expression to process words

    void parse(String line);
}
