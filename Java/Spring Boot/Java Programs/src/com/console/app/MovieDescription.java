package com.console.app;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class MovieDescription {
    private static Connection connection;

    // Method to establish a connection to the database
    private static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load database configuration from properties file
                Properties properties = new Properties();
                FileInputStream fileInputStream = new FileInputStream("config.properties");
                properties.load(fileInputStream);
                fileInputStream.close();

                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");

                connection = DriverManager.getConnection(url, username, password);
            } catch (IOException exception) {
                System.err.println("Error loading configuration: " + exception.getMessage());
                throw new SQLException("Failed to load database configuration", exception);
            }
        }
        return connection;
    }

    // Method to show available movies and process ticket bookings
    public void showMovies(Scanner scanner) {
        String query = "SELECT * FROM movies";
        try (Statement statement = getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            List<Movie> movieList = new ArrayList<>();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String showtime = resultSet.getString("showtime");
                int availableSeats = resultSet.getInt("available_seats");
                double ticketPrice = resultSet.getDouble("ticket_price");
                movieList.add(new Movie(id, name, showtime, availableSeats, ticketPrice));
            }

            System.out.println("\nAvailable Movies:");
            for (int i = 0; i < movieList.size(); i++) {
                Movie movie = movieList.get(i);
                System.out.println((i + 1) + ". " + movie.getName() + " (" + movie.getShowtime() + ")");
                System.out.println("Available Seats: " + movie.getAvailableSeats());
                System.out.println("Price: ₹" + movie.getTicketPrice());
                System.out.println();
            }

            // Ask if the user wants to book a ticket after displaying the movie details
            System.out.print("\nWould you like to book a ticket? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                bookTickets(scanner, movieList);
            } else {
                System.out.println("Thank you for checking the movie details!");
            }
        } catch (SQLException exception) {
            System.err.println("Error showing movies or processing booking: " + exception.getMessage());
        }
    }

    // Method to handle ticket booking logic
    private void bookTickets(Scanner scanner, List<Movie> movieList) {
        System.out.print("\nEnter movie number to book or 0 to logout: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); 

        if (choice == 0) {
            System.out.println("You have logged out. Goodbye!");
            return;
        }

        if (choice < 1 || choice > movieList.size()) {
            System.out.println("Invalid choice. Try again.");
            return;
        }

        Movie selectedMovie = movieList.get(choice - 1);

        if (selectedMovie.getAvailableSeats() == 0) {
            System.out.println("Sorry, no available seats for this movie.");
            return;
        }

        System.out.print("Enter number of tickets: ");
        int numberOfTickets = scanner.nextInt();
        scanner.nextLine();

        if (numberOfTickets <= 0 || numberOfTickets > selectedMovie.getAvailableSeats()) {
            System.out.println("Invalid number of tickets. Please enter a valid number.");
            return;
        }

        double totalAmount = numberOfTickets * selectedMovie.getTicketPrice();
        System.out.println("\nBooking Summary:");
        System.out.println("Movie: " + selectedMovie.getName());
        System.out.println("Showtime: " + selectedMovie.getShowtime());
        System.out.println("Tickets: " + numberOfTickets);
        System.out.println("Total amount: ₹" + totalAmount);

        System.out.print("\nDo you want to confirm the booking? (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            // Update available seats in the database
            String updateSeatsQuery = "UPDATE movies SET available_seats = available_seats - ? WHERE id = ?";
            try (PreparedStatement updateStatement = getConnection().prepareStatement(updateSeatsQuery)) {
                updateStatement.setInt(1, numberOfTickets);
                updateStatement.setInt(2, selectedMovie.getId());
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("\nBooking confirmed. Enjoy your movie!");
                } else {
                    System.out.println("\nError confirming the booking.");
                }
            } catch (SQLException exception) {
                System.err.println("Error updating seat availability: " + exception.getMessage());
            }
        } else {
            System.out.println("Booking cancelled.");
        }
    }
}
