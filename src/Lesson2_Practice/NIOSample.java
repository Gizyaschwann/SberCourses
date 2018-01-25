package Lesson2_Practice;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Ilyuza on 1/22/2018.
 */
public class NIOSample {
    public static void main(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("1.txt", "rw");

        FileChannel inChannel = randomAccessFile.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(42);

        inChannel.read(buffer);

        int readed = inChannel.read(buffer);

        while (readed != -1){
            buffer.flip();

            while (buffer.hasRemaining()){
                System.out.println((char)buffer.get());
            }

            readed = inChannel.read(buffer);
        }
        randomAccessFile.close();
    }
}
