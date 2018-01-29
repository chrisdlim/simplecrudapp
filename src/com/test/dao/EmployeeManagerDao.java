package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.model.EmployeeManager;
import com.test.util.DbUtil;

public class EmployeeManagerDao {
	private Connection connection;
	
	public EmployeeManagerDao(){
		connection = DbUtil.getConnection();
	}
	
	//check that manager exists
	public boolean checkManager(String mgrId){
		boolean exists = false;
		
		try{
			PreparedStatement ps = connection.prepareStatement("select MANAGER_ID from manager_association where MANAGER_ID = ?");
			ps.setString(1, mgrId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				exists = true;
			}
		} catch (Exception ex){
			System.out.println("Error - " + ex.getMessage());
		}
		return exists;
	}
	
	//check that employee exists
		public boolean checkEmployee(String empId){
			boolean exists = false;
			
			try{
				PreparedStatement ps = connection.prepareStatement("select EMPLOYEE_ID from employee where EMPLOYEE_ID = ?");
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
	
	//returns employee's manager information
	public EmployeeManager getEmployeeManagerByEmpId(String empId){
		
		EmployeeManager employeeManager = new EmployeeManager();
		
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT e1.EMPLOYEE_ID AS EMP_ID, e1.EMPLOYEE_NAME, e1.EMPLOYEE_DEPARTMENT_ID, DEPARTMENT.DEPARTMENT_NAME AS DEPT_NAME, "
					+ "e2.EMPLOYEE_ID AS MGR_ID, e2.EMPLOYEE_NAME AS MGR_NAME "
					+ "FROM EMPLOYEE e1 LEFT JOIN MANAGER_ASSOCIATION ON e1.EMPLOYEE_ID = MANAGER_ASSOCIATION.MANAGER_EMPLOYEE_ID "
					+ "JOIN DEPARTMENT ON e1.EMPLOYEE_DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID "
					+ "LEFT JOIN EMPLOYEE e2 ON MANAGER_ASSOCIATION.MANAGER_ID = e2.EMPLOYEE_ID "
					+ "WHERE e1.EMPLOYEE_ID=?");
			preparedStatement.setString(1, empId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				employeeManager.setEmpName(rs.getString("EMPLOYEE_NAME"));
				employeeManager.setEmpId(rs.getString("EMP_ID"));
				employeeManager.setDeptId(rs.getString("EMPLOYEE_DEPARTMENT_ID"));
				employeeManager.setMgrId(rs.getString("MGR_ID"));
				employeeManager.setMgrName(rs.getString("MGR_NAME"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return employeeManager;
	}

	//returns list of employees for a manager
	public List<EmployeeManager> getEmployeesByMgrId(String mgrId){
		List<EmployeeManager> employees = new ArrayList<EmployeeManager>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT e1.EMPLOYEE_ID AS EMP_ID, e1.EMPLOYEE_NAME, e1.EMPLOYEE_DEPARTMENT_ID, DEPARTMENT.DEPARTMENT_NAME AS DEPT_NAME, "
					+ "e2.EMPLOYEE_ID AS MGR_ID, e2.EMPLOYEE_NAME AS MGR_NAME "
					+ "FROM EMPLOYEE e1 LEFT JOIN MANAGER_ASSOCIATION ON e1.EMPLOYEE_ID = MANAGER_ASSOCIATION.MANAGER_EMPLOYEE_ID "
					+ "JOIN DEPARTMENT ON e1.EMPLOYEE_DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID "
					+ "LEFT JOIN EMPLOYEE e2 ON MANAGER_ASSOCIATION.MANAGER_ID = e2.EMPLOYEE_ID "
					+ "WHERE e2.EMPLOYEE_ID=?");
			preparedStatement.setString(1, mgrId);
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()){
				EmployeeManager employee = new EmployeeManager();
				employee.setEmpName(rs.getString("EMPLOYEE_NAME"));
				employee.setEmpId(rs.getString("EMP_ID"));
				employee.setDeptId(rs.getString("EMPLOYEE_DEPARTMENT_ID"));
				employee.setMgrId(rs.getString("MGR_ID"));
				employee.setMgrName(rs.getString("MGR_NAME"));
				employees.add(employee);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return employees;
	}
}
