package com.javarush.task.task32.task3213;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/* 
Шифр
Цезаря
*/

public class Solution {
	public static void main(String[] args) throws IOException {
		StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
		System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
	}

	public static String decode(StringReader reader, int key) throws IOException {

		String decodeString = "";

		if(reader == null) {
			return decodeString;
		}
		String codeString;
		StringWriter writer = new StringWriter();
		BufferedReader bufferedReader = new BufferedReader(reader);
		while((codeString = bufferedReader.readLine())!=null){
			char[] codeChars = codeString.toCharArray();
			for(char codeChar : codeChars) {
				codeChar += key;
				writer.append(codeChar);
			}
		}


		decodeString = writer.toString();
		bufferedReader.close();

		return decodeString;
	}
}
