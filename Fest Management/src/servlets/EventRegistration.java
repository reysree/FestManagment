package servlets;

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
//import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterasDocServlet
 */
@WebServlet("/EventRegistration")

public class EventRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("My sql driver loaded");
			con =DriverManager.getConnection(config.getInitParameter("jdbc:mysql://localhost:3306/fest_management"), config.getInitParameter("root"), config.getInitParameter("1234"));
		    if(con!=null) System.out.println("connection established");
		    else System.out.println("connection is not establisshed");
		   
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		//PrintWriter out=response.getWriter();
		//request.getRequestDispatcher("HeaderMovies.html").include(request, response);
		String value[]= request.getParameterValues("Events");
		String events="";
		for(String i:value) {
			events += i;
		}
		
		String rno = null; //get roll no from session 
		System.out.println(events);
		try {
			Statement stmt = con.createStatement();
			System.out.println(stmt);
			stmt.executeUpdate("insert into events values('"+rno+"','"+events+"')");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}