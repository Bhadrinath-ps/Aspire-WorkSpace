package com.aspiresys;

//Method Overloading

class EmployeeStatus {
	
	void employeeDetails(int employeeID, String name) {
		System.out.println("Employee ID: "+employeeID +"Employee Name: " +name);
	}
	
	void employeeDetails(int employeeID, String name, float salary) {
		System.out.println("Employee ID: "+employeeID +"Employee Name: " +name +"Salary: "+salary);
	}
	
	void employeeDetails() {
		System.out.println("Add Employee Details!!");
	}
	
}

//Method Overriding

class EmployeeRole extends EmployeeStatus {
	
	void employeeDetails(String designation) {
		System.out.println("Employee Designation: "+designation);
	}
	
}

public class Polymorphism {

	public static void main(String[] args) {
		EmployeeStatus employee = new EmployeeStatus();
		employee.employeeDetails();
		employee.employeeDetails(01, "Bhadri");
		employee.employeeDetails(02, "Gopi", 25000);
		
		EmployeeRole role = new EmployeeRole();
		role.employeeDetails("Developer");
	}

}

