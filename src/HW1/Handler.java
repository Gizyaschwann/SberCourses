package HW1;

import java.io.*;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

/**
 * Class responsible for files processing & threads launch
 */

public class Handler implements IHandle{

    private File files;
    private Parser parser;
    private AtomicBoolean atomicBoolean;
    private IParse parserProxy;

    Handler(){
        files = new File("src/HW1/resources");
        parser = new Parser();
        atomicBoolean = new AtomicBoolean(true);
        parserProxy = (IParse) Proxy.newProxyInstance(Parser.class.getClassLoader(), Parser.class.getInterfaces(),
                new MyInvocationHandler(parser));
    }

    @Override
    public void start(){

        try {

            for(File item : files.listFiles()){
                Thread thread = new Thread(() -> {
                    if (atomicBoolean.get() != false) {
                        handleFile(item);
                    }

                });
                thread.start();
            }
        } catch (NullPointerException e){
            System.out.println("Incorrect path to directory with files, please check it one more time.");
        }


    }

    @Override
    public void handleFile(File item) {
        String line = "";
        try {
            FileReader fileReader =
                    new FileReader(item);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                parserProxy.parse(line);
            }
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            stopThreads();
            try {
                sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            System.out.println("----------------------------------------------------------\n" +
                    "I've stopped all threads, because I found illegal symbol" +
                    "\n----------------------------------------------------------");
        }
    }

    @Override
    public void stopThreads(){
        atomicBoolean.set(false);
    }

}