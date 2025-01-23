package com.aspiresys.productmanagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Login {

	private String username;
	private String password;
	private Properties properties;

	public Login() {
		properties = new Properties();
		loadCredentials();
	}

	private void loadCredentials() {
		try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
			properties.load(fileInputStream);
			this.username = properties.getProperty("username");
			this.password = properties.getProperty("password");
		} catch (IOException exception) {
			System.out.println("Error loading credentials from properties file: " + exception.getMessage());
		}
	}

	public boolean authenticate(Scanner scanner) {
		System.out.print("\nEnter username: ");
		String inputUsername = scanner.nextLine();	
		System.out.print("Enter password: ");
		String inputPassword = scanner.nextLine();

		return username.equals(inputUsername) && password.equals(inputPassword);
	}

	public void register(Scanner scanner) {
    System.out.print("Enter new username: ");
    String newUsername = scanner.nextLine();

    // Check if the username already exists in the properties
    if (properties.containsKey("username") && properties.getProperty("username").equals(newUsername)) {
        System.out.println("Username already exists. Please choose a different username.");
        return;
    }

    if (newUsername.isEmpty()) {
        System.out.println("Username cannot be empty.");
        return;
    }

    System.out.print("Enter new password: ");
    String newPassword = scanner.nextLine();

    if (newPassword.isEmpty()) {
        System.out.println("Password cannot be empty.");
        return;
    }

    System.out.print("Confirm new password: ");
    String confirmPassword = scanner.nextLine();

    if (!newPassword.equals(confirmPassword)) {
        System.out.println("Passwords do not match. Please try again.");
        return;
    }

    // Store new credentials only if username doesn't exist
    properties.setProperty("username", newUsername);
    properties.setProperty("password", newPassword);

    try (FileOutputStream fileOutputStream = new FileOutputStream("config.properties", true)) {
    	
        // Using append mode to avoid overwriting the existing properties file
        properties.store(fileOutputStream, "User credentials");
        System.out.println("Registration successful. Credentials saved.");
        loadCredentials();
    } catch (IOException exception) {
        System.out.println("Error saving credentials to properties file: " + exception.getMessage());
    }
}
}
