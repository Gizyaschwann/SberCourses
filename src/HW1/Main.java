package HW1;

import java.io.*;
import java.util.HashMap;
import java.util.regex.*;

/**
 * Created by Ilyuza on 1/16/2018.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File files = new File("src/HW1/resources");
        HashMap<String, Integer> dictionary = new HashMap<>();

        for(File item : files.listFiles()){
                System.out.println(item.getName());
        }

            for(File item : files.listFiles()){
                Thread thread = new Thread(new Runnable() {
                    String line = "";

                    @Override
                    public void run() {
                        try {

                            FileReader fileReader =
                                    new FileReader(item);
                            BufferedReader bufferedReader =
                                    new BufferedReader(fileReader);

                            while((line = bufferedReader.readLine()) != null) {
                                System.out.println(line);
                                System.out.println("GGGGGG");
                            }

                            bufferedReader.close();

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }

        System.out.println("Halo from main");

        Pattern regexp = Pattern.compile("a*b*");
        Matcher m = regexp.matcher("aaaaabc");
        boolean b = m.matches();
        System.out.println(b);
    }
}
