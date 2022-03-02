package Utility;

public class EmployeeNotFoundException extends Exception{
    private int id;

	public EmployeeNotFoundException(int id) {
		this.id = id;
	}
	
	public String toString() {
		return "Employee Not Found Exception" + this.id;
	}
 
}
