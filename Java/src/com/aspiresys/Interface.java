package com.aspiresys;

interface DeveloperInterface {
	void devTask();
	default void task() {
		System.out.println("Task Updates");
	}
}

class JavaDeveloper implements DeveloperInterface {
	@Override
	public void devTask() {
		System.out.println("Task on JAVA");
	}
}

class SQLDeveloper implements DeveloperInterface {
	@Override
	public void devTask() {
		System.out.println("Task on SQL");
	}
}

class Interface {
	public static void main (String[] args) {
		DeveloperInterface jd = new JavaDeveloper();
		jd.devTask();
		
		
		DeveloperInterface sd = new SQLDeveloper();
		sd.devTask();
		
		sd.task();
	}
}