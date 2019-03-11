package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ringato.services.StockItem;

import exeptions.DatabaseException;
import model.Book;

public class Dao {

	public void createTable() {
		
		try(Connection conn = DriverManager
				.getConnection("jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\RingatoStock\\ring.db");		
		Statement statement = conn.createStatement();){
		
		statement.execute("CREATE TABLE IF NOT EXISTS books"
				+ "(title TEXT, quantity INTEGER, author TEXT, illustrator TEXT, price INTEGER)");
	
	} catch(SQLException e) {
		System.out.println("Something went wrong" + e.getMessage());
	}
		
	}
	public void insertItem(StockItem bookToStore) {
		try(Connection conn = DriverManager
				.getConnection("jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\RingatoStock\\ring.db");		
			Statement statement = conn.createStatement()) {
		
		statement.execute("INSERT INTO books " +
				"(title, quantity, author, illustrator, price)"
				+ " VALUES('" + 
				bookToStore.getTitle() + "', '" +
				bookToStore.getQuantity() + "', '" +
				bookToStore.getAuthor() + "', '" +
				bookToStore.getIllustrator() + "', '" +
				bookToStore.getPrice() +
				"')");
	
		} catch(SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
		}	
	}
	
	public List<Book> queryDB () throws DatabaseException {
		List<Book> listToReturn = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(
				"jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\RingatoStock\\ring.db");
				Statement statement = conn.createStatement();) {			
		
		ResultSet results = statement
				.executeQuery("SELECT * FROM books");
		
		while (results.next()) {
			Book bookToReturn = new Book(results.getString("title"), results.getInt("quantity"));
			bookToReturn.setAuthor(results.getString("author"));
			bookToReturn.setIllustrator(results.getString("illustrator"));
			bookToReturn.setPrice(results.getString("price"));
			
			listToReturn.add(bookToReturn);
		}
		
		return listToReturn;
	
		} catch(SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
			throw new DatabaseException("Query problem", e);
		}		
	}
	
	public List<Book> queryDBbyTitle(String bookTitle) throws DatabaseException {
		List<Book> listToReturn = new ArrayList<>();
		try(Connection conn = DriverManager.getConnection(
				"jdbc:sqlite:C:\\Users\\Róbert\\eclipse-workspace\\RingatoStock\\ring.db")) {
			
		Statement statement = conn.createStatement();
		ResultSet results = statement
				.executeQuery("SELECT * FROM books WHERE TITLE = " + bookTitle);
		
		while (results.next()) {
			Book bookToReturn = new Book(results.getString("title"), results.getInt("quantity"));
			bookToReturn.setAuthor(results.getString("author"));
			bookToReturn.setIllustrator(results.getString("illustrator"));
			bookToReturn.setPrice(results.getString("price"));
			
			listToReturn.add(bookToReturn);
		}
		
		return listToReturn;
	
		} catch(SQLException e) {
			System.out.println("Something went wrong" + e.getMessage());
			throw new DatabaseException("Query problem", e);
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("I'm running");
/*		createTable();
		Book theBookOfR = new Book("Sarkany paripa", 7);
		theBookOfR.setAuthor("Ili");
		theBookOfR.setIllustrator("nemtom");
		theBookOfR.setPrice("3000");
		insertItem(theBookOfR);
		
		try {
			System.out.println(queryDB().toString());
		} catch (DatabaseException e) {		
			e.printStackTrace();
		}*/
	}

	
}
