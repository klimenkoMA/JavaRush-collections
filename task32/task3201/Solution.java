package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/* 
Запись
в
существующий файл
*/

public class Solution {
	public static void main(String... args) throws FileNotFoundException {
		String fileName = args[0];
		long number = Long.parseLong(args[1]);
		String text = args[2];

		try(RandomAccessFile rf = new RandomAccessFile(fileName, "rw")) {
			long fileLength = rf.length() - number;
			if(fileLength >= text.length()) {
				rf.seek(number);
				rf.write(text.getBytes(StandardCharsets.UTF_8));
			} else {
				rf.seek(rf.length());
				rf.write(text.getBytes(StandardCharsets.UTF_8));
			}
		} catch(Exception e) {
			/**/
		}

	}
}
