package com.console.app;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Admin {
    private Connection connection;

    // Constructor to initialize the connection
    public Admin() {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
    }

    // Method to establish a connection to the database
    private static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("config.properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
        }

        String url = properties.getProperty("db.url");
        String username = properties.getProperty("db.username");
        String password = properties.getProperty("db.password");
        
        return DriverManager.getConnection(url, username, password);
    }

    // Method to display the admin menu
    public void showAdminMenu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==================== ADMIN MENU ====================");
            System.out.println("1. Add Movie");
            System.out.println("2. Update Movie");
            System.out.println("3. Delete Movie");
            System.out.println("4. View All Users");
            System.out.println("5. Delete User");
            System.out.println("6. Exit");
            System.out.println("====================================================");

            System.out.print("Enter choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addMovie(scanner);
                    break;
                case 2:
                    updateMovie(scanner);
                    break;
                case 3:
                    deleteMovie(scanner);
                    break;
                case 4:
                    viewAllUsers();
                    break;
                case 5:
                    deleteUser(scanner);
                    break;
                case 6:
                    System.out.println("\n===== Exiting Admin Menu... Thank you! =====");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to add a new movie
    private void addMovie(Scanner scanner) {
        System.out.println("\n==== Add New Movie ====");
        System.out.print("Enter movie name: ");
        String name = scanner.nextLine();
        System.out.print("Enter showtime (format: HH:mm): ");
        String showtime = scanner.nextLine();
        System.out.print("Enter available seats: ");
        int availableSeats = scanner.nextInt();
        System.out.print("Enter ticket price: ₹");
        double ticketPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        String insertMovieQuery = "INSERT INTO movies (name, showtime, available_seats, ticket_price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertMovieQuery)) {
            statement.setString(1, name);
            statement.setString(2, showtime);
            statement.setInt(3, availableSeats);
            statement.setDouble(4, ticketPrice);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("\nMovie added successfully!");
            } else {
                System.out.println("\nError adding movie.");
            }
        } catch (SQLException e) {
            System.err.println("Error adding movie: " + e.getMessage());
        }
    }

    // Method to update an existing movie
    private void updateMovie(Scanner scanner) {
        System.out.println("\n==== Update Movie ====");
        System.out.print("Enter movie ID to update: ");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter new movie name (or leave blank to keep current): ");
        String name = scanner.nextLine();
        System.out.print("Enter new showtime (format: HH:mm) (or leave blank to keep current): ");
        String showtime = scanner.nextLine();
        System.out.print("Enter new available seats (or leave blank to keep current): ");
        String availableSeatsInput = scanner.nextLine();
        System.out.print("Enter new ticket price (or leave blank to keep current): ₹");
        String ticketPriceInput = scanner.nextLine();

        String updateMovieQuery = "UPDATE movies SET ";
        boolean hasUpdates = false;

        if (!name.isEmpty()) {
            updateMovieQuery += "name = ?, ";
            hasUpdates = true;
        }
        if (!showtime.isEmpty()) {
            updateMovieQuery += "showtime = ?, ";
            hasUpdates = true;
        }
        if (!availableSeatsInput.isEmpty()) {
            updateMovieQuery += "available_seats = ?, ";
            hasUpdates = true;
        }
        if (!ticketPriceInput.isEmpty()) {
            updateMovieQuery += "ticket_price = ?, ";
            hasUpdates = true;
        }

        // Remove the last comma and space
        if (hasUpdates) {
            updateMovieQuery = updateMovieQuery.substring(0, updateMovieQuery.length() - 2);
            updateMovieQuery += " WHERE id = ?";
        } else {
            System.out.println("No updates made.");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(updateMovieQuery)) {
            int paramIndex = 1;
            if (!name.isEmpty()) statement.setString(paramIndex++, name);
            if (!showtime.isEmpty()) statement.setString(paramIndex++, showtime);
            if (!availableSeatsInput.isEmpty()) statement.setInt(paramIndex++, Integer.parseInt(availableSeatsInput));
            if (!ticketPriceInput.isEmpty()) statement.setDouble(paramIndex++, Double.parseDouble(ticketPriceInput));
            statement.setInt(paramIndex, movieId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("\nMovie updated successfully!");
            } else {
                System.out.println("\nError updating movie or movie not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating movie: " + e.getMessage());
        }
    }

    // Method to delete a movie
    private void deleteMovie(Scanner scanner) {
        System.out.println("\n==== Delete Movie ====");
        System.out.print("Enter movie ID to delete: ");
        int movieId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        String deleteMovieQuery = "DELETE FROM movies WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteMovieQuery)) {
            statement.setInt(1, movieId);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("\nMovie deleted successfully!");
            } else {
                System.out.println("\nError deleting movie or movie not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting movie: " + e.getMessage());
        }
    }

    // Method to view all users
    private void viewAllUsers() {
        System.out.println("\n==== View All Users ====");
        String query = "SELECT * FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                System.out.println("User: " + username);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing users: " + e.getMessage());
        }
    }

    // Method to delete a user
    private void deleteUser(Scanner scanner) {
        System.out.println("\n==== Delete User ====");
        System.out.print("Enter username to delete: ");
        String username = scanner.nextLine();

        String deleteUserQuery = "DELETE FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteUserQuery)) {
            statement.setString(1, username);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("\nUser deleted successfully!");
            } else {
                System.out.println("\nError deleting user or user not found.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting user: " + e.getMessage());
        }
    }
}
