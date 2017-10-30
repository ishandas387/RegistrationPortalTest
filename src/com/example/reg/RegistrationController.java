package com.example.reg;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		response.setContentType("text/html");
		PrintWriter r = response.getWriter();
		int i = Integer.parseInt(request.getParameter("roll"));
		String name = request.getParameter("name");
		String city = request.getParameter("city");
		
		
		if(name.isEmpty()|| city.isEmpty())
		{
			RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
			r.println("<font color = red > Please fill all the details </font>");
			rd.forward(request, response);
		}
		else
		{
			int rowsInserted =0;
			Connection c = null;
		    try {
				Class.forName("org.postgresql.Driver");
				
				c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Employee",  "postgres", "admin");
				/* .getConnection("jdbc:postgresql://localhost:5432/Employee",
	            "postgres", "admin");*/
				
				String q = "insert into employee values(?,?,?,?)";
				
				PreparedStatement ps = c.prepareStatement(q);
				
				ps.setInt(1, i*10+22);
				ps.setString(2, name);
				ps.setInt(3, 4);
				ps.setString(4, city);
				
				 rowsInserted = ps.executeUpdate();
				 ps.close();
				 c.close();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			RequestDispatcher rd = request.getRequestDispatcher("result.jsp");
			
			r.println("<font color = red > Rows inserted"+rowsInserted+" </font>");

			rd.forward(request, response);
		}
		
	}

}
