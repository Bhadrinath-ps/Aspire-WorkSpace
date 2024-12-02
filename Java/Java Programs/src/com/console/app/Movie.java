package com.console.app;

import java.util.*;

class Movie {
    private String name;
    private String showtime;
    private int availableSeats;
    private double ticketPrice;

    // Constructor
    public Movie(String name, String showtime, int availableSeats, double ticketPrice) {
        this.name = name;
        this.showtime = showtime;
        this.availableSeats = availableSeats;
        this.ticketPrice = ticketPrice;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public String getShowtime() {
        return showtime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void reduceAvailableSeats(int seats) {
        this.availableSeats -= seats;
    }
}

class Booking {
    private Movie movie;
    private int tickets;
    private double totalAmount;

    // Constructor for booking
    public Booking(Movie movie, int tickets) {
        this.movie = movie;
        this.tickets = tickets;
        this.totalAmount = tickets * movie.getTicketPrice();
    }

    // Getters
    public Movie getMovie() {
        return movie;
    }

    public int getTickets() {
        return tickets;
    }

    public double getTotalAmount() {
        return totalAmount;
    }
}

class User {
    private String username;
    private String password;

    // Constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

public class MovieTicketBookingSystem {

    private static List<Movie> movies = new ArrayList<>();
    private static List<User> users = new ArrayList<>();
    private static User loggedInUser = null;

    public static void main(String[] args) {
        // Sample movie data
        movies.add(new Movie("Avengers: Endgame", "12:00 PM", 50, 10.0));
        movies.add(new Movie("The Lion King", "3:00 PM", 60, 8.5));
        movies.add(new Movie("Spider-Man: No Way Home", "6:00 PM", 40, 12.0));

        // Sample users with hardcoded credentials
        users.add(new User("user1", "password123"));
        users.add(new User("user2", "password456"));

        Scanner scanner = new Scanner(System.in);

        // User login
        if (login(scanner)) {
            // After login, show movie booking options
            while (true) {
                showMovies(scanner);
            }
        }

        scanner.close();
    }

    // Login method
    private static boolean login(Scanner scanner) {
        System.out.println("Welcome to Movie Ticket Booking System!");
        System.out.print("Enter Username: ");
        String username = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                System.out.println("Login successful! Welcome, " + loggedInUser.getUsername());
                return true;
            }
        }

        System.out.println("Invalid credentials. Please try again.");
        return false;
    }

    // Method to display available movies and handle ticket booking
    private static void showMovies(Scanner scanner) {
        // Display movie list
        System.out.println("\nAvailable Movies:");
        for (int i = 0; i < movies.size(); i++) {
            Movie movie = movies.get(i);
            System.out.println((i + 1) + ". " + movie.getName() + " (" + movie.getShowtime() + ") - Available Seats: " + movie.getAvailableSeats() + " - Price: $" + movie.getTicketPrice());
        }

        // Choose movie
        System.out.print("\nEnter movie number to book or 0 to logout: ");
        int choice = scanner.nextInt();

        if (choice == 0) {
            System.out.println("You have logged out. Goodbye!");
            loggedInUser = null;
            return;
        }

        if (choice < 1 || choice > movies.size()) {
            System.out.println("Invalid choice. Try again.");
            return;
        }

        Movie selectedMovie = movies.get(choice - 1);

        // Check for available seats
        if (selectedMovie.getAvailableSeats() == 0) {
            System.out.println("Sorry, no available seats for this movie.");
            return;
        }

        // Ask for number of tickets
        System.out.print("Enter number of tickets: ");
        int numTickets = scanner.nextInt();

        if (numTickets <= 0 || numTickets > selectedMovie.getAvailableSeats()) {
            System.out.println("Invalid number of tickets. Please enter a valid number.");
            return;
        }

        // Create a booking
        Booking booking = new Booking(selectedMovie, numTickets);
        System.out.println("\nBooking Summary:");
        System.out.println("Movie: " + booking.getMovie().getName());
        System.out.println("Showtime: " + booking.getMovie().getShowtime());
        System.out.println("Tickets: " + booking.getTickets());
        System.out.println("Total amount: $" + booking.getTotalAmount());

        // Reduce available seats
        selectedMovie.reduceAvailableSeats(numTickets);

        // Confirm booking
        System.out.print("\nDo you want to confirm the booking? (yes/no): ");
        String confirm = scanner.next();

        if (confirm.equalsIgnoreCase("yes")) {
            System.out.println("\nBooking confirmed. Enjoy your movie!");
        } else {
            // Revert the seat reduction if user cancels
            selectedMovie.reduceAvailableSeats(-numTickets);
            System.out.println("Booking cancelled.");
        }
    }
}
