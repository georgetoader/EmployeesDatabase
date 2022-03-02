package Service;

import Model.Employee;

import java.util.List;

public interface EmployeeService {
    void addEmployee(Employee e);
	void deleteEmployee(int id);
	void updateEmployee(Employee e);
	Employee findEmployee(Employee e);
	List<Employee> showAllEmployees();
}
