package com.test.dao;

import java.sql.*;
import java.util.*;
import com.test.model.Department;
import com.test.util.DbUtil;

public class DepartmentDao {

	private Connection connection;
	
	public DepartmentDao(){
		connection = DbUtil.getConnection();
	}
	
	public void checkDepartment(Department department){
		try{
			PreparedStatement ps = connection.prepareStatement("select DEPARTMENT_ID from DEPARTMENT where DEPARTMENT_ID=?");
			ps.setString(1,  department.getDeptId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("Updating");
				updateDepartment(department);
			} else{
				System.out.println("Adding");
				addDepartment(department);
			}
		} catch (Exception ex){
			System.out.println("Error - " + ex.getMessage());
		}
		System.out.println("done checking department");
	}

	public void addDepartment(Department department) {
		try{																  
			PreparedStatement preparedStatement = connection.prepareStatement("insert into department (DEPARTMENT_ID, DEPARTMENT_NAME, DEPARTMENT_DESCRIPTION) values (?, ?, ?)");
			preparedStatement.setString(1, department.getDeptId());
			preparedStatement.setString(2, department.getDeptName());
			preparedStatement.setString(3, department.getDeptDesc());
			preparedStatement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	private void updateDepartment(Department department) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("update department set DEPARTMENT_NAME=?, DEPARTMENT_DESCRIPTION=?" +
					"WHERE DEPARTMENT_ID=?");
			preparedStatement.setString(1, department.getDeptName());
			preparedStatement.setString(2, department.getDeptDesc());
			preparedStatement.setString(3, department.getDeptId());
			preparedStatement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteDepartment(String deptId) {
        System.out.println("deptid: " +deptId);
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from department where DEPARTMENT_ID=?");
            preparedStatement.setString(1, deptId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public List<Department> getAllDepartments(){
		List<Department> departments = new ArrayList<Department>();
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from department");
			while(rs.next()) {
				Department department = new Department();
				department.setDeptName(rs.getString("DEPARTMENT_NAME"));
				department.setDeptId(rs.getString("DEPARTMENT_ID"));
				department.setDeptDesc(rs.getString("DEPARTMENT_DESCRIPTION"));
				departments.add(department);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return departments;
	}
	
	public Department getDepartmentById(String deptId){
		Department department = new Department();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select * from department where DEPARTMENT_ID = ?");
			preparedStatement.setString(1, deptId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				department.setDeptName(rs.getString("DEPARTMENT_NAME"));
				department.setDeptId(rs.getString("DEPARTMENT_ID"));
				department.setDeptDesc(rs.getString("DEPARTMENT_DESC"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return department;
	}
}
