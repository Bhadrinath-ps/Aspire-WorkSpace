/*	
 *	Author Name 		: Bhadrinath P S
 *	Project Title 		: Movie Ticket Booking System
 *	Version 			: 0.3
 *	Created On			: 28-11-2024
 *	Modified On 		: 3-12-2024
 *	Reviewed By 		:
 */

package com.console.app;

import java.util.Scanner;

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        MovieDescription movieDescription = new MovieDescription();

        user.connectToDatabase(); // Establish the database connection
        user.startLoginOrRegistrationProcess(movieDescription);
        user.disconnectFromDatabase(); // Disconnect from the database
        scanner.close();
    }
}
