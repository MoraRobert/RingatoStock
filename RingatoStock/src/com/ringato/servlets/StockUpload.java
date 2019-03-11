package com.ringato.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ringato.services.InMemory;
import com.ringato.services.StockItem;

import database.CreateMaintainDB;
import database.Dao;
import database.DatabaseException;
import model.Book;
import model.User;

/**
 * Servlet implementation class StockUpload
 */
@WebServlet("/StockUpload")
public class StockUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockUpload() {
        super();        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //private InMemory ringatoDB = InMemory.getInstance();
    private Dao ringatoRegistry = new Dao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

/*		response.getWriter().append("<b>Served at</b>: ").append(request.getContextPath())
		.append("<form method=\"POST\">\r\n" + 
				"  User name:<br>\r\n" + 
				"  <input type=\"text\" name=\"username\"><br>\r\n" + 
				"  Password:<br>\r\n" + 
				"  <input type=\"text\" name=\"password\"><input type=\"submit\" value=\"Send\">" + 
				"</form>");
*/		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("form.html");
				
		requestDispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//String username = request.getParameter("username");
		//String password = request.getParameter("password");
		HttpSession session=request.getSession(true);  
				
		try {
			User user = CreateMaintainDB.findUser(	request.getParameter("username"), 
													request.getParameter("password"));
			
			if (user != null) {
				if(user.getSecurityLevel().equalsIgnoreCase("w")) {
			    session.setAttribute("USER", user);
			    response.setContentType("text/html; charset=UTF-8");
			    response.getWriter().append("Successfully logged in.");
			    response.getWriter().append("The uploaded book is: " + request.getParameter("book-title"));
				response.getWriter().append(". The quantity of the book is: " + request.getParameter("book-quantity"));
				
				Book newBook = 
						new Book(request.getParameter("book-title"), 
						Integer.parseInt(request.getParameter("book-quantity")));
				newBook.setAuthor(request.getParameter("book-author"));
				newBook.setIllustrator(request.getParameter("book-illustrator"));
				newBook.setPrice(request.getParameter("price"));
				
				ringatoRegistry.insertItem(newBook);
			
				} else {
					response.getWriter().append("Login failed, you don't have the right to write");
				}
			} else {
				response.getWriter().append("Login failed, no such user.");
			}
		} catch (DatabaseException e) {			
			e.printStackTrace();
		}
		
		//ringatoRegistry.createTable();  // TODO: put in the right place
/*		ringatoRegistry.insertItem(newBook);*/
		
		//ringatoDB.addToStock(newItem);    // used in the previous version
		
		response.getWriter().append("<p><a href='form.html'>BACK</a></p>");
	
	}

/*	
	public InMemory getRingatoDB() {
		return this.ringatoDB;
	}
*/
}
