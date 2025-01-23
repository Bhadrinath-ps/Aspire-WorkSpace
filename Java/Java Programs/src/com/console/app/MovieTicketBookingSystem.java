package com.console.app;

import java.util.Scanner;

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        MovieDescription movieDescription = new MovieDescription();

        user.connectToDatabase(); // Establish the database connection
        user.startLoginOrRegistrationProcess(movieDescription); // Start login/registration or admin login
        user.disconnectFromDatabase(); // Disconnect from the database
        scanner.close();
    }
}
