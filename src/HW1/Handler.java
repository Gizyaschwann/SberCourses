package HW1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

/**
 * Created by Ilyuza on 1/18/2018.
 */
public class Handler {

    File files;
    Parser parser;

    Handler(){
        files = new File("src/HW1/resources");
        parser = new Parser();
    }


    public void start(){

//        for(File item : files.listFiles()){
//            System.out.println(item.getName());
//        }

        for(File item : files.listFiles()){
            Thread thread = new Thread(() -> {
//                    System.out.println("Thread " + Thread.currentThread().getName());
                    handleFile(item);

            });
            thread.start();
        }

    }
    private synchronized void handleFile(File item) {
        String line = "";
        try {
            FileReader fileReader =
                    new FileReader(item);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
//                System.out.println(line);
                parser.parse(line);
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

    public void printFinalMap(){
        parser.printMap();
    }

}
