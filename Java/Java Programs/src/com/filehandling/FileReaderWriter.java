package com.filehandling;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class FileReaderWriter {
	public static void main(String[] args) {
		File file = new File("FileHandling.txt");

		// File Creation
		try {
			if (file.createNewFile()) {
				System.out.println("File Created: " + file.getName());
			} else {
				System.out.println("File Already Exists");
			}

			// File Writing
			try (FileWriter fileWriter = new FileWriter(file)) {
				fileWriter.write(
						"Java FileWriter and FileReader classes are used to write and read data from text files. \n");
				fileWriter.write("Reading and writing take place character by character.");
				System.out.println("Data written to file successfully.");
			} catch (IOException exception) {
				System.out.println("An error occurred while writing to the file.");
				exception.printStackTrace();
			}

		} catch (IOException exception) {
			System.out.println("An error occurred during file creation.");
			exception.printStackTrace();
		}

		// File Reading
		try (FileReader fileReader = new FileReader(file); Scanner scanner = new Scanner(fileReader)) {
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
			}
		} catch (IOException exception) {
			System.out.println("An error occurred while reading from the file.");
			exception.printStackTrace();
		}
	}
}
