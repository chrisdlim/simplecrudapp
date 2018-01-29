package com.test.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.model.Employee;
import com.test.model.EmployeeDepartmentManager;
import com.test.util.DbUtil;

public class EmployeeDepartmentManagerDao {

private Connection connection;
	
	public EmployeeDepartmentManagerDao(){
		connection = DbUtil.getConnection();
	}
	
	public List<EmployeeDepartmentManager> showAll(){
		List<EmployeeDepartmentManager> listAll = new ArrayList<EmployeeDepartmentManager>();
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(
					"SELECT e1.EMPLOYEE_ID AS EMP_ID, e1.EMPLOYEE_NAME, e1.EMPLOYEE_DEPARTMENT_ID, DEPARTMENT.DEPARTMENT_NAME AS DEPT_NAME, "
					+ "e2.EMPLOYEE_ID AS MGR_ID, e2.EMPLOYEE_NAME AS MGR_NAME, DEPARTMENT.DEPARTMENT_DESCRIPTION AS DEPT_DESC,"
					+ "MANAGER_ASSOCIATION.MANAGER_ASSOCIATION_ID AS MGR_AS_ID "
					+ "FROM EMPLOYEE e1 LEFT JOIN MANAGER_ASSOCIATION ON e1.EMPLOYEE_ID = MANAGER_ASSOCIATION.MANAGER_EMPLOYEE_ID "
					+ "JOIN DEPARTMENT ON e1.EMPLOYEE_DEPARTMENT_ID = DEPARTMENT.DEPARTMENT_ID "
					+ "LEFT JOIN EMPLOYEE e2 ON MANAGER_ASSOCIATION.MANAGER_ID = e2.EMPLOYEE_ID "
					+ "ORDER BY e1.EMPLOYEE_ID");
			while(rs.next()) {
				EmployeeDepartmentManager all = new EmployeeDepartmentManager();
				all.setEmpName(rs.getString("EMPLOYEE_NAME"));
				all.setEmpId(rs.getString("EMP_ID"));
				all.setDeptId(rs.getString("EMPLOYEE_DEPARTMENT_ID"));
				all.setDeptName(rs.getString("DEPT_NAME"));
				all.setDeptDesc(rs.getString("DEPT_DESC"));
				all.setMgrAsId(rs.getString("MGR_AS_ID"));
				all.setMgrId(rs.getString("MGR_ID"));
				all.setMgrName(rs.getString("MGR_NAME"));
				listAll.add(all);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return listAll;
	}
}
