package HW1;

import java.io.*;

/**
 * Class responsible for files processing & threads launch
 */

public class Handler {

    private File files;
    private Parser parser;

    Handler(){
        files = new File("src/HW1/resources");
        parser = new Parser();
    }


    public void start(){

        try {
            for(File item : files.listFiles()){
                Thread thread = new Thread(() -> {
                    handleFile(item);

                });
                thread.start();
            }
        } catch (NullPointerException e){
            System.out.println("Incorrect path to directory with files, please check it one more time.");
        }


    }
    private void handleFile(File item) {
        String line = "";
        try {
            FileReader fileReader =
                    new FileReader(item);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {

                parser.parse(line);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
