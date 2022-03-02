package Model;

public class Employee {
	private final int id;
	private final String name;
	private final int age;
	private final String designation;
	private final double salary;
	private final int experience;

	public Employee(int id, String name, int age, String designation, double salary, int experience) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.designation = designation;
		this.salary = salary;
		this.experience = experience;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getDesignation() {
		return designation;
	}

	public double getSalary() {
		return salary;
	}

	public int getExperience() {
		return experience;
	}
}
