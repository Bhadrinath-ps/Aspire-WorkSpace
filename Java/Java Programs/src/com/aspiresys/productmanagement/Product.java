package com.aspiresys.productmanagement;

public class Product {
	
	//Declaration

	private int productId;
	private String productName;
	private double productPrice;
	
	//Constructor Creation
	
	Product(int productId, String productName, double productPrice) {
		this.productId = productId;
		this.productName =  productName;
		this.productPrice = productPrice;
	}

	//	Getter & Setter Method
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public void getProductId(int productId) {
		this.productId = productId;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public double getProductPrice() {
		return productPrice;
	}
	
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String toString() {
		return "Product [ID = " + productId + ", Name = " + productName + ", Price = " + productPrice + "]";
	}
	
}
