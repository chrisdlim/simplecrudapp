package com.test.model;

public class EmployeeDepartment {
	private String empName;
	private String empId;
	private String deptId;
	private String deptName;
	private String deptDesc;
	
	public void setName(String empName){
		this.empName = empName;
	}
	
	public String getName(){
		return empName;
	}
	
	public void setEmpId(String empId){
		this.empId = empId;
	}
	
	public String getEmpId(){
		return empId;
	}
	
	public void setDeptId(String deptId){
		this.deptId = deptId;
	}
	
	public String getDeptId(){
		return deptId;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}
	
	public String getDeptName(){
		return deptName;
	}
	
	public void setDeptDesc(String deptDesc){
		this.deptDesc = deptDesc;
	}
	
	public String getDeptDesc(){
		return deptDesc;
	}
	
}
