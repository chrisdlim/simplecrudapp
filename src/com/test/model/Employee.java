package com.test.model;

public class Employee {
	private String name;
	private String empId;
	private String deptId;
	
	//getters & setters
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
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
}
