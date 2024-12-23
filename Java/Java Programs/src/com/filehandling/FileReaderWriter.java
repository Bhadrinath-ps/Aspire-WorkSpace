package com.filehandling;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderWriter {

	public static void main(String[] args) {

		// Creating a new File
		File file = new File("FileHandling.txt");
		try {
			if (file.createNewFile()) {
				System.out.println("File Created " + file.getName());
			} else {
				System.out.println("File Already Exists");
			}

			// File Writing
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(
					"Java FileWriter and FileReader classes are used to write and read data from text files. \n");
			fileWriter.write("Reading and writing take place character by character.");
			System.out.println("Data written to file successfully.");
			fileWriter.close();

		} catch (IOException exception) {
			exception.printStackTrace();
		}

		try {
			
			// File Reading
			FileReader fileReader = new FileReader(file);
			Scanner scanner = new Scanner(fileReader);
			while (scanner.hasNext()) {
				String data = scanner.nextLine();
				System.out.println(data);
			}
		} catch (IOException exception) {
			System.out.println("An Error has occured.");
			exception.printStackTrace();
		} 
	}

}
