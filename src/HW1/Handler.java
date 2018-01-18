package HW1;

import java.io.*;
import java.util.HashMap;

/**
 * Created by Ilyuza on 1/18/2018.
 */
public class Handler {

    public void start(){

        File files = new File("src/HW1/resources");
        HashMap<String, Integer> dictionary = new HashMap<>();

        for(File item : files.listFiles()){
            System.out.println(item.getName());
        }

        String b = "аааа, (бб)!";
        Parser parser = new Parser();
        parser.parse(b);


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
                            String[] words = parser.parse(line);
                            for (int i = 0; i < words.length; i++) {
                                if (dictionary.containsKey(words[i])){
                                    int temp = dictionary.get(words[i]);
                                    dictionary.put(words[i], temp++);
                                }
                                else {
                                    dictionary.put(words[i], 1);
                                }
                            }
                        }

                        bufferedReader.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e){
                        System.out.println("There's an illegal argument in string");
                        // Stop all threads and terminate wwwwwwwwwww
                    }
                }
            });
            thread.start();
//            System.out.println("START____________");
//            for (String key: dictionary.keySet()){
//                String value = dictionary.get(key).toString();
//                System.out.println(key + " " + value);
//
//
//            }
        }
    }

}
