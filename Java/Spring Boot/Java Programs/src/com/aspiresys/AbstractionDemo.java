package com.aspiresys;

abstract class Employee {
	abstract void task();
}

class SoftwareDeveloper extends Employee {
	void task() {
		System.out.println("Task was assigned for you");
	}
}

public class AbstractionDemo {
	public static void main(String[] args) {
		SoftwareDeveloper softwareDeveloper = new SoftwareDeveloper();
		softwareDeveloper.task();
	}
}
