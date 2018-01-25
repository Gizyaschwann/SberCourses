package Lesson3;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ilyuza on 1/25/2018.
 */
public class Post {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.google.com/search?q=stc");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
        dataOutputStream.writeUTF("param=jdksdhksdh");
        dataOutputStream.flush();
        dataOutputStream.close();

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
