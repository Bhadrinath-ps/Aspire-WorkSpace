package com.aspiresys.productmanagement;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProductManager {

	private Map<Integer, Product> products = new HashMap<>();
	private Scanner scanner;

	public ProductManager(Scanner scanner) {
		this.scanner = scanner;
	}

	// Home Menus
	public void showMenu() {

		boolean running = true;
		while (running) {
			System.out.println("\n---------- Product Management System ----------");
			System.out.println("1. Add Product");
			System.out.println("2. Update Product");
			System.out.println("3. Delete Product");
			System.out.println("4. View Products");
			System.out.println("5. Exit");
			System.out.print("\nEnter your choice: ");
			int choice = scanner.nextInt();
			scanner.nextLine();

			switch (choice) {
			case 1:
				addProduct();
				break;
			case 2:
				updateProduct();
				break;
			case 3:
				deleteProduct();
				break;
			case 4:
				viewProducts();
				break;
			case 5:
				System.out.println("Thank You..!");
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	private void addProduct() {
		System.out.print("Enter product ID: ");
		int id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Enter product name: ");
		String name = scanner.nextLine();
		System.out.print("Enter product price: ");
		double price = scanner.nextDouble();

		if (products.containsKey(id)) {
			System.out.println("Product ID already exists.");
		} else {
			Product product = new Product(id, name, price);
			products.put(id, product);
			System.out.println("Product added successfully.");
		}
	}

	private void updateProduct() {
		System.out.print("Enter product ID to update: ");
		int id = scanner.nextInt();
		scanner.nextLine();

		if (products.containsKey(id)) {
			Product product = products.get(id);
			System.out.print("Enter new product name: ");
			String name = scanner.nextLine();
			System.out.print("Enter new product price: ");
			double price = scanner.nextDouble();

			product.setProductName(name);
			product.setProductPrice(price);
			System.out.println("Product updated successfully.");
		} else {
			System.out.println("Product not found.");
		}
	}

	private void deleteProduct() {
		System.out.print("Enter product ID to delete: ");
		int id = scanner.nextInt();

		if (products.containsKey(id)) {
			products.remove(id);
			System.out.println("Product deleted successfully.");
		} else {
			System.out.println("Product not found.");
		}
	}

	private void viewProducts() {
		if (products.isEmpty()) {
			System.out.println("No products available.");
		} else {
			for (Product product : products.values()) {
				System.out.println(product);
			}
		}
	}
}
