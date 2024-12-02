package com.aspiresys;

interface DeveloperInterface {
	void developmentTask();
	default void task() {
		System.out.println("Task Updates");
	}
}

class JavaDeveloper implements DeveloperInterface {
	@Override
	public void developmentTask() {
		System.out.println("Task on JAVA");
	}
}

class SQLDeveloper implements DeveloperInterface {
	@Override
	public void developmentTask() {
		System.out.println("Task on SQL");
	}
}

class Interface {
	public static void main (String[] args) {
		DeveloperInterface javaDeveloper = new JavaDeveloper();
		javaDeveloper.developmentTask();
		
		
		DeveloperInterface sqlDeveloper = new SQLDeveloper();
		sqlDeveloper.developmentTask();
		
		sqlDeveloper.task();
	}
}