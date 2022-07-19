package com.javarush.task.task32.task3202;

import java.io.*;

/* 
Читаем
из потока
*/

public class Solution {
	public static void main(String[] args) throws IOException {
		StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
		System.out.println(writer.toString());
	}

	public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
		if(is == null) {
			return new StringWriter();
		}

		BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
		StringWriter writer = new StringWriter();
		while(bufferedInputStream.available() > 0) {
			writer.append((char) bufferedInputStream.read());
		}
		is.close();
		bufferedInputStream.close();


		return writer;
	}
}
