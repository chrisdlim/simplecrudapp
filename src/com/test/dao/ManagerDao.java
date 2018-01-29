package com.test.dao;
import java.sql.*;
import java.util.*;

import com.test.model.Manager;
import com.test.util.DbUtil;

public class ManagerDao {

	private Connection connection;
	
	public ManagerDao(){
		connection = DbUtil.getConnection();
	}
	
	public void checkManager(Manager manager){
		try{
			PreparedStatement ps = connection.prepareStatement("select MANAGER_ID from manager_association where MANAGER_ASSOCIATION_ID=?");
			ps.setString(1,  manager.getMgrAsId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				System.out.println("Updating");
				updateManager(manager);
			} else{
				System.out.println("Adding");
				addManager(manager);
			}
		} catch (Exception ex){
			System.out.println("Error - " + ex.getMessage());
		}
		System.out.println("done checking manager");
	}
	
	public void addManager(Manager manager) {
		try{																  
			PreparedStatement preparedStatement = connection.prepareStatement("insert into manager_association (MANAGER_ID, MANAGER_ASSOCIATION_ID, MANAGER_EMPLOYEE_ID) values (?, ?, ?)");
			preparedStatement.setString(1, manager.getMgrId());
			preparedStatement.setString(2, manager.getMgrAsId());
			preparedStatement.setString(3, manager.getEmpId());
			preparedStatement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	private void updateManager(Manager manager) {
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("update manager_association set MANAGER_ID=?, MANAGER_EMPLOYEE_ID=?" +
					"WHERE MANAGER_ASSOCIATION_ID=?");
			preparedStatement.setString(1, manager.getMgrId());
			preparedStatement.setString(2, manager.getEmpId());
			preparedStatement.setString(3, manager.getMgrAsId());
			preparedStatement.executeUpdate();
		} catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	public void deleteManager(String mgrAsId) {
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from manager_association where MANAGER_ASSOCIATION_ID=?");
            preparedStatement.setString(1, mgrAsId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public List<Manager> getAllManagers(){
		List<Manager> managers = new ArrayList<Manager>();
		try{
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select * from manager_association");
			while(rs.next()) {
				Manager manager = new Manager();
				manager.setMgrId(rs.getString("MANAGER_ID"));
				manager.setMgrAsId(rs.getString("MANAGER_ASSOCIATION_ID"));
				manager.setEmpId(rs.getString("MANAGER_EMPLOYEE_ID"));
				managers.add(manager);
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return managers;
	}
	
	public Manager getManagerById(String mgrAsId){
		Manager manager = new Manager();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("select * from manager_association where MANAGER_ASSOCIATION_ID = ?");
			preparedStatement.setString(1, mgrAsId);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.next()){
				manager.setMgrId(rs.getString("MANAGER_ID"));
				manager.setMgrAsId(rs.getString("MANAGER_ASSOCIATION_ID"));
				manager.setEmpId(rs.getString("MANAGER_EMPLOYEE_ID"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		return manager;
	}
}
