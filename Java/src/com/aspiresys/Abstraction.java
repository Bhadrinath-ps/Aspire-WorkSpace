package com.aspiresys;

abstract class employee {
	abstract void task();
}

class softwareDeveloper extends employee {
	void task() {
		System.out.println("Task was assigned for you");
	}
}

public class Abstraction {
	public static void main(String[] args) {
		softwareDeveloper sd = new softwareDeveloper();
		sd.task();
	}
}
