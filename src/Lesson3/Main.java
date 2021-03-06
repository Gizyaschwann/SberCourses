package Lesson3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.google.com/search?q=stc");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = connection.getResponseCode(); //connection.getContent();

        if(responseCode == 200){
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream())
            );
            String temp;

            while((temp = reader.readLine()) != null){
                System.out.println(temp);
            }

            reader.close();
        }
    }
}
