package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/

public class Solution {
	public static void main(String[] args){
		ByteArrayOutputStream password = getPassword();
		System.out.println(password.toString());
	}

	public static ByteArrayOutputStream getPassword(){
		StringBuilder result = new StringBuilder();
		String resultPassword;
		String lower = ".*[a-z].*";
		String upper = ".*[A-Z].*";
		String digit = ".*\\d.*";

		String lowerCases = "abcdefghijklmnopqrstuvwxyz";
		String upperCases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numbers = "0123456789";

		for(int i = 0; i < 8; i++) {

			int rand = (int) (3 * Math.random());

			switch(rand) {
				case 0:
					rand = (int) (lowerCases.length() * Math.random());
					result.append(lowerCases.charAt(rand));
					break;
				case 1:
					rand = (int) (upperCases.length() * Math.random());
					result.append(upperCases.charAt(rand));
					break;
				case 2:
					rand = (int) (numbers.length() * Math.random());
					result.append(numbers.charAt(rand));
					break;
			}

			String password = result.toString();
			if(!password.matches(lower)) {
				rand = (int) (lowerCases.length() * Math.random());
				result.append(lowerCases.charAt(rand));
				i++;
			} else if(!password.matches(upper)) {
				rand = (int) (upperCases.length() * Math.random());
				result.append(upperCases.charAt(rand));
				i++;
			} else if(!password.matches(digit)) {
				rand = (int) (numbers.length() * Math.random());
				result.append(numbers.charAt(rand));
				i++;
			}

		}

		resultPassword = result.toString();
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream()){
            baos.write(resultPassword.getBytes());
            return baos;
        } catch(IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayOutputStream();
	}
}
