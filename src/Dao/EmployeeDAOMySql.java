package Dao;

import Model.Employee;
import Utility.EmployeeNotFoundException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeDAOMySql implements EmployeeDao {

	private Connection con = null;
	private PreparedStatement pstate = null;
	private ResultSet res = null;
	
	public static final String INS_COMMAND = "INSERT INTO employee VALUES (?,?,?,?,?,?)";
	public static final String UPDATE_COMMAND = "UPDATE employee SET name=?, age=?, designation=?, salary=?, experience=? WHERE id=?";
	public static final String DELETE_COMMAND = "DELETE FROM employee WHERE id=?";
	public static final String FIND_COMMAND = "SELECT * FROM employee WHERE id=?";
	public static final String SELECT_ALL = "SELECT * FROM employee";
	
	public EmployeeDAOMySql() {
		try {
			con = DriverManager.getConnection(EmployeeDao.URL, EmployeeDao.USERNAME, EmployeeDao.PASSWORD);
		} catch (SQLException ex) {
			System.out.println("SQL Message - Unable to establish the Connection with Database.");
			ex.printStackTrace();
		}
	}

	@Override
	public void addEmployee(Employee e) {
		try {
			pstate = con.prepareStatement(INS_COMMAND);
			pstate.setInt(1,  e.getId());
			pstate.setString(2, e.getName());
			pstate.setInt(3, e.getAge());
			pstate.setString(4, e.getDesignation());
			pstate.setDouble(5, e.getSalary());
			pstate.setInt(6, e.getExperience());
			pstate.executeUpdate();
			pstate.close();
		} catch (SQLException ex) {
			System.out.println("SQL Message - Adding Record failed... Unable to execute INS Command.");
			ex.printStackTrace();
		}
	}

	@Override
	public void deleteEmployee(int id) {
		try {
			pstate = con.prepareStatement(DELETE_COMMAND);
			pstate.setInt(1, id);
			pstate.executeUpdate();
			pstate.close();
		} catch (SQLException ex) {
			System.out.println("SQL Message - Delete Operation Failed... Unable to execute DELETE command.");
			ex.printStackTrace();
		}
	}

	@Override
	public void updateEmployee(Employee e) {
		try {
			pstate = con.prepareStatement(UPDATE_COMMAND);
			pstate.setInt(6,  e.getId());
			pstate.setString(1, e.getName());
			pstate.setInt(2, e.getAge());
			pstate.setString(3, e.getDesignation());
			pstate.setDouble(4, e.getSalary());
			pstate.setInt(5, e.getExperience());
			pstate.executeUpdate();
			pstate.close();
		} catch (SQLException ex) {
			System.out.println("SQL Message - Update Operation Failed...Unable to execute UPDATE command.");
			ex.printStackTrace();
		}
	}

	@Override
	public Employee findEmployee(Employee e) {
		Employee temp = null;
		try {
			pstate = con.prepareStatement(FIND_COMMAND);
			pstate.setInt(1, e.getId());
			res = pstate.executeQuery();
			if(!res.next()) {
				throw new EmployeeNotFoundException(e.getId());
			}
			temp = new Employee(e.getId(), res.getString("name"), res.getInt("age"),
					res.getString("designation"), res.getDouble("salary"), res.getInt("experience"));
			res.close();
			pstate.close();
		} catch (SQLException | EmployeeNotFoundException ex) {
			ex.printStackTrace();
		}

		return temp;
	}

	@Override
	public List<Employee> showAllEmployees() {
		List <Employee> list = new LinkedList<>();
		try {
			pstate = con.prepareStatement(SELECT_ALL);
			res = pstate.executeQuery();
			while(res.next()) {
				list.add(new Employee(res.getInt("id"), res.getString("name"), res.getInt("age"),
						res.getString("designation"), res.getDouble("salary"), res.getInt("experience")));
			}
			res.close();
			pstate.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
}
