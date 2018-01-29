package misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

public abstract class Entity {
	private static final String DRIVER = "org.sqlite.JDBC";
	private static final String CONNECTION_STRING = "jdbc:sqlite:test2db";

	
	protected Connection getConnection() throws Exception {
		Connection conn = null;
		
		try{
			
	        Class.forName(DRIVER);  
	        conn = DriverManager.getConnection(CONNECTION_STRING);
	        if(!hasInitData(conn)) {

	        	initDb(conn);
	        	System.out.println("Connected");
	        }
	    }catch (Exception ex){
	          throw ex;
	    }
		
		return conn;
	}
	
	private boolean hasInitData(Connection conn) {
		boolean hasData = false;
		
		try {
			DatabaseMetaData meta = conn.getMetaData();
			ResultSet set = meta.getTables(null, null, "%",new String[] {"TABLE"});
			while(set.next()) {
				String tableName = set.getString("TABLE_NAME");
				if(tableName.equalsIgnoreCase("employee")) {
					hasData = true;
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return hasData;
	}
	
	private void initDb(Connection con) {
		String initSql = getInitScriptAsString();
		
		try {
			con.createStatement().executeUpdate(initSql);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	private String getInitScriptAsString() {
		String sql = "";
		
		try (BufferedReader scanner = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/init.sql")))){
			StringBuilder builder = new StringBuilder();
			String line = "";
			
			while((line=scanner.readLine()) != null) {
			    builder.append(line);
			}
			
			sql = builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return sql;
	}
}
