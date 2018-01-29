package com.test.dao;

import java.sql.*;
import java.util.*;
import com.test.model.Employee;
import com.test.util.DbUtil; 

public class EmployeeDao {
	
	private Connection connection;
	
	public EmployeeDao(){
		connection = DbUtil.getConnection();
	}
	
	public void checkEmployee(Employee employee){
		try{
			PreparedStatement ps = connection.prepareStatement("select EMPLOYEE_ID from EMPLOYEE where EMPLOYEE_ID = ?");
			ps.setString(1,  employee.getEmpId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("Updating");
				updateEmployee(employee);
			} else{
				System.out.println("Adding");
				addEmployee(employee);
			}
		} catch (Exception ex){
			System.out.println("Error - " + ex.getMessage());
		}
		System.out.println("done checking");
	}

	public void addEmployee(Employee employee) {
		try{																  
			PreparedStatement preparedStatement = connection.prepareStatement("insert into employee (EMPLOYEE_NAME, EMPLOYEE_ID , EMPLOYEE_DEPARTMENT_ID) values (?, ?, ?)");
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getEmpId());
			preparedStatement.setString(3, employee.getDeptId());
			preparedStatement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}

	private void updateEmployee(Employee employee) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("update employee set EMPLOYEE_NAME=?, EMPLOYEE_DEPARTMENT_ID=?" +
					"WHERE EMPLOYEE_ID=?");
			preparedStatement.setString(1, employee.getName());
			preparedStatement.setString(2, employee.getDeptId());
			preparedStatement.setString(3, employee.getEmpId());
			preparedStatement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteEmployee(String empId) {
        System.out.println("empid: " +empId);
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from employee where EMPLOYEE_ID=?");
            preparedStatement.setString(1, empId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public List<Employee> getAllEmployees(){
		List<Employee> employees = new ArrayList<Employee>();
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from employee");
			while(rs.next()) {
				Employee employee = new Employee();
				employee.setName(rs.getString("EMPLOYEE_NAME"));
				employee.setEmpId(rs.getString("EMPLOYEE_ID"));
				employee.setDeptId(rs.getString("EMPLOYEE_DEPARTMENT_ID"));
				employees.add(employee);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return employees;
	}
	
	public Employee getEmployeeById(String empId){
		Employee employee = new Employee();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select * from employee where EMPLOYEE_ID = ?");
			preparedStatement.setString(1, empId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				employee.setName(rs.getString("EMPLOYEE_NAME"));
				employee.setEmpId(rs.getString("EMPLOYEE_ID"));
				employee.setDeptId(rs.getString("EMPLOYEE_DEPARTMENT_ID"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return employee;
	}
}


