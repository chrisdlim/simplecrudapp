package com.test.model;

public class Department {
	private String deptId;
	private String deptName;
	private String deptDesc;
	
	//getters and setters
	
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
