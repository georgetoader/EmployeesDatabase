package Controller;

import Model.Employee;
import Service.EmployeeService;
import Service.EmployeeServiceImpl;

import java.util.Scanner;

public class EmployeeMain {

	public static void menu() {
		System.out.println();
		System.out.println("""
				Available commands:
				1. Add Employee
				2. View Employee
				3. Update Employee
				4. Delete Employee
				5. View All Employees
				6. Exit""");
		System.out.println();
	}

	public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmployeeService service = new EmployeeServiceImpl();

		while (true) {
			//Method call
			menu();
			System.out.println("Enter a valid command: ");
			int choice = sc.nextInt();

			switch (choice) {
				case 1 -> {	//Adding Employee
					int id, age, experience;
					double salary;
					String name, designation;
					System.out.println("Please enter the number of Employees you want to add:");
					int n = sc.nextInt();
					for (int i = 0; i < n; i++) {
						System.out.println("Enter id:");
						id = sc.nextInt();
						System.out.println("Enter name: ");
						name = sc.next();
						sc.nextLine();
						System.out.println("Enter age: ");
						age = sc.nextInt();
						System.out.println("Enter designation: ");
						designation = sc.next();
						System.out.println("Enter salary: ");
						salary = sc.nextDouble();
						System.out.println("Enter experience: ");
						experience = sc.nextInt();

						service.addEmployee(new Employee(id, name, age, designation, salary, experience));
						System.out.println("Employee added succesfully.");
					}
				}

				case 2 -> {	//View Employee by id
					System.out.println("Please enter Employee ID: ");
					int id = sc.nextInt();
					boolean found2 = false;
					for (Employee e : service.showAllEmployees()) {
						if (e.getId() == id) {
							System.out.println("----------\nEmployee ID: " + e.getId() + "\nEmployee Name: " + e.getName() + "\nEmployee Age: " + e.getAge() + "\nEmployee Designation: " + e.getDesignation()
									+ "\nEmployee Salary: " + e.getSalary() + "\nEmployee Experience: " + e.getExperience() + "\n----------");
							found2 = true;
						}
					}
					if (!found2) {
						System.out.println("Employee not found.");
					}
				}

				case 3 -> {	//Update Employee Details
					System.out.println("Please enter Employee ID: ");
					int id = sc.nextInt();
					boolean found = false;
					for (Employee e : service.showAllEmployees()) {
						if (e.getId() == id) {
							System.out.println("Enter new name for Employee of ID " + id + ": ");
							String newname = sc.next();
							System.out.println("Enter new age for Employee of ID " + id + ": ");
							int newage = sc.nextInt();
							System.out.println("Enter new designation for Employee of ID " + id + ": ");
							String newdesignation = sc.next();
							System.out.println("Enter new Salary for Employee of ID " + id + ": ");
							double newsal = sc.nextDouble();
							System.out.println("Enter new Experience for Employee of ID " + id + ": ");
							int newexp = sc.nextInt();

							service.updateEmployee(new Employee(id, newname, newage, newdesignation, newsal, newexp));

							found = true;
							System.out.println("Employee details updated successfully.");
						}
					}
					if (!found) {
						System.out.println("Employee not found.");
					}
				}

				case 4 -> {	//Delete Employee
					System.out.println("Please enter Employee ID: ");
					int id = sc.nextInt();
					boolean found = false;
					for (Employee e : service.showAllEmployees()) {
						if (e.getId() == id) {
							service.deleteEmployee(id);
							System.out.println("Employee deleted successfully.");
							found = true;
						}
					}
					if (!found) {
						System.out.println("Employee not found.");
					}
				}

				case 5 -> {	//View All Employees
					System.out.println("All Employees list:");
					for (Employee e : service.showAllEmployees()) {
						System.out.println("----------\nEmployee ID: " + e.getId() + "\nEmployee Name: " + e.getName() + "\nEmployee Age: " + e.getAge()
								+ "\nEmployee Designation: " + e.getDesignation() + "\nEmployee Salary: " + e.getSalary() + "\nEmployee Experience: " + e.getExperience());
					}
				}

				case 6 -> {	//Exit
					System.out.println("Exiting...");
					System.exit(0);
				}

				default -> System.out.println("Please enter a valid command.");
			}

		}
	}
	
}

