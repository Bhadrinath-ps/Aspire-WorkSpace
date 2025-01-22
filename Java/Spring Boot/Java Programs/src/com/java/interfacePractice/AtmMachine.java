package com.java.interfacePractice;

interface AtmMachine {
	void machineOpens();

	default void machineRuns() {
		System.out.println("Machine is running");
	}
}
