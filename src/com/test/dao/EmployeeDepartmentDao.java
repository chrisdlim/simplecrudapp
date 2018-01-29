package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.model.Department;
import com.test.model.Employee;
import com.test.model.EmployeeDepartment;
import com.test.util.DbUtil;

public class EmployeeDepartmentDao {
	private Connection connection;
	
	public EmployeeDepartmentDao(){
		connection = DbUtil.getConnection();
	}
	
	//check that employee exists
	public boolean checkEmployee(String empId){
		boolean exists = false;
		
		try{
			PreparedStatement ps = connection.prepareStatement("select EMPLOYEE_ID from EMPLOYEE where EMPLOYEE_ID = ?");
			ps.setString(1, empId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				exists = true;
			}
		} catch (Exception ex){
			System.out.println("Error - " + ex.getMessage());
		}
		return exists;
	}
	
	//check that department exists
		public boolean checkDepartment(String deptId){
			boolean exists = false;
			
			try{
				PreparedStatement ps = connection.prepareStatement("select DEPARTMENT_ID from department where DEPARTMENT_ID = ?");
				ps.setString(1, deptId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					exists = true;
				}
			} catch (Exception ex){
				System.out.println("Error - " + ex.getMessage());
			}
			return exists;
		}
	
	public EmployeeDepartment getEmployeeDepartmentByEmpId(String empId){
		
		EmployeeDepartment employeeDepartment = new EmployeeDepartment();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * from employee INNER JOIN department " +
					"ON employee.EMPLOYEE_ID = department.DEPARTMENT_ID " +
					"AND employee.EMPLOYEE_ID = ?");
			preparedStatement.setString(1, empId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				employeeDepartment.setName(rs.getString("EMPLOYEE_NAME"));
				employeeDepartment.setEmpId(rs.getString("EMPLOYEE_ID"));
				employeeDepartment.setDeptId(rs.getString("DEPARTMENT_ID"));
				employeeDepartment.setDeptName(rs.getString("DEPARTMENT_NAME"));
				employeeDepartment.setDeptDesc(rs.getString("DEPARTMENT_DESCRIPTION"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return employeeDepartment;
	}
	
	public List<EmployeeDepartment> getEmployeeDepartmentByDeptId(String deptId){
		List<EmployeeDepartment> employeeDepartments = new ArrayList<EmployeeDepartment>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM employee INNER JOIN department "
					+ "on department.DEPARTMENT_ID = employee.EMPLOYEE_DEPARTMENT_ID "
					+ "AND department.DEPARTMENT_ID = ?");
			preparedStatement.setString(1, deptId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				EmployeeDepartment employeeDepartment = new EmployeeDepartment();
				employeeDepartment.setName(rs.getString("EMPLOYEE_NAME"));
				employeeDepartment.setEmpId(rs.getString("EMPLOYEE_ID"));
				employeeDepartment.setDeptId(rs.getString("DEPARTMENT_ID"));
				employeeDepartment.setDeptName(rs.getString("DEPARTMENT_NAME"));
				employeeDepartment.setDeptDesc(rs.getString("DEPARTMENT_DESCRIPTION"));
				employeeDepartments.add(employeeDepartment);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return employeeDepartments;
	}
	
	
	public List<EmployeeDepartment> getEmployeeDepartments(Employee employee, Department department){
		
		List<EmployeeDepartment> employeeDepartments = new ArrayList<EmployeeDepartment>();
		
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM employee LEFT JOIN department on department.DEPARTMENT_ID = employee.DEPARTMENT_ID ");
			
			while(rs.next()) {
				EmployeeDepartment employeeDepartment = new EmployeeDepartment();
				employeeDepartment.setName(rs.getString("EMPLOYEE_NAME"));
				employeeDepartment.setEmpId(rs.getString("EMPLOYEE_ID"));
				employeeDepartment.setDeptId(rs.getString("DEPARTMENT_ID"));
				employeeDepartment.setDeptName(rs.getString("DEPARTMENT_NAME"));
				employeeDepartment.setDeptDesc(rs.getString("DEPARTMENT_DESCRIPTION"));
				employeeDepartments.add(employeeDepartment);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return employeeDepartments;
	}
}
