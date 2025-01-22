package com.aspiresys;

class Task extends Thread {
	private int dividend;
	private int divisor;

	public Task(int dividend, int divisor) {
		this.dividend = dividend;
		this.divisor = divisor;
	}

	@Override
	public void run() {
		try {
			System.out.println("Task started by " + Thread.currentThread().getName());
			Thread.sleep(3000);
			int result = dividend / divisor;
			System.out.println("Result : " + result);
		} catch (ArithmeticException e) {
			System.out.println("Cannot divide by zero! " + e.getMessage());
		} catch (Exception e) {
			System.out.println("An error occurred in thread: " + e.getMessage());
		} finally {
			System.out.println("Task finished by " + Thread.currentThread().getName());
		}
	}
}

public class ThreadWithExceptionHandling {
	public static void main(String[] args) {
		Task task1 = new Task(10, 2);
		Task task2 = new Task(10, 0);
		Task task3 = new Task(20, 4);

		task1.start();
		task2.start();
		task3.start();
	}
}
