package Dao;

import Model.Employee;

import java.util.List;

public interface EmployeeDao {
	String URL="jdbc:mysql://localhost:3306/test";
	String USERNAME="root";
	String PASSWORD="root";
	String DRIVER_CLASSNAME="com.mysql.cj.jdbc.Driver";

	void addEmployee(Employee e);
	void deleteEmployee(int empid);
	void updateEmployee(Employee e);
	Employee findEmployee(Employee e);
	List<Employee> showAllEmployees();
}
