package com.javarush.task.task32.task3210;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* 
Используем RandomAccessFile
*/

public class Solution {
    public static void main(String... args) throws FileNotFoundException {

        long number = Long.parseLong(args[1]);
        String text = args[2];

        try(RandomAccessFile raf = new RandomAccessFile(args[0], "rw")){

            int textLength = text.length();
            raf.seek(number);
            byte[] readByte = new byte[textLength];
            raf.read(readByte, 0, textLength);
            String readingText = new String(readByte, StandardCharsets.UTF_8);
            raf.seek(raf.length());
            if(text.equals(readingText)){
                raf.write("true".getBytes());
            }else{
                raf.write("false".getBytes(StandardCharsets.UTF_8));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
