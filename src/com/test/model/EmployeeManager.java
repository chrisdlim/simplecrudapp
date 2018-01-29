package com.test.model;

public class EmployeeManager {
	private String empName;
	private String empId;
	private String deptId;
	private String mgrId;
	private String mgrName;
	
	public void setEmpName(String empName){
		this.empName = empName;
	}
	
	public String getEmpName(){
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

	public void setMgrId(String mgrId){
		this.mgrId = mgrId;
	}
	
	public String getMgrId(){
		return mgrId;
	}

	public String getMgrName(){
		return mgrName;
	}
	
	public void setMgrName(String mgrName) {
		this.mgrName = mgrName;
	}
}
