package com.console.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class User {
    private static User loggedInUser = null;
    private static Admin loggedInAdmin = null; // To track admin login
    private String username;
    private String password;
    private Connection connection;

    // Method to connect to the database
    public void connectToDatabase() {
        try {
            // Load database configuration from properties file
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            String url = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            connection = DriverManager.getConnection(url, dbUsername, dbPassword);
            System.out.println("Connected to the database successfully.");
        } catch (IOException | SQLException exception) {
            System.err.println("Database connection failed: " + exception.getMessage());
            exception.printStackTrace();
        }
    }

    // Method to disconnect from the database
    public void disconnectFromDatabase() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Disconnected from the database successfully.");
            }
        } catch (SQLException exception) {
            System.err.println("Failed to disconnect from the database: " + exception.getMessage());
        }
    }

    // Method to start login or registration process
    public void startLoginOrRegistrationProcess(MovieDescription movieDescription) {
        Scanner scanner = new Scanner(System.in);

        // Display options for login or registration (user/admin selection)
        System.out.println("\n================= Welcome to the Movie Ticket Booking System =================");
        System.out.println("1. User Login/Register");
        System.out.println("2. Admin Login");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        if (option == 1) {
            // Handle user login/registration
            System.out.println("\n==== User Login or Registration ====");
            handleUserLoginOrRegistration(scanner, movieDescription);
        } else if (option == 2) {
            // Handle admin login
            System.out.println("\n==== Admin Login ====");
            handleAdminLogin(scanner);
        } else {
            System.out.println("Invalid option. Exiting the system.");
        }
    }

    // Method to handle user login or registration
    private void handleUserLoginOrRegistration(Scanner scanner, MovieDescription movieDescription) {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.print("Choose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume the newline left-over

        if (option == 1) {
            if (register(scanner)) {
                System.out.println("\nRegistration successful! You can now log in.");
            } else {
                System.out.println("Registration failed. Please try again.");
            }
        } else if (option == 2) {
            if (login(scanner)) {
                System.out.println("\nLogin successful!");
                System.out.println("Welcome, " + loggedInUser.getUsername());
                movieDescription.showMovies(scanner); // Show movies after successful login
            } else {
                System.out.println("Invalid login. Please try again.");
            }
        } else {
            System.out.println("Invalid option. Exiting the system.");
        }
    }

    // Method to handle admin login
    private void handleAdminLogin(Scanner scanner) {
        System.out.print("Enter Admin Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Admin Password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loggedInAdmin = new Admin(); // Assuming you have the Admin class defined
                System.out.println("\nAdmin login successful!");
                loggedInAdmin.showAdminMenu(); // Show admin menu after successful login
            } else {
                System.out.println("Invalid admin login. Please try again.");
            }
        } catch (SQLException exception) {
            System.err.println("Login failed: " + exception.getMessage());
        }
    }

    // Method to handle user registration
    private boolean register(Scanner scanner) {
        System.out.print("Enter a username: ");
        String username = scanner.nextLine();
        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        String checkUserQuery = "SELECT * FROM users WHERE username = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkUserQuery)) {
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Username already exists. Please choose a different username.");
                return false;
            }
        } catch (SQLException exception) {
            System.err.println("Error checking username availability: " + exception.getMessage());
            return false;
        }

        String insertUserQuery = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertUserQuery)) {
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            int rowsInserted = insertStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException exception) {
            System.err.println("Error registering user: " + exception.getMessage());
            return false;
        }
    }

    // Method to handle user login
    private boolean login(Scanner scanner) {
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loggedInUser = new User();
                loggedInUser.username = username;
                loggedInUser.password = password;
                return true;
            }
        } catch (SQLException exception) {
            System.err.println("❌ Login failed: " + exception.getMessage());
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static Admin getLoggedInAdmin() {
        return loggedInAdmin;
    }
}
