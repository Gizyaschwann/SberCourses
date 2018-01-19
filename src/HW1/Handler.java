package HW1;

import java.io.*;


/**
 * Created by Ilyuza on 1/18/2018.
 */
public class Handler {

    private File files;
    private Parser parser;

    Handler(){
        files = new File("src/HW1/resources");
        parser = new Parser();
    }


    public void start(){

        for(File item : files.listFiles()){
            Thread thread = new Thread(() -> {
                    handleFile(item);

            });
            thread.start();
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
        } catch (IllegalArgumentException e){
            System.out.println("There's an illegal argument in string");
        }
    }

}
