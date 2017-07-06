package com.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class servlet1
 */
@WebServlet("/servlet1")
public class servlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public servlet1() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		  pw.println("Please enter below values to insert.");
		  pw.println("<form action='servlet1' method='post'>");
		  pw.println("NAME: <input type='text' name='name'/></br>");
		  pw.println("AGE: <input type='text' name='age'/></br>");
		  pw.println("<input type='submit' name='insert' value='Add'/></form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw = response.getWriter();
		
		// JDBC driver name and database URL
	      final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	      final String DB_URL="jdbc:mysql://localhost/onlinebooks";
	      
	  //  Database credentials
	      final String USER = "root";
	      final String PASS = "root123";
	      boolean found = false;
	      
	      String name = request.getParameter("name");
	      String age = request.getParameter("age");
	      
	      try {
	    	// Register JDBC driver
	          Class.forName(JDBC_DRIVER);
	          Connection connection = (Connection)getServletContext().getAttribute("database.connection");
	          if (connection == null)
	          {
	                  connection = DriverManager.getConnection(DB_URL, USER, PASS);
	                  getServletContext().setAttribute("database.connection", connection);
	          }
	          
	          Statement stmt = connection.createStatement();
	        	String sql;
	        	String query = " insert into employee values ('" + name + "','" + age + "');";
	        	stmt.executeUpdate(query);
	        	pw.print("Successfully added.");
	        	response.setHeader("Refresh", "3; URL=http://localhost:8080/jdbc/servlet2");
	      } catch (Exception se) {
		    	//Handle errors for JDBC
		          se.printStackTrace();
		      }
	}

}
