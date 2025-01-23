package com.java.interfacePractice;

public class Main {
	public static void main(String[] args) {
		AtmMachine atm = new Transactions();
		atm.machineOpens();
		atm.machineRuns();
	};
}