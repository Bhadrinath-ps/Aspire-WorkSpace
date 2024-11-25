package com.aspiresys;

//Method Overloading

class EmpStatus {
	
	void empDetails(int empID, String name) {
		System.out.println("Employee ID: "+empID +"Employee Name: " +name);
	}
	
	void empDetails(int empID, String name, float sal) {
		System.out.println("Employee ID: "+empID +"Employee Name: " +name +"Salary: "+sal);
	}
	
	void empDetails() {
		System.out.println("Add Employee Details!!");
	}
	
}

//Method Overriding

class EmpRole extends EmpStatus {
	
	void empDetails(String designation) {
		System.out.println("Employee Designation: "+designation);
	}
	
}

public class Polymorphism {

	public static void main(String[] args) {
		EmpStatus emp = new EmpStatus();
		emp.empDetails();
		emp.empDetails(01, "Bhadri");
		emp.empDetails(02, "Gopi", 25000);
		
		EmpRole role = new EmpRole();
		role.empDetails("Developer");
	}

}

