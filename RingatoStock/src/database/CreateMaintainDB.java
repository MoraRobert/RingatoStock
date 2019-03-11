package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.User;

public class CreateMaintainDB {
	
	public static void createTable() {
			
			try(Connection conn = DriverManager
					.getConnection("jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\RingatoStock\\ring.db");		
			Statement statement = conn.createStatement();){
				
			statement.execute("CREATE TABLE IF NOT EXISTS user"
					+ "(name TEXT, password TEXT, securityLevel TEXT)");
		
		} catch(SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
		}
	}
	
	public static void insertUser(User user) {
		
		try(Connection conn = DriverManager
				.getConnection("jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\RingatoStock\\ring.db");		
		Statement statement = conn.createStatement();){
			
		statement.execute("INSERT INTO user VALUES ('" +
				user.getName() + "', '" +
				user.getPassword() + "', '" +
				user.getSecurityLevel() + "')");				
	
		} catch(SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
		}
	}
	
	public static User findUser(String userName, String password) throws database.DatabaseException {		
		
		try(Connection conn = DriverManager
				.getConnection("jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\RingatoStock\\ring.db");		
		Statement statement = conn.createStatement();){		
		
		ResultSet results = statement
				.executeQuery("SELECT * FROM user WHERE password = " + password + " AND name = '" + userName+"'");
		
		if(!results.next()) {
			System.out.println("no such name/pass pair");
			return null;
		} else {
		
			User user = new User (	results.getString("name"), 
									results.getString("password"),
									results.getString("securityLevel"));
			System.out.println("User " + userName + " successfully found in database");
			return user;
		}		 
		
		} catch(SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
			throw new DatabaseException("Query problem", e);
		}
	}
	
	public static void main(String[] args) {
/*		createTable();
		User kati = new User("Viki", "123", "w");
		insertUser(kati);
		
		try {
			System.out.println(findUser("Pisti", "100"));
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		try(Connection conn = DriverManager
				.getConnection("jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\SessionTest\\user.db");		
		Statement statement = conn.createStatement();){
		
			statement.execute("INSERT INTO user VALUES ('Viki', '123', 'w')");
			statement.execute("INSERT INTO user VALUES ('Ili', '456', 'w')");
			statement.execute("INSERT INTO user VALUES ('Robi', '789', 'r')");
	
		} catch(SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
		}
*/		
	}
}