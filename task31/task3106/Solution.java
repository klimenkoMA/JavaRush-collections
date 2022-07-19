package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* 
Разархивируем файл
*/

public class Solution {
	public static void main(String[] args) throws IOException {

		File destFile = new File(args[0]); // Take result file

		if(!destFile.exists()) { // if file is not exists create it
			destFile.createNewFile();
		}

		List<FileInputStream> fileInputStreamList = new ArrayList<>(); // List of file parts

		List<String> fileNames = new ArrayList<>(); // List of file names
		fileNames.addAll(Arrays.asList(args).subList(1, args.length)); // add file names to list
		Collections.sort(fileNames);// sort list from 1 to 10

		for(String name : fileNames //Создаем входящий стрим для каждого куска архива
		) {
			fileInputStreamList.add(new FileInputStream(name));
		}

		try(ZipInputStream zis = new ZipInputStream(new SequenceInputStream( //Входящий стрим общего архива
				Collections.enumeration(fileInputStreamList)))){

            while(true){
                ZipEntry entry = zis.getNextEntry();

                if(entry == null){
                    break;
                }

                try(OutputStream os = new BufferedOutputStream(new FileOutputStream(destFile))) {

                    final int bufferSize = 1024;
                    byte[] buffer = new byte[bufferSize];

                    for(int readBytes; (readBytes = zis.read(buffer, 0, bufferSize)) > -1;){
                        os.write(buffer, 0, readBytes);
                    }

                    os.flush();

                }
            }
        }

	}
}
