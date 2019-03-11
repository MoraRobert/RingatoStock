package com.ringato.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ringato.services.StockItem;

import database.CreateMaintainDB;
import database.Dao;
import exeptions.DatabaseException;
import model.Book;
import model.User;

/**
 * Servlet implementation class StockListing
 */
@WebServlet("/StockListing")
public class StockListing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StockListing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private Dao ringatoListing = new Dao();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.getWriter().append("<b>Served at</b>: ").append(request.getContextPath())
		.append("<form method=\"POST\">\r\n" + 
				"  User name:<br>\r\n" + 
				"  <input type=\"text\" name=\"username\"><br>\r\n" + 
				"  Password:<br>\r\n" + 
				"  <input type=\"text\" name=\"password\"><input type=\"submit\" value=\"Send\">" + 
				"</form>");
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		try {
			User user = CreateMaintainDB.findUser(username, password);
			if(user != null) {
				response.getWriter()
					.append("<html>\r\n" + 
						"<head>\r\n" + 
						"<meta charset=\"ISO-8859-1\">\r\n" + 
						"<title>Table of available stock</title>\r\n" + 
						"</head></html>")
					.append("Right now the stock contains: ")
					.append("\r\n ");
					//StockUpload ringatoUpload = new StockUpload();
						
					//List<StockItem> listToDisplay = ringatoUpload.getRingatoDB().getStock();
				try {
					List<Book> listToDisplay = ringatoListing.queryDB();
					
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					for (Book item : listToDisplay) {
						out.println("<p>" + item.toString() + "</p>");
					}
	
					response.getWriter()
					.append("<html>\r\n" + 
							"<head>\r\n" + 
							"<meta charset=\"ISO-8859-1\">\r\n" + 
							"<title>Table of available stock</title>\r\n" +
							"<style>\r\n" + 
							"table, td {\r\n" + 
							"  border: 1px solid black;\r\n" + 
							"}\r\n" + 
							"</style>" +
							"</head>\r\n" +
							"<body><table method=\"POST\">\r\n" +
					
							"		<tr>			\r\n" + 
							"			<th>Title</th>\r\n" + 
							"			<th>Quantity</th>\r\n" + 
							"			<th>Author</th>\r\n" +
							"			<th>Illustrator</th>\r\n" +
							"			<th>Price</th>\r\n" +
							"		</tr>\r\n"); 
					for (Book sItem : listToDisplay) {
						response.getWriter()
						.append("<tr>\r\n" +
								"<td>" + sItem.getTitle() + "</td>\r\n" + 
								"			<td>" + sItem.getQuantity() + "</td>\r\n" + 
								"			<td>" + sItem.getAuthor() + "</td>\r\n" +
								"			<td>" + sItem.getIllustrator() + "</td>\r\n" +
								"			<td>" + sItem.getPrice() + "</td>\r\n" +
								"		</tr>"  
						);
					}
							
							response.getWriter()
							.append("</table></body></html>");
					
				} catch (DatabaseException e) {
					e.getMessage();
				}
			}
		} catch (database.DatabaseException e1) {			
			e1.printStackTrace();
		}
	}
}