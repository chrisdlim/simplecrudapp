package com.test.model;

public class Manager {
	private String mgrId;
	private String mgrAsId;
	private String empId;
	
	//getters and setters
	
	public void setMgrId(String mgrId){
		this.mgrId = mgrId;
	}
	
	public String getMgrId(){
		return mgrId;
	}
	
	public void setMgrAsId(String mgrAsId){
		this.mgrAsId = mgrAsId;
	}
	
	public String getMgrAsId(){
		return mgrAsId;
	}
	
	public void setEmpId(String empId){
		this.empId = empId;
	}
	
	public String getEmpId(){
		return empId;
	}
}
