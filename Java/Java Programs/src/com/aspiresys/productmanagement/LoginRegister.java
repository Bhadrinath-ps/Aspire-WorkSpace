package com.aspiresys.productmanagement;

import java.util.Scanner;

public class LoginRegister {

    private Login login;

    public LoginRegister() {
        login = new Login(); // Initialize the login functionality
    }

    public void showLoginRegisterMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;

        while (!loggedIn) {
            // Display login/register options
        	
            System.out.println("\n-- Welcome to the Product Management System -- \n");
            System.out.println("1. Login");
            System.out.println("2. Register \n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            if (choice == 1) {
                // Attempt to authenticate the user
                if (login.authenticate(scanner)) {
                    System.out.println("\nLogin successful.");
                    loggedIn = true;  // Exit the loop to proceed to product management
                    // Proceed to product management logic after successful login
                } else {
                    System.out.println("\nInvalid username or password. Please try again.");
                }
            } else if (choice == 2) {
                // Register a new user
                login.register(scanner);
            } else {
                System.out.println("\nInvalid option. Please choose either 1 or 2.");
            }
        }

        // After successful login or registration, proceed to product management
        ProductManager productManager = new ProductManager(scanner);
        productManager.showMenu(); // Show the product management menu
    }
}
