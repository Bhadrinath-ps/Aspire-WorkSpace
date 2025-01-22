package employeeApi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	private int id;
	private String name;
	private String domain;
	private double salary;

	public Employee(int id, String name, String domain, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.domain = domain;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public double getSalary() {
	    return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}

}
