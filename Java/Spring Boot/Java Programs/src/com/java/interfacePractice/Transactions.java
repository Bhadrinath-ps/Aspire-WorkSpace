package com.java.interfacePractice;

public class Transactions implements AtmMachine{
	@Override
	public void machineOpens() {
		System.out.println("Machine Opens");
		
	}
}