package LLK.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	
	public static Connection conn = null;
	public static void myClose(Connection conn, Statement stat, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat != null) {
			try {
				stat.close();
				stat = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConn() {
	    try {
	      Class.forName("org.sqlite.JDBC");
	      conn = DriverManager.getConnection("jdbc:sqlite:test.db");
          if(!conn.isClosed())
              System.out.println("Succeeded connecting to the Database!");
		      conn.createStatement();
			return conn;
	    } catch ( Exception e ) {
	    		e.printStackTrace();
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    
	    System.out.println("Opened database successfully");
	    	return  null;
	}
	
	public static void main(String[] args) {
		
	}

}
