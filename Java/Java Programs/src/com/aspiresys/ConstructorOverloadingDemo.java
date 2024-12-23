package com.aspiresys;

class TaskStatus {
	int taskId;
	String taskName;
	String taskDescription;

	TaskStatus() {
		this.taskId = 0;
		this.taskName = "empty";
		this.taskDescription = "empty";
	}

	TaskStatus(int taskId) {
		this.taskId = taskId;
		this.taskName = "empty";
		this.taskDescription = "empty";
	}

	TaskStatus(int taskId, String taskName) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDescription = "empty";
	}

	TaskStatus(int taskId, String taskName, String taskDescription) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
	}

	void displayTask() {
		System.out.println("task ID : " + taskId);
		System.out.println("task Name : " + taskName);
		System.out.println("task Description : " + taskDescription);
	}
}

public class ConstructorOverloadingDemo {
	public static void main(String[] args) {
		TaskStatus noParameter = new TaskStatus();
		TaskStatus oneParameter = new TaskStatus(01);
		TaskStatus twoParameter = new TaskStatus(02, "Self Exporation");
		TaskStatus threeParameter = new TaskStatus(03, "Training Session", "Attended Session on Java");

		System.out.println("---- No-parameter constructor ----");
		noParameter.displayTask();
		System.out.println();

		System.out.println("---- One-parameter constructor ----");
		oneParameter.displayTask();
		System.out.println();

		System.out.println("---- Two-parameter constructor ----");
		twoParameter.displayTask();
		System.out.println();

		System.out.println("---- Three-parameter constructor ----");
		threeParameter.displayTask();
		
	}
}
