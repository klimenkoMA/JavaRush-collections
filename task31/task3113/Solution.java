package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/* 
Что
внутри
папки?
*/

public class Solution {

	public static void main(String[] args) throws IOException {


		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			Path dir = Paths.get(reader.readLine());

			if(!Files.isDirectory(dir)) {
				System.out.println(dir.toAbsolutePath() +
						" - не папка");
			} else {
				final int[] dirCount = {0};
				final int[] filesCount = {0};
				final long[] dirSize = {0};

				SimpleFileVisitor<Path> fileVisitor = new SimpleFileVisitor<Path>() {

					@Override
					public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
						if(attrs.isDirectory()) {
							dirCount[0]++;
						}
						return FileVisitResult.CONTINUE;
					}

					@Override
					public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

						 if(attrs.isRegularFile()) {
							filesCount[0]++;
							dirSize[0] += attrs.size();
						}
						return FileVisitResult.CONTINUE;
					}
				};

				Files.walkFileTree(dir, fileVisitor);

				System.out.println("Всего папок - " + (dirCount[0] - 1));
				System.out.println("Всего файлов - " + filesCount[0]);
				System.out.println("Общий размер - " + dirSize[0]);

			}
		} catch(Exception e) {
			/**/
		}
	}
}
