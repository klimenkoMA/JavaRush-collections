package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.util.*;

/* 
Находим
 все файлы
*/

public class Solution {
	public static List<String> getFileTree(String root) throws IOException {

		List<String> listFiles = new ArrayList<>();
		File file = new File(root);
		Queue<File> queue = new PriorityQueue<>();

		queue.addAll(Arrays.asList(file.listFiles()));

		while(!queue.isEmpty()
		) {
			File f = queue.poll();
			if(f.isDirectory()) {
				queue.addAll(Arrays.asList(f.listFiles()));
			} else {
				listFiles.add(f.getAbsolutePath());
			}
		}
		return listFiles;
	}

	public static void main(String[] args) {

	}
}
